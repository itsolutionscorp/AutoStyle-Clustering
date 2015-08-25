public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for (int k = pos; k < values.length - 1; k++) {
			values[k] = values[k + 1];
		}
		values[values.length - 1] = 0;
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for (int k = values.length - 1; k > pos; k--) {
			values[k] = values[k-1];
		}
		values[pos] = newInt;
	}
	
	public static int[] zip (int[] array1, int[] array2) {
		int length = array1.length + array2.length;
		int[] result = new int[length];
		for (int resultIndex = 0, arrayIndex = 0; resultIndex < length-1; resultIndex++, arrayIndex++) {
			result[resultIndex] = array1[arrayIndex];
			resultIndex++;
			result[resultIndex] = array2[arrayIndex];
		}
		return result;
	}
}