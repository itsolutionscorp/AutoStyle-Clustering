public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		for(int i = pos + 1; i <= values.length; i++){
			
			if(i == values.length){
				values[values.length - 1] = 0;
				break;
			}else{
				values[i-1] = values[i];
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
		// YOUR CODE HERE
		for(int i = values.length - 1; i >= pos; i--){
			if(i == pos){
				values[pos] = newInt;
				break;
			}else{
				values[i] = values[i - 1];
			}
			
			
		}
	}
	
	public static int[] zip (int[] values1, int[] values2){
		int[] zipvalues = new int[values1.length + values2.length];
		int Counter = 0;
		for(int i = 0; i <= zipvalues.length; i += 2){
			if(i == zipvalues.length){
				break;
			}else{
				zipvalues[i] = values1[Counter];
				zipvalues[i+1] = values2[Counter];
				Counter++;
			}
			
		}
		return zipvalues;
	}
}
