
public class TriangleDrawer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int col = 1;
		int row = 0;
		int SIZE = 10;
		int count = 0; 
		//while (row <= SIZE) {
		//while (col <= row){	
			//System.out.println ('*');
		while (row < SIZE){
			while (count < col) {
				System.out.print ('*');
				//row = row + 1;
				count = count + 1; 
			}
			System.out.println("");
			col = col + 1; 
			count = 0; 
			row = row + 1; 
		}	

	}
}

