public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		for (int k = 0; k < values.length; k+=1) {
			if (k == pos) {
				for (int x = k; x < values.length; x += 1) {
					if (x == values.length - 1) {
						values[values.length - 1] = 0;
					}	
					else {
						values[x] = values[x+1];
					}
				}
		
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
		for (int k = 0; k < values.length; k+=1) {
			if (k == pos) {
				for (int x = values.length - 1; x >= pos; x -= 1) {
					if (x == pos) {
						values[x] = newInt;
				
				    } else {
						values[x] = values[x-1];
					}
				}
		
			}
		}
	
	}


	public static int[] zip (int[] values1, int[] values2) {
		int[] arr = new int[values1.length + values2.length];
		int holder1 = 0;
		int holder2 = 0;
		for (int k = 0; k < values1.length * 2; k+=1) {
			if (k % 2 == 0) {
				arr[k] = values1[holder1];
				holder1 += 1;
			}
			else {
				arr[k] = values2[holder2];
				holder2 += 1;
			}
		}
		return arr;
}
}