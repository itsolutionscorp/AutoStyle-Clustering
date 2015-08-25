public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		for (; pos < values.length - 1; pos++) {
			values[pos] = values [pos + 1];
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
		for (int loc = values.length - 1; loc > pos; loc--) {
            values[loc] = values[loc - 1];
        }
        values[pos] = newInt;
	}
	
	  public static int[] zip (int[] array1, int[] array2) {
	        int total = array1.length + array2.length;
	        int[] result = new int[total];
	        int place = 0;
	        boolean increment = false;
	        for (int i = 0; i < total; i++) {
	            if (i % 2 == 0) {
	                result[i] = array1[place];
	                increment = false;
	            } else {
	                result[i] = array2[place];
	                increment = true;
	            }
	            if (increment) {
	                place++;
	            }
	        }
	        return result;
	    }
}
