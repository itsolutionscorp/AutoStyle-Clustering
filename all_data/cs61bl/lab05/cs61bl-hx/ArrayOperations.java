public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		int next = 0;
		if ((pos+1)<values.length){
				next = values[pos+1];
		}
		for (int i = pos; i<values.length; i++){
			values[i]=next;
			if ((i+2)<values.length){
				next = values[i+2];
			} else {
				next = 0;
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
		int prev = values[pos];
		int next = 0;
		if ((pos+1)<values.length) {
			next = values[pos + 1];
		} 
		values[pos] = newInt;
		for(int c = pos+1; c < values.length; c++){
			values[c] = prev;
			prev = next;
			if ((c+1)<values.length){
				next = values[c+1];
			}
			
		}
		
	}
	
	public static int[] zip (int[] array1, int[] array2) {
		int[] result = new int[array1.length*2];
		for (int c = 0; c<array1.length*2; c=c+2){
			result[c]=array1[c/2];
			result[c+1]=array2[c/2];
		}
		return result;
		
	}
	
}
