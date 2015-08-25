public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		else {
			for (int i = pos; i < values.length; i++) {
				if (i == values.length-1) {
					values[i] = 0;
					break;
				}
				values[i] = values[i + 1];
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
		else {
			for (int i = values.length - 1; i > pos; i--) {
				if (i == 0) {
					break;
				}
				values[i] = values[i - 1];
			}
			values[pos] = newInt;
		}
	}
	
	public static int[] zip (int[] array1, int[] array2) {
		int[] result = new int[array1.length + array2.length];
		for (int pos1 = 0, pos2 = 0, posr = 0; posr < result.length; posr++) {
			if (posr%2 == 0) {
				result[posr] = array1[pos1];
				pos1++;
			}
			else {
				result[posr] = array2[pos2];
				pos2++;
			}
		}
		return result;
		
	}
}