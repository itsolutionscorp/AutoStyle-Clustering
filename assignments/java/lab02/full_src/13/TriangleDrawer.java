public class TriangleDrawer {
	public static void main(String args[]) {
		int SIZE = 10;
		int col = 0;

		while (col <= SIZE) {
			int row = 0;
			while (row <= col) {
				System.out.print('*');
				row += 1;
			}
		col += 1;
		System.out.println();
		}
	}
}