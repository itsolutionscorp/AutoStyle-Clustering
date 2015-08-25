public class ArrayOperations {

	//zip method interleaves two arrays
	public static int[] zip (int[] array1, int[] array2) {
		int[] zipped = new int[2*array1.length];
		for (int i = 0; i < array1.length; i ++) {
			zipped[2*i] = array1[i];
			zipped[2*i + 1] = array2[i];
		}
		return zipped;
	}
	
	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for (int i = 0; i < values.length; i ++) {
			if (i < pos) {
				continue;
			}
			else if (i != values.length - 1) {
				values[i] = values[i + 1];
			}
			else {
				values[i] = 0;
			}
		}
		return;
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		int temp = values[pos];
		values[pos] = newInt;
		int temp2 = 0;
		for (int i = 0; i < values.length; i ++) {
			if (i <= pos) {
				continue;
			}
			else {
				temp2 = values[i];
				values[i] = temp;
				temp = temp2;
			}
		}
		return;
	}
}
