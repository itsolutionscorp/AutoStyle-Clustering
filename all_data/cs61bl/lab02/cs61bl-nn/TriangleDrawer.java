
public class TriangleDrawer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int SIZE = 10;
		int row = 0;
		
		while (row < SIZE) {
			int col = 0;
			while (col < row) {
				System.out.print ('*');
				col = col + 1;
			}
			row = row + 1;
			System.out.println ('*');
		}

	}

}
