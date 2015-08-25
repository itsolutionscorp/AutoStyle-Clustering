public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		int []temp= new int[values.length+1];
		for (int k=0; k < values.length; k++){
			temp[k]=values[k];
			temp[values.length]=0;
		}
		for (int n=pos; n < values.length; n++){
			 
			values[n]=temp[n+1];

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
		int []temp= new int[values.length];
		for (int k=1; k < values.length; k++){
			temp[k]=values[k-1];
			temp[0]=values[0];
		}
		for (int n=pos+1; n < values.length; n++){
			 
			values[n]=temp[n];

		}
		values[pos] = newInt;
}
	
	public static int[] zip (int[] array1, int []array2) {
	    int [] values = new int[array1.length+array2.length];
//	    for (int k=0; k < array1.length; k++){
//	    	values[k]=array1[k];
//	    	ArrayOperations.insert(values, , array2[k]);
	    int k = 0;
	    while (k < array1.length+array2.length) {
	    	if (k %2 == 0) { 
	    		values[k] = array1[k/2];
	    		k++;
	        } else{
	    		values[k] = array2[(k-1)/2];
	    		k++;
	        }
	    }
		return values;	
	    }
}