
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
			col = 0;
			while (col < row) {
				System.out.print ('*');
				col = col + 1;
			}
			System.out.println ('*');
			row = row + 1; 
		}
		

	}

}
