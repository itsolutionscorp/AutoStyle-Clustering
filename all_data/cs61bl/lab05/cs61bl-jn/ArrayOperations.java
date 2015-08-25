public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void remove (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for (int i = 0; i < values.length; i++) {
			if (i >= pos && i != values.length - 1) {
				values[i] = values[i + 1];
				continue;
			} else if (i == values.length - 1) {
				values[i] = 0;
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
		int[] copy = values.clone();
		for (int i = 0; i < values.length; i++) {
			if (i == pos) {
				values[i] = newInt;
			} else if (i > pos) {
				values[i] = copy[i - 1];
			}
		}
	}
	
	public static int[] zip (int[] array1, int[] array2) {
		int[] result = new int[array1.length * 2];
		for (int i = 0, k = 0; i < array1.length; i += 1, k += 2) {
			result[k] = array1[i];
			result[k+1] = array2[i];
		}
		return result;
		
	}
}
