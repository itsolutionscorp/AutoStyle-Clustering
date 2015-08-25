public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for (int k = pos; k < values.length; k++) {
			if (k == values.length - 1) {
				values[k] = 0; 
			}
			else {
				values[k] = values [k+1];
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
		for (int p = values.length - 1; p > pos; p -= 1) {
			values[p] = values[p - 1];
		}
		values[pos] = newInt;
		}
	public static int[] zip (int[] array1, int[] array2) {
		 int resultlength = array1.length + array2.length;
		 int[] result = new int[resultlength];
		 for (int k = 0, j = 0; j < array1.length; k = k + 2, j = j + 1) {
			 result[k] = array1[j];
			 result[k+1] = array2[j];
		 }
		 return result;
		 
	}
}
