public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for (int i = 0; i < values.length-1; i++) {
			if (i >= pos) {
				values[i] = values[i+1];
			}
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
		for (int i = values.length-1; i>=0 ;i--) {
			if (i > pos) {
				values[i] = values[i-1];
			} else if (i == pos) {
				values[pos] = newInt;
			}
		}
	}
	
	public static int [] zip (int[] array1, int[] array2) {
		int[] temp = new int[array1.length+array2.length];
		for (int i = 0; i < array1.length; i++) {
			temp[2*i] = array1[i] ;
			temp[1+2*i] = array2[i];
		}	
		return temp;
	}
}
