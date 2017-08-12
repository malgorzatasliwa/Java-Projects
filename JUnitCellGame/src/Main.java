//1. Je�li �ywa kom�rka ma mniej ni� 2 s�siad�w ginie z samotno�ci
//2. Je�eli �ywa kom�rka ma 2 albo 3 �ywych s�siad�w prze�yje do nast�pnej tury
//3. Je�eli �ywa kom�rka ma wi�cej ni� 3 s�siad�w ginie z przeludnienia
//4. Je�li martwa kom�rka ma dok�adnie 3 s�siad�w, o�ywa

public class Main {

	public static void main(String[] args) {

		int width = 5;
		int height = 5;

		Board board = new Board(width, height);

		for (int i = 0; i < width; i++) { // wype�ni macierz falsami
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
