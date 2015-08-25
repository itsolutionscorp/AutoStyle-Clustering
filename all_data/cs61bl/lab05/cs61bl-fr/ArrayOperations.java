public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for (int k = pos; k<values.length; k++) {
			if (k == values.length-1) {
				values[k] = 0;
				break;
			}
			values[k] = values[k+1];
		}
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for (int k = values.length-1; k >= pos; k--) {
			if (k == pos) {
				values[k] = newInt;
			} else {
			values[k] = values[k-1];
			}
		}
	}
	public static int[] zip (int[] array1, int[] array2) {
		int[] myArray = new int[array1.length + array2.length];
		for (int i=0,  j=1,  pos=0; pos<array1.length; pos+=1) {
			myArray[i] = array1[pos];
			myArray[j] = array2[pos];
			i+=2;
			j+=2;
		}
		return myArray;
	}
}
