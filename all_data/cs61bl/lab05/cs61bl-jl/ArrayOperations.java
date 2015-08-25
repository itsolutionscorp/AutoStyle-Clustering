public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for (int i = 0; i < values.length; i++) {
			if (i < pos) { 
				continue;
			}
			else if (i == values.length - 1){
				values[i] = 0; 
			}
			else if (i >= pos && i < values.length) {
				values[i] = values[i+1];
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
		int temp = values[0];
		for (int i = 0; i < values.length; i++) {
			if (i < pos) { 
				continue;
			}
			else if (i == pos) {
				temp = values[i];
				values[i] = newInt;
			}
			else if (i > pos) {
				int temp1 = values[i];
				values[i] = temp;
				temp = temp1;
			}
		}
	}
	
	public static int[] zip(int[] arr1, int[] arr2) {
		int[] result = new int[arr1.length + arr2.length];
		for (int i= 0, j = 0; i < arr1.length; i++, j += 2) { 
				result[j] = arr1[i];
				result[j + 1] = arr2[i];
			}
		return result;
	}
}
