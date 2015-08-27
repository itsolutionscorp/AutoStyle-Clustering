package lab02;

public class TriangleDrawer {
	public static void main(String[] args) {
	int SIZE = 10;
	int row = 0;
	
	while (row < SIZE) {
		row = row + 1;
		int col = 0;
		while (col < row) {
			col = col + 1;
			System.out.print ('*');	
			}
		System.out.println ( );
		}
	}
}




