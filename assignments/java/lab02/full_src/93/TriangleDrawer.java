
public class TriangleDrawer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int col = 0;
		int row = 0;
		int SIZE = 10;
		while (row < SIZE) {
			while (col < row) {
				System.out.print ('*');
				col = col + 1;
			}
			System.out.println ( );
			col = 0;
			row = row + 1;
		}
	}

}
