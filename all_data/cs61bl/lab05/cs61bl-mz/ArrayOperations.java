public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for (int i = 0; i < values.length; i++) {
			if (i == pos) {
				for (int k = i; k+1 < values.length; k++) {
					values[k] = values[k+1];
				}
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
		int curr = 0;
		for (int i = 0; i < values.length; i++) {
			curr = values[i];
			if (i == pos) {
				values[i] = newInt;
				break;
			}
		}
		int current = 0;
		for (int j = pos + 1; j < values.length; j ++) {
			current = values[j];
			values[j] = curr;
			curr = current;
		}
	}
	
	public static int[] zip(int[] array1, int[] array2) {
		int[] result;
		result = new int[array1.length * 2];
		int pos = 0;
		for (int i = 0; i < array1.length; i++) {
			result[pos] = array1[i];
			result[pos + 1] = array2[i];
			pos += 2;
		}
		return result;
	}
}
