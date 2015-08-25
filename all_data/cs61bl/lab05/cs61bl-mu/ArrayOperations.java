public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}else{
			for(int i = 1; (i+pos) <= values.length-1; i++){
				values[pos+i-1]= values[pos+i];
			}
				values[values.length-1] = 0;
		}
		// YOUR CODE HERE
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}else {
		for(int i  = values.length - 1; pos < i; i--){
			values[i] = values[i-1];
		}
		values[pos] = newInt;
		}
	}
	
	public static int[] zip (int[] array1, int[] array2) {
		int[] result = new int[array1.length + array2.length];
		int k = 0;
		for(int i = 0; i <= array1.length-1; i++){
			
			result[k] = array1[i];
			result[k+1] = array2[i];
			k = k+2;
		}
		return result;
		
	}
}
