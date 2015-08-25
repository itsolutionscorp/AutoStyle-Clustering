public class ArrayOperations {

	
	// Given two int arrays array 1 and array2 of the same length, zip 
	// should return an array result that is twice as long,
	// in which the elements of array1 and array2 are interleaved.
	
	public static int[] zip(int[] array1, int[] array2) {
		int arrayLength = array1.length + array2.length;
		int[] result = new int[arrayLength];
		for (int k = 0, j = 0; j < array1.length; k = k + 2, j = j + 1) {
			result[k] = array1[j];
			result[k+1] = array2[j];
		}
		return result;
	}
	
	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete(int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}

		for (int k = 0; k < values.length; k++) {
			if (k >= pos) {
				if (k == values.length - 1) {
					values[k] = 0;
				} else {
					values[k] = values[k + 1];
				}
			}
		}
	}

	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert(int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		int currentValue = 0;

		for (int k = 0; k < values.length; k++) {
			if (k == pos) {
				currentValue = values[k];
				values[k] = newInt;
				break;
			}
		}
		for (int k = 0; k < values.length; k++) {
			if (k > pos) {
				int nextValue = values[k];
				values[k] = currentValue;
				currentValue = nextValue;
			}
		}
	}
}
