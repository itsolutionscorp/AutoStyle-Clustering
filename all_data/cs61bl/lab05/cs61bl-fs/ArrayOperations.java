public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for (int i = 0; i < values.length-1; i++) {
			if (i >= pos){
				values[i] = values[i+1];
			}
		}
		values[values.length-1]= 0; 
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for (int i = 0, j = 0; i < values.length; i++) {
			if (i == pos) {
				j = values[i];
				values[i] = newInt;
			} else if (i > pos) {
				int k;
				k = values[i];
				values[i] = j;
				j = k;
			}
		}
	}
	
	public static int[] zip (int[] arr1, int[] arr2) {
		int[] result = new int[arr1.length + arr2.length];
		for (int i = 0; i < arr1.length; i++){
			result[2*i] = arr1[i];
		}
		for (int i = 0; i < arr2.length; i++){
			result[2*i + 1] = arr2[i];
		}
		return result;
	}
}
