
public class TriangleDrawer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int SIZE = 7;
		int col = 0;
		int row = 0;
		while (row < SIZE) {
			row = row + 1;
			while (col < row) {
				col = col + 1;
				System.out.print ('*');
			}
			System.out.println ();
			col = 0;
		}
	}
}
