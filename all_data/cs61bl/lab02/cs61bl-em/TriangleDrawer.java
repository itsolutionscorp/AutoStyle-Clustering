package lab02;

public class TriangleDrawer {

	public static void main (String[] args) {
		
		int row = 0;
		int SIZE = 3;
	
		while (row < SIZE) {
			int col = 0;
			row = row + 1;
			while (col < row) {
				System.out.print ('*' );
				col = col + 1;
			}
			System.out.println();
			
		}
	//while (col <= row) { <-- unused line
	//while (row <= SIZE) { <-- unused line
	//System.out.println('*'); <-- unused line
		
	}
}
