public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		else {
			for(int n = pos; n < values.length - 1; n += 1){
				values[n] = values[n + 1];
			}
			values [values.length -1] = 0;
		}
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		else {
			for(int n = values.length - 1; n > pos; n -= 1){
				values[n] = values[n-1];
			}
			values[pos] = newInt;
				
		}
	}
	
	public static int[] zip(int[] array1, int[] array2){
		if (array1.length == 0  && array2.length == 0){
			return new int[0];
		} else {
			int[] result;
			int k = 0;
			result = new int [2 * array1.length];
			for(int n = 0; n < array1.length + 2; n += 2) {
				result[n] = array1[k];
				result[n + 1] = array2[k];
				k += 1;
			}
			return result;
		}
	}
}
