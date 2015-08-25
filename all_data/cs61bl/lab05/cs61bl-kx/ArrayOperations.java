public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		for (int i =pos;i<values.length-1;i++){
			values[i] = values[i+1];
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
		// YOUR CODE HERE
		for (int i = pos;i<values.length;i++){
			int temp = values[i];
			values[i] = newInt;
			newInt = temp;
		}
		
	}
	
	//zip
	public static int[] zip(int[] array1, int[] array2){
		int z = 0;
		int[] zipped = new int[array1.length*2];
		for(int i = 0;i<array1.length;i++){
			zipped[z] = array1[i];
			zipped[z+1] = array2[i];
			z += 2;
			
		}
		return zipped;
	}
}
