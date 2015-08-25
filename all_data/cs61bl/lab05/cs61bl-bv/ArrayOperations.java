public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		for (int i = 0; i < values.length; i++) {
			if (i == values.length - 1) {
				values[i] = 0;
				break;
			}
			int nextInt = values[i+1];
			if (i == pos) {
				values[i] = nextInt;
				pos++;
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
		// YOUR CODE HERE
		int oldInt = values[0];
		for (int i = 0; i < values.length; i++) {
			oldInt = values[i];
			if (i == pos) {
				values[i] = newInt;
				pos++;
				newInt = oldInt;
			}
		}
	}
	
	//Takes two int arrays, array1 and array2, of the same length.
	//Returns the int array result of interleaving them (int length should be
	//the sum of the lengths of the 2 arrays).
	public static int[] zip(int[] array1, int[] array2) {
		int[] new_array = new int[array1.length + array2.length];
		int new_counter = 0;
		int array1_counter = 0;
		int array2_counter = 0;
		while (new_counter < new_array.length) {
			if (new_counter % 2 == 0) {
				new_array[new_counter] = array1[array1_counter];
				array1_counter++;
			} else {
				new_array[new_counter] = array2[array2_counter];
				array2_counter++;
			}
			new_counter++;
		}
		return new_array;
	}
}
