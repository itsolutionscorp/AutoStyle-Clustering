public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for (int n = pos; n<values.length-1; n++){
			values[n] = values[n+1];
		}
		values[values.length -1] = 0;
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for (int n = values.length - 1; n > pos; n--) {
			values[n] = values[n-1];
		}
		values[pos] = newInt;
	}
	public static int[] zip (int[] array1, int[] array2) {
		int[] result = new int[array1.length * 2];
		if (array1.length == array2.length) {
			for (int k = 0; k < array1.length; k++) {
				result[2*k] = array1[k];
				result[2*k+1] = array2[k];
			}
			return result;
		} else {
			return result;
		}
		
	}
	
}
