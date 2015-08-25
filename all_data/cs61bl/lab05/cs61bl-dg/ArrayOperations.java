public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		for (int position = pos;position<values.length-1;position++){
			values[position] = values[position+1];
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
		for (int position = values.length-1;position>pos;position--){
			values[position] = values[position-1];
		}
		values[pos] = newInt;
	}
	
	public static int[] zip(int[] array1, int[] array2) {
		int [] returnArray = new int[array1.length*2];
		for(int x = 0; x < returnArray.length; x=x+2){
			returnArray[x] = array1[x/2];
			returnArray[x+1] = array2[x/2];
		}
		return returnArray;
	}
}
