public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for (int i=0; i < values.length; i++){
			if (pos == i){ //start deletion/shift at position given
				if (i == values.length-1){ // nothing to shift when we reach the last element
					values[i]=0;
				} else {
					values[i] = values[i+1];
					pos++; //move position to continue shifting
				}
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
		for (int i = 0; i < values.length; i++ ){
			if (pos==i){
				int temp = values[i];
				values[i]=newInt;
				newInt = temp;
				pos++;
			}
		}
	}
	
	public static int[] zip(int[] array1, int[] array2){
		int newArrayLength = array1.length+array2.length;
		int[] result = new int[newArrayLength];
		for (int i=0; i < array1.length; i++){
			int zipIndex = 2*i;
			result[zipIndex]=array1[i];
			result[zipIndex+1]=array2[i];
		}
		return result;
	}
}
