public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for (int k = 0; k < values.length - 1; k += 1) {
			if (k >= pos) {
				values[k] = values[k + 1];
			}
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
		for (int k = values.length - 1; k > 0; k -= 1) {
			if (k >= pos) {
				values[k] = values[k - 1];
			}
		}
		values[pos] = newInt;
	}

	public static int[] zip(int[] array1, int[] array2) {
		int[] combined = new int[array1.length*2];

		if (array1.length == array2.length) {
			int count = 0;
			for (int k = 0; k < array1.length; k += 1) {
				combined[count] = array1[k];
				count += 1;
				combined[count] = array2[k];
				count += 1;
			}
			return combined;
		}
		return null;
	}
}
