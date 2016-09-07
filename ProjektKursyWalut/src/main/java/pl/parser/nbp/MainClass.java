// Task URL: http://www2.smart4aviation.aero/java_developer/task.php

package pl.parser.nbp;

public class MainClass {

	public static void main(String[] args) {
		System.out.println("Podaj dane:\n- Waluta: USD, EUR, CHF, GBP\n- Daty poczatkow¹ i koñcow¹ w formacie yyyy-mm-dd\n");
		if (args.length >= 3) {
			String currency = args[0];
			String dateBegin = args[1];
			String dateEnd = args[2];

			XMLDownloader downloader = new XMLDownloader(currency, dateBegin, dateEnd);
			downloader.tablesLoad();
			downloader.parseXMLfiles();

		} else {
			System.out.println("Za ma³o danych.");
		}
	}

}
