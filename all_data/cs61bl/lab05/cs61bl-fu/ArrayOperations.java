public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (values == null || values.length == 0) { return; }
        if (pos < 0 || pos >= values.length) {
			return;
		}
        for (int i = pos; i < values.length - 1; i ++) {
            values[i] = values[i+1];
        }
        values[values.length-1] = 0;
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (values == null || values.length == 0) { return; }
        if (pos < 0 || pos >= values.length) {
			return;
		}
        for (int i = values.length - 1; i > pos; i --) {
            values[i] = values[i-1];
        }
        values[pos] = newInt;
	}
    
    // Returns new int[] twice as long as the arguments, such that
    // the elements of each are interleaved.
    public static int[] zip (int[] array1, int[] array2) {
        if (array1 == null || array1.length == 0) { return array2; }
        if (array2 == null || array2.length == 0) { return array1; }
        int[] toReturn = new int[2*Math.min(array1.length,array2.length)];
        for (int i = 0; i < Math.min(array1.length, array2.length); i ++) {
            toReturn[2*i] = array1[i];
            toReturn[2*i+1] = array2[i];
        }
        return toReturn;
    }
}
