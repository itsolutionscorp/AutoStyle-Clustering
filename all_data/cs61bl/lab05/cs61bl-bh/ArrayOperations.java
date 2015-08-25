public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		int k = pos;
		int n;
		while (k+1 < values.length) {
			n = values[k+1];
			values[k] = n;
			k++;
		}
		values[k] = 0;
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		/**int k = pos;
		int n;
		while (k < values.length) {
			n = values[k];
			values[k] = newInt;
			newInt = n;
			k++;
		}*/
		for (int n, k = pos; k < values.length; k++) {
			n = values[k];
			values[k] = newInt;
			newInt = n;
		}
	}
	
	public static int[] zip (int[] array1, int[] array2) {
		int[] result = new int[array1.length*2];
		for (int k = 0; k < array1.length; k++) {
			result[2*k] = array1[k];
			result[2*k+1] = array2[k];
		}
		return result;
	}
}
