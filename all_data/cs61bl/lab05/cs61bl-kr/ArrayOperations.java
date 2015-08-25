public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.

	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		int k;
		for (k = pos; k <= values.length-1 ; k++) {
			if (k < values.length-1) {
				values[k] = values[k+1];
			}
			else {
				values[values.length-1] = 0;
			}
		
		}
	}
	
	public static int[] zip (int[] Array1, int [] Array2) {
		int y;
		int a = 0;
		int b = 0; 
		int c = 0;
		int[] Result = new int[2*Array1.length];
		if (Array1.length <= 0 || Array2.length <=0) {
			return Result = new int[0];
		}
		for (y = 0; y < Array1.length; y++) {
			Result[a] = Array1[c];
			Result[b+1] = Array2[c];
			a = a + 2;	
			b = b + 2;
			c++;
			
		}
		return Result;
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		int k;
		int x = 1;
		for (k = pos; k <= values.length-1; k++) {
			if (k < values.length-1) {	
				
				values[values.length-x] = values[values.length-x-1];
				x ++;
			}
			else {
				values[pos] = newInt; 
				
			}
		}
	}
}
