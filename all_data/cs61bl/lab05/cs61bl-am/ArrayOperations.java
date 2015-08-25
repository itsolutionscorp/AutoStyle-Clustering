public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for(int i = pos; i < values.length-1;  i++){
			values[i]=values[i+1];
		}
		values[(values.length-1)] = 0;	
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;}
		for(int i = values.length-1; i>pos; i--){
			values[i]=values[i-1];
		}
		values[pos] = newInt;
				
	}
	public static int[] zip (int[] array1, int[] array2){
		int len = array1.length;
		int[] merge = new int[len*2];
		int k = 0;
		for(int i = 0; i< len*2; i = i+2 ){
			merge[i] = array1[k];
			k++;
		}
		k = 0;
		for(int i = 1; i< len*2; i = i+2 ){
			merge[i] = array2[k];
			k++;
		}
		return merge;
	}
}
