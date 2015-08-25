public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete(int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for (int i = 0; i < values.length; i++) {
			int next;
			if (i == pos) {
				for (int j = i; j < values.length; j++) {
					if (j == values.length-1){
						values[j] = 0;
					} else {	
					next = values[j+1];
					values[j] = next;
					}
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
		for (int i = 0; i < values.length; i++) {
			int next;
			if (i == pos) {
				next = values[i];
				values[i] = newInt;
				for (int j = i + 1; j < values.length; j++) {
					int after = values[j];
					values[j] = next;
					next = after;

					/*
					 * values[j+1] = next; next = values[j+1];
					 */
				}
			}
		}
	}
	
	public static int[] zip(int[] array1, int[] array2) {
		int[] newArray = new int[(array1.length*2)];
		int x = 0;
		for (int i=0; i<newArray.length; i+=2) {
			newArray[i] = array1[x];
			newArray[i+1] = array2[x];
			x++;
		}
		return newArray;

	}	
}
