public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		} else {
			for (int count = pos; count <= values.length - 2; count++) {
				values[count] = values[count + 1];
			}
			values[values.length - 1] = 0;
		}
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		} else {
			for (int count = values.length - 1; count > pos; count--) {
				values[count] = values[count - 1];
			}
			values[pos] = newInt;
		}
	}
	
	public static int[] zip (int[] values1, int[] values2) {
		int[] result;
		result = new int[values1.length + values2.length];
		for (int count = 0; count <= values1.length - 1; count++) {
			result[2 * count] = values1[count];
			result[(2 * count) + 1] = values2[count];
		}
		return result;
	}
}
