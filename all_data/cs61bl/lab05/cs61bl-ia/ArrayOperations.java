public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		if (pos != values.length - 1) {
			for (int current = pos; current < values.length - 1; current += 1) {
				values[current] = values[current + 1];
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
		for (int current = values.length - 1; current > pos; current--) {
			values[current] = values[current - 1];
		}
		values[pos] = newInt;
	}
	
	// Given two int arrays (array1, array2) of the same length,
	// returns an array result that is twice as long, in which the
	// elements of array1 and array2 are interleaved.
	public static int[] zip (int[] array1, int[] array2) {
		int[] result = new int[array1.length + array2.length];
		for (int current = 0, k = 0; k < array1.length; current += 2, k++){
			result[current] = array1[k];
			result[current + 1] = array2[k];
		}
		return result;
	}
}
