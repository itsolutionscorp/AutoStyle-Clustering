public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		
		
		
		for(int i=pos; i<values.length-1; i++){
			values[i] = values[i+1];
		}
		
		values[values.length-1] = 0;
		
		
		
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		
		for(int i=values.length-1; i>pos; i--){
			
			values[i] = values[i-1];
		}
		
		values[pos] = newInt;
		
	}
    // int [] array12;
	public static int[] zip(int[] array1, int[] array2) {
	
		int []	array12  =new int[ array1.length +  array2.length];
		int i=0;
		
		for(int k=0; k<array1.length; k++){
			array12[i] = array1[k];
			array12[i+1] = array2[k];
			i = i+2;
		}
		 
		return array12;
	}	
		
}


