
public class TriangleDrawer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int col = 0;
		int row = 0;
		int SIZE = 10;
		
		while (row < SIZE) {
			if (col < row) {
				col += 1;
				System.out.print('*');
			} else {
				System.out.println('*');
				row += 1;
				col = 0;
			}
			}
		}
}