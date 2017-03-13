package pl.nbp.api;

public class Main {

	public static void main(String[] args) throws Exception {

		System.out.println("Podaj dane:\n- Waluta: USD, EUR, CHF, GBP");
		System.out.println("- Daty poczatkow¹ i koñcow¹ w formacie yyyy-mm-dd");
		System.out.println("Zakres dat nie mo¿e obejmowaæ przedzia³u d³u¿szego, ni¿ 93 dni.");
		
		if (args.length >= 3) {
			
			String currency = args[0];
			String dateBegin = args[1];
			String dateEnd = args[2];
											
			Connector connector = new Connector(currency, dateBegin, dateEnd);
			
						
		} else {
			System.out.println("Za ma³o danych.");
		
		
		}
	}
}
