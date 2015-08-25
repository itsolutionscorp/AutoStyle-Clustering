public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for (int k = (pos+1); k<values.length; k+=1) {
			values[k-1] = values[k];
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
		for (int k = (values.length - 1); k > pos; k-=1) {
			values[k] = values[k-1];
		}
		values[pos] = newInt;	
	}
	
	public static int[] zip (int[] array1, int[] array2) {
		int[] result = new int[2*array1.length];
		for (int k = 0; k<(2*array1.length); k+=1) {
			if (k % 2 == 0) {
				result[k] = array1[k/2]; 
			} else {
				result[k] = array2[k/2];
			}
		}
		return result;
	}
}
