
public class TriangleDrawer {

	public static void main(String[] args) {
		int col = 0;
		
		int SIZE = 15;
		while (col < SIZE) {
			int row = 0;
			while (row < col) {
				System.out.print ('*');
				row = row + 1;
			}
			col = col + 1;
			System.out.println ('*');
			
		}
	}
}




