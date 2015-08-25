public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for (int k=pos;k<values.length-1;k++) {
			values[k]=values[k+1];
		}
		values[values.length-1]=0;
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for (int k=values.length-1;k>pos;k--) {
			values[k]=values[k-1];
		}
		values[pos]=newInt;
	}
	
	
	public static int[] zip (int[] array1, int[] array2) {
		if (array1.length!=array2.length) {
			return new int[0];
		}
		int[] bigarray= new int[array1.length*2];
		for (int k=0; k<array1.length; k++) {
			bigarray[2*k]=array1[k];
			bigarray[2*k+1]=array2[k];
		}
		return bigarray;
	}
}