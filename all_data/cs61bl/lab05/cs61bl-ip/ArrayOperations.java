public class ArrayOperations {
	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		
		for (int i = 0; i< values.length -1 ; i++) {	
			if (i < pos) {
				continue;
			} else {
				values[i] = values[i+1];			
			}
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
		
		for (int i=values.length-1; i>0; i--) {
			if (i > pos) {
				values[i] = values[i-1];
			} else if (i < pos) {
				continue;
			}
		}
		values[pos] = newInt;
	}
	
	/*That is, result[0] is array1[0], result[1] is array2[0], 
	 * result[2] is array1[1], and so on. The zip method 
	 * should not change its arguments.
	 */
	public static int[] zip(int[] array1, int[] array2) {
		int[] zipResult = new int[array1.length + array2.length];
		for (int i=0; i<array1.length; i++) {
			zipResult[i*2] = array1[i];
			zipResult[i*2+1] = array2[i];
		}
		return zipResult;
	}
}
