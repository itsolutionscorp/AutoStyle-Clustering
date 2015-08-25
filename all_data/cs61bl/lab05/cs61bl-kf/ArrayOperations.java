public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		for (int i = pos; i < values.length; i++) {
			if (i == values.length - 1) {
				values[i] = 0;
				continue;
			}
			values[i] = values[i+1];					
		}
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		int temp1 = 0; //just to initialize so will compile
		int temp2 = 0;
		// YOUR CODE HERE
		for (int i = pos; i < values.length; i++) {
			if (i == pos) {
				temp1 = values[i];
				values[i] = newInt;
				continue;
			}
			temp2 = values[i];
			values[i] = temp1;
			temp1 = temp2;			
		}
	}
	public static int[] zip(int[] values1, int[] values2) {
		int[] result = new int[values1.length + values2.length];
		boolean check = true;
		int k = 0;
		int h = 0;
		for (int i = 0; i < result.length; i++) {
			if (check) {
				result[i] = values1[k];
				k ++;
				check = false;
			} else {
				result[i] = values2[h];
				h ++;
				check = true;
			}			
		}
		return result;
	}                                                            
}
