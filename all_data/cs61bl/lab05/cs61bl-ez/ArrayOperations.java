public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		
		int lastPlace = values.length - 1; 
		
		for (int k = pos; k < lastPlace; k += 1 )
		{
			values[k] = values[k+1];
			
		}
		
		values[lastPlace] = 0;
		
		
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		
		int lastPlace = values.length - 1; 
		
		for ( int k = lastPlace; pos < k ; k-=1 ){
			
			values[k] = values[k-1];
			
		}
		
		values[pos] = newInt;
		
		
	}
	
	public static int[] zip(int[] a1, int[] a2){
		
		int totalLength = a1.length + a2.length; 
		
		int[] largeArray = new int[totalLength];
		
		int i = 0; 
		
		for (int k = 0; k < totalLength; k+= 1) {
			
			largeArray[k] = a1[i];
			k+=1;
			largeArray[k] = a2[i];
			i +=1;
			
		}
		
		return largeArray;
						
		
	}
}
