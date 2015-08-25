public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		else {
			for (int i=0; pos < values.length-1; pos++){
			values[pos] = values[pos + 1];
			}
			values[values.length-1] = 0;
			
		}
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		else {
			
			for (int i = values.length - 1; i > pos; i--) {
				values[i] = values[i-1];

			}
			values[pos] = newInt;
		}
	}
	
	public static int[] zip(int[] array1, int[] array2) {
		if (array1.length != array2.length)
		{
			return null;
		}
		else { 
			int[] res = new int[array1.length * 2];
			int n = 0;
			for(int i=0; i<res.length;i=i+2) {
				
				res[i] = array1[n];
				res[i+1] = array2[n];
				n=n+1;
			}
			return res;
		}
	}
}
