public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		for (int idx = pos; idx < values.length; idx++) {
			if (idx == values.length - 1) {
				values[idx] = 0;
			} else {
				values[idx] = values[idx+1];
			}
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
		int next = values[pos];
		values[pos] = newInt;
		for (int idx = pos+1; idx < values.length; idx++) {
			int nextnext = values[idx];
			values[idx] = next;
			next = nextnext;
		}
		
	}
	
	
	public static int[] zip (int[] array1, int[] array2) {
//		if (array1.length != array2.length) {
//			return  ;
//		}
		int [] result = new int[array1.length*2];
		int idx_result = 0;
		for (int idx = 0; idx < array1.length; idx++) {
			result[idx_result] = array1[idx];
			result[idx_result+1] = array2[idx];
			idx_result += 2;
		}
		return result;
	}
}
