public class ArrayOperations {
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos > 0 || pos <= values.length) {
			for (int k = values.length - 1; k >= pos; k--) {
				if (k == pos) {
					values[k] = newInt;
				} else {
				values[k] = values[k - 1];
				}
			}
		}
	}

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos > 0 || pos <= values.length) {
			for (int k = pos; k < values.length; k++) {
				if (k == values.length - 1) {
					values[k] = 0;
				} else {
				values[k] = values[k + 1];
				}
			}
		}
	}
	
	public static int[] zip (int[] array1, int[] array2) {
		int[] result;
		result = new int[array1.length + array2.length];
		for (int i = 0; i < result.length; i++) {
			if (i % 2 == 0) {
				result[i] = array1[i / 2];
			} else {
				result[i] = array2[i / 2];
			}
		}
		return result;
	}
}
