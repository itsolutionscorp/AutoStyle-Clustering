
public class TriangleDrawer {
	public static void main(String[] args) {
		int row = 0;
		int SIZE = 10;
		
		while (row < SIZE) {
			int col = 0;			
			
			while (col < row) {		
				System.out.print ('*');
				col = col + 1;
			}
			
			row = row + 1;
			System.out.println ('*');
		}
		System.out.println ( );
	}
}

