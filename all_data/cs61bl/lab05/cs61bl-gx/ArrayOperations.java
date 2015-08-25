public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		
		for (int i = pos; i < values.length - 1; i++) {
			values[i] = values[i+1];
		}
		
		values[values.length - 1] = 0;
		
		// YOUR CODE HERE
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		
		for (int i = values.length - 1; i > pos; i--) {
			values[i] = values[i - 1];
		}
		values[pos] = newInt;

	}
	
	public static int[] zip(int[] array1, int[] array2) {
		int[] set;
		
		if (array1 == null 	|| array2 == null) return null;
		if (array1.length != array2.length) return null;
		
		set = new int[2 * array1.length];
		for (int i = 0; i < array1.length; i++) {
			set[i * 2] = array1[i];
			set[i * 2 + 1] = array2[i];
		}
		
		return set;
	}
	
}
