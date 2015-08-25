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
	
	public static int[] zip(int[] arrayone, int[] arraytwo){
		int[] newarray = new int[arrayone.length + arraytwo.length];
		
		for(int i = 0; i < arrayone.length; i++){
				newarray[2*i] = arrayone[i];
				newarray[2*i + 1] = arraytwo[i];
		}
		return newarray;
	}

	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert(int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}

		for (int i = values.length - 2; i >= pos; i--) {
			values[i + 1] = values[i];
		}
		values[pos] = newInt;
	}
}
