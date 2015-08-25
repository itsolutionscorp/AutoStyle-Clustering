public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		for(int k = pos; k < values.length-1; k++) {
			values[k] = values[k+1];
		}
		values[values.length-1] = 0;
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		
		int[] temp = new int[values.length];
		
		for (int k = 0; k < values.length; k++) {
			temp[k] = values[k];
		}
		
		for(int k = pos; k < values.length-1; k++) {
			values[k+1] = temp[k];
		}
		values[pos] = newInt;
	}
	
	public static int[] zip (int[] array1, int[] array2) {
		int[] zipArray = new int[2*array1.length];
		
		if (array1.length != 0) {
			for (int k = 0; k < zipArray.length; k = k + 2) {
				zipArray[k] = array1[k/2];
				zipArray[k+1] = array2[k/2];
			}
		}
		
		return zipArray;
		
	}
}
