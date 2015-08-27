
public class TriangleDrawer {

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
			col = 0;
			System.out.println ('*');
			row = row + 1;
		}
	}

}
