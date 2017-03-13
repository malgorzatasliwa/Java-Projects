package pl.nbp.api;

public class Main {

	public static void main(String[] args) throws Exception {

		System.out.println("Podaj dane:\n- Waluta: USD, EUR, CHF, GBP");
		System.out.println("- Daty poczatkowa i koncowa w formacie yyyy-mm-dd");
		System.out.println("Zakres dat nie moze obejmowac przedzialu dluzszego, niz 93 dni.");
		
		if (args.length >= 3) {
			
			String currency = args[0];
			String dateBegin = args[1];
			String dateEnd = args[2];
											
			Connector connector = new Connector(currency, dateBegin, dateEnd);
			
						
		} else {
			System.out.println("Za malo danych.");
		
		
		}
	}
}
