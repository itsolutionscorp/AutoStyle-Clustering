public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for(int i = pos; i < values.length - 1; i++) {
			values[i] = values[i+1];
		}
		values[values.length-1] = 0;
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for(int i = values.length-1; i > pos; i--) {
			values[i] = values[i-1];
		}
		values[pos] = newInt;
	}
	
	public static int[] zip(int[] a, int[] b) {
		int[] result = new int[a.length * 2];
		int resultIndex = 0;
		for(int i = 0; i<a.length;i++) {
			result[resultIndex] = a[i];
			resultIndex++;
			result[resultIndex] = b[i];
			resultIndex++;
		}
		return result;
	}
}
