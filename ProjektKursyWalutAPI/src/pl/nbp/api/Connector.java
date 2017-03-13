package pl.nbp.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Connector {

	private String currency;
	private String dateBegin;
	private String dateEnd;

	private ArrayList<Double> currencyBuy;
	private ArrayList<Double> currencySell;
	
	private Document xmlFromServer;
	
	public Connector(String currency, String dateBegin, String dateEnd) {
		
		this.currency = currency;
		this.dateBegin = dateBegin;
		this.dateEnd = dateEnd;

		String url = "http://api.nbp.pl/api/exchangerates/rates/c/" + currency + "/" + dateBegin + "/" + dateEnd
				+ "/?format=xml";
		System.out.println("URL zapytania do API: " + url);

		currencyBuy = new ArrayList<Double>();
		currencySell = new ArrayList<Double>();
		
		try {
			downoloadXML(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {			
			e.printStackTrace();
		} catch (SAXException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		readXML();
	}
	private void downoloadXML(String url)
			throws ParserConfigurationException, SAXException, IOException, MalformedURLException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		xmlFromServer = db.parse(new URL(url).openStream());
		xmlFromServer.getDocumentElement().normalize();
		
	}
	
	private void readXML() {
		try {			
			NodeList nodes = xmlFromServer.getElementsByTagName("Rate");
			System.out.println("Nodes length: " + nodes.getLength());
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				Element element = (Element) node;
				try {
					currencyBuy.add(Double.parseDouble((element.getElementsByTagName("Bid").item(0).getTextContent())));
				} catch (DOMException e) {
					e.printStackTrace();
				}
				try {
					currencySell.add(Double.parseDouble((element.getElementsByTagName("Ask").item(0).getTextContent())));
							
				} catch (DOMException e) {
					e.printStackTrace();
				}
				
			}
			System.out.println("currencyBuy: " + currencyBuy);
			System.out.println("currencySell: " + currencySell);	
			getReadFromNBP();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

		private void getReadFromNBP() {
			System.out.println(currency + " › kod waluty");
			System.out.println(dateBegin + " › data poczatkowa");
			System.out.println(dateEnd + " › data koncowa");
			System.out.printf("%.4f › sredni kurs kupna\n", meanBuy());
			if(standDeviationSell() != 0.0) {
				System.out.printf("%.4f › odchylenie standardowe kursow sprzedazy", standDeviationSell());
			} else {
				System.out.println("Zbyt malo danych › odchylenie standardowe kursow sprzedazy");
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


