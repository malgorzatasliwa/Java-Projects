package pl.parser.nbp;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;


public class XMLDownloader {
	
	private String currency;
	private LocalDate dateBegin;
	private LocalDate dateEnd;
	
	private ArrayList<String> currencyBuySellTables;
	private ArrayList<Double> currencyBuy;
	private ArrayList<Double> currencySell;
	
	public XMLDownloader(String currency, String dateBegin, String dateEnd) {
		
		this.currency = currency;
		try
		{					
			this.dateBegin = LocalDate.parse(dateBegin);
			this.dateEnd = LocalDate.parse(dateEnd);
		}
		catch (java.time.format.DateTimeParseException e) {
			System.out.println("Z³y format daty, u¿yj: yyyy-mm-dd");
			return;
		}
	
		currencyBuySellTables = new ArrayList<String>();
		currencyBuy = new ArrayList<Double>();
		currencySell = new ArrayList<Double>();
						
	}
	
	public void tablesLoad()
	{
		
		int startYear = dateBegin.getYear();
		int endYear = dateEnd.getYear();	
		

		String urlString = "http://www.nbp.pl/kursy/xml/dir"; //main address where're all files with tables
		URL dir = null;
		for (int yr = startYear; yr <= endYear; yr++) {
			try {
			
				if(yr == 2016)
					dir = new URL(urlString + ".txt");  //http://www.nbp.pl/kursy/xml/dir.txt - files from 2016 yr
				else
					dir = new URL(urlString + yr + ".txt"); //http://www.nbp.pl/kursy/xml/dir2002.txt (i.e. of 2002 yr)
			
				InputStreamReader inputStream = new InputStreamReader(dir.openStream()); // opens and reads from specific yr (that contains xml files c001z020102,h001z020102)
				BufferedReader inputBuffer = new BufferedReader(inputStream); 
				String inputLine;
				while ((inputLine = inputBuffer.readLine()) != null) {
					if (inputLine.charAt(0) == 'c') {
						currencyBuySellTables.add(inputLine); // adds all xml files that start with c
					}
				}
				
				inputBuffer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}			
	
	public void parseXMLfiles() {
		try {
			DocumentBuilderFactory docBuilderFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFact.newDocumentBuilder();

			for(String tableFile : currencyBuySellTables) { // tableFile it's a xml file named i.e. c001z020102
				int len = tableFile.length();
				LocalDate tableFileName = LocalDate.parse("20" + tableFile.substring(5, 7) + //yr "20"+01
														   "-" + tableFile.substring(7, 9) + //month 01
														   "-" + tableFile.substring(9, len)); // day 01			

				
				if(dateBegin.compareTo(tableFileName) <= 0 && dateEnd.compareTo(tableFileName) >= 0) {  // checks date scope
					Document doc = docBuilder.parse("http://www.nbp.pl/kursy/xml/" + tableFile + ".xml"); // gets all currency xml files which are in date scope
					doc.getDocumentElement().normalize(); // doc is now xml tree
					readXML(doc);
				}		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (currencyBuy.size() == 0 || currencySell.size() == 0) {
			System.out.println("Coœ posz³o nie tak.");
		} else {
			getReadFromNBP(); //standard out
		}
		
	}

	private void readXML(Document doc) {

		NodeList nodeList = doc.getElementsByTagName("data_publikacji"); // takes all elements <data_publikacji> from xml file
		Node node = nodeList.item(0); // each file has only one <data_publikacji> at the beginning
		String publishingDate = node.getTextContent(); // takes node and puts it into String
		LocalDate publishDate = LocalDate.parse(publishingDate);
		
		if (dateBegin.compareTo(publishDate) <= 0 && dateEnd.compareTo(publishDate) >= 0) { // checks publishDate in xml
			NodeList nList = doc.getElementsByTagName("pozycja"); //fills NodeList nList with all elements <pozycja>...</pozycja>
			for (int nodeIndex = 0; nodeIndex < nList.getLength(); nodeIndex++) {
				Node nNode = nList.item(nodeIndex); //each nNode it's a next subsequent <pozycja>

				if (nNode.getNodeType() == Node.ELEMENT_NODE) { 
					Element eElement = (Element) nNode; // nNode is casted to Element type (value that we search for)

					if (currency.equals(eElement.getElementsByTagName("kod_waluty").item(0).getTextContent())) { // first four lines in one line
						try {
							NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMAN); // formater 

							currencyBuy.add(numberFormat.parse(eElement.getElementsByTagName("kurs_kupna").item(0).getTextContent()).doubleValue());
							currencySell.add(numberFormat.parse(eElement.getElementsByTagName("kurs_sprzedazy").item(0).getTextContent()).doubleValue());
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					}
				}
			}
		}

	}
	
	private void getReadFromNBP() {
		System.out.println(currency + " › kod waluty");
		System.out.println(dateBegin + " › data pocz¹tkowa");
		System.out.println(dateEnd + " › data koñcowa");
		System.out.printf("%.4f › œredni kurs kupna\n", meanBuy());
		if(standDeviationSell() != 0.0) {
			System.out.printf("%.4f › odchylenie standardowe kursów sprzeda¿y", standDeviationSell());
		} else {
			System.out.println("Zbyt ma³o danych › odchylenie standardowe kursów sprzeda¿y");
		}
	}
	
	private double meanBuy() { 
		double sum = 0;
		
		for(double e : currencyBuy) {
			sum+=e;
		}
		
		return sum/currencyBuy.size();
	}
	
	private double standDeviationSell() {
		if(currencySell.size() == 1) { //Return 0 if there is only one element in ArrayList
			return 0;
		}
		
		double sum = 0;
		for(double e : currencySell) {
			sum+=e;
		}
		sum = sum/currencySell.size(); 
		
		double standardDev = 0;
		for(double e : currencySell) {
			standardDev = Math.pow(e-sum,2) + standardDev;
		}
		standardDev = Math.sqrt( standardDev/(currencySell.size()) ); 
		
		return standardDev;
	}
	

	

}
