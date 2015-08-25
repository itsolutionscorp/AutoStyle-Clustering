public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		int i = pos;
		while (i < values.length - 1) {
			values[i] = values[i + 1]; 
			i++;
		}
		values[values.length - 1] = 0;
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		int i = values.length - 1; 
		while (pos < i) {
			values[i] = values[i-1]; 
			i--;
		}
		values[pos] = newInt;
	}
	
	//Given two int arrays array1 and array2 of the same length, zip 
	//should return an array result that is twice as long, in which the 
	//elements of array1 and array2 are interleaved.
	
	public static int[] zip (int[] array1, int[] array2) {
		int pos1 = 0;
		int pos2 = 0;
		int totalLength = array1.length * 2;
		int[] zipped = new int[totalLength];
		for (int i = 0; i < totalLength; i++) {
			if (i % 2 == 0) {
				zipped[i] = array1[pos1];
				pos1++;						
			} else {
				zipped[i] = array2[pos2];
				pos2++;
			}
		}
		
		return zipped; 	
	}
}
