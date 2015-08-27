package lab02;

public class TriangleDrawer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*

		

		
		
		System.out.println ('*');

		while (col <= row) {
		

		while (row <= SIZE) {

		*/
		
		int SIZE = 10;
		int row = 0;
		
		while (row < SIZE) {
			int col = 0;
			row = row + 1;
			while (col < row) {
				System.out.print ('*');
				col = col + 1;
			}
			System.out.println ( );
		}	
	}

}
