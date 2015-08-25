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
		while (i<values.length-1){
			values[i] = values[i+1];
			i = i+1;
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
			for (int k = 0; k == pos; k=k+1) {
				int temp;
				int i = values.length-1;
				while (i > pos) {
					temp = values[i-1];
					values[i] = temp;
					i = i-1;	
					}
			}	
			values[pos] = newInt;
				
		}
	
	public static int[] zip(int[] array1, int[] array2) {
		int totalLength = array1.length + array2.length;
		int[] zipped =  new int[totalLength];
		int i = 0;
		int d = 0;
		
		while (i < totalLength){
			zipped[i] = array1[d];
			zipped[i+1] = array2[d];
			d = d+1;
			i = i+2;
			
		}
		return zipped;
		
	}
	
}
