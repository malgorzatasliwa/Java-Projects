//1. Jeœli ¿ywa komórka ma mniej ni¿ 2 s¹siadów ginie z samotnoœci
//2. Je¿eli ¿ywa komórka ma 2 albo 3 ¿ywych s¹siadów prze¿yje do nastêpnej tury
//3. Je¿eli ¿ywa komórka ma wiêcej ni¿ 3 s¹siadów ginie z przeludnienia
//4. Jeœli martwa komórka ma dok³adnie 3 s¹siadów, o¿ywa

public class Main {

	public static void main(String[] args) {

		int width = 5;
		int height = 5;

		Board board = new Board(width, height);

		for (int i = 0; i < width; i++) { // wype³ni macierz falsami
			for (int j = 0; j < height; j++) {
				System.out.println(board.getCellValue(i, j));
			}
		}
		System.out.println("");

		board.setCellValue(2, 2, false);
		board.setCellValue(2, 1, true);
		board.setCellValue(2, 3, true);
		board.setCellValue(1, 2, true);

		System.out.println("2-2 alife - " + board.getCellValue(2, 2));
		System.out.println("AliveNeighbours " + board.countAliveNeighbours(2, 2));
		board.nextCycle();
		System.out.println(board.getCellValue(2, 2));

	}

}
