public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for (;pos!=values.length-1;pos++){
		values[pos] = values[pos+1]; 
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
		int k = values.length-1;
		for (;k!=pos;k--){
			values[k] = values[k-1]; 
 		}
		values[pos] = newInt;
	}
	
	public static int[] zip (int[] array1, int[] array2) {
		int i;
		int j=0;
		int result[] = new int[array1.length*2];
		for (i=0; i != array2.length; i++,j=j+2){
			result[j] = array1[i];
			result[j+1] = array2[i];
		}
		return result;
	}
}
