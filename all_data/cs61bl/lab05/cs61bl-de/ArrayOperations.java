public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		for(int i = pos; i < values.length-1; i++){
			values[i] = values[i+1];
		}
		values[values.length-1]=0;
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		for(int i = values.length-1; i > pos; i--){
			values[i] = values[i-1];
		}
		values[pos] = newInt;
	}
	
	// Merges two given arrays Ar1 and Ar2 starting with Ar1[0] and
	// alternating elements of the arrays until they end
	public static int[] zip(int[] array1, int[] array2) {
		int[] newArray = new int[array1.length + array2.length];
		int i = 0, j = 0, k = 0;
		while(k < array1.length + array2.length){
			if(i < array1.length){
				newArray[k] = array1[i];
				k++;
				i++;
			}
			if(j < array2.length){
				newArray[k] = array2[j];
				k++;
				j++;
			}
		}
		return newArray;
	}	
}	
	