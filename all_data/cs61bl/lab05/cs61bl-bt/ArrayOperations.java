public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		int [] copyOfValues = new int [values.length];
		System.arraycopy(values, 0, copyOfValues, 0, values.length);
		for (int p = 0; p < values.length; p++){
			if (p == values.length - 1){
				values[p] = 0;
			}
			else if (p >= pos){
				values[p] = copyOfValues[p+1];
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
		int [] copyOfValues = new int [values.length];
		System.arraycopy(values, 0, copyOfValues, 0, values.length);
		for (int p = 0; p < values.length; p++){
			if (p == pos){
				values[p] = newInt;
			}
			else if (p > pos){
				values[p] = copyOfValues[p-1];
			}
		}
	}
	
	public static int[] zip(int[] array1, int[] array2){
		int [] result = new int[array1.length + array2.length];
		if (array1.length != array2.length){
			return result;
		}
		for (int k = 0, b = 0; k < result.length - 1; k++, b++){
			result[k] = array1[b];
			k++;
		}
		for (int k = 1, b = 0; k < result.length; k++, b++){
			result[k] = array2[b];
			k++;
		}
		return result;
	}
}
