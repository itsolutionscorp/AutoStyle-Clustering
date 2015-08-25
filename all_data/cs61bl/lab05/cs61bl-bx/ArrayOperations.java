public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.

	public static void delete(int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for (int i = pos; i < values.length - 1; i++) {
			values[i] = values[i + 1];
		}
		values[values.length - 1] = 0;
	}

	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert(int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		int[] newValues = new int[values.length];
		for (int i = 0; i < pos; i++) {
			newValues[i] = values[i];
		}
		newValues[pos] = newInt;
		for (int i = pos + 1; i < newValues.length; i++) {
			newValues[i] = values[i - 1];
		}
		for (int i = 0; i < values.length; i++) {
			values[i] = newValues[i];
		}
	}

	public static int[] zip(int[] array1, int[] array2) {
		int[] finalArray = new int[array1.length * 2];
		int counter = 0;
		for (int i = 0; i < finalArray.length; i += 2) {
			finalArray[i] = array1[counter];
			finalArray[i + 1] = array2[counter];
			counter++;
		}
		return finalArray;

	}

}
