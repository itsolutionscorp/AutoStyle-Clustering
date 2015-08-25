public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for (int i = pos; i<= values.length-1;i++) {
			if (i == values.length-1) {
				values[i]=0;
			} else {
				values[i]=values[i+1];
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
		for (int i = values.length-1; i>= pos;i--) {
			if (i == pos) {
				values[i] = newInt;
			} else {
				values[i] = values [i-1];
			}
		}
	}
	public static int[] zip (int[] array1, int [] array2) {
		int[] zipResult = new int[array1.length+array2.length];
		int counter = 0; 
		for (int i = 0; i < zipResult.length; i++) {
			if (i % 2 == 0){
				zipResult[i] = array1[counter];
			} else {
				zipResult[i] = array2[counter];
				counter++;
			}
		}
		return zipResult;
	}
}
