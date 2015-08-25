
public class TriangleDrawer {

	public static void main(String[] args) {
		int SIZE = 10;
		int row = 0;
		while(row<SIZE){
			int col = 0;
			row=row+1;
			while(col<row){
				System.out.print ('*');
				col=col+1;
			}
			System.out.println ( );
		}

	}
	/*
	 * col = col + 1;
int col = 0;
int row = 0;
int SIZE = 10;
row = row + 1;
System.out.print ('*');
System.out.println ('*');
System.out.println ( );
while (col <= row) {
while (col < row) {
while (row < SIZE) {
while (row <= SIZE) {

	 */
}
