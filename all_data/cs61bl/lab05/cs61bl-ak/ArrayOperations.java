public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for (int i = pos; i != values.length -1; i+=1){
			values[i]= values[i+1]; 
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
		for (int i= values.length -1; i != pos; i-= 1 ) {
			values[i] = values[i-1]; 
		}
		values[pos]= newInt; 
	}
	
	public static int[] zip(int[] array1, int[] array2) {
		int[] zip_array = new int[array1.length+array2.length]; 
		for (int i = 0, x=0; i < zip_array.length && x<array1.length; i += 2, x+=1) {
				zip_array[i] = array1[x];
			}
		for (int i = 1, x=0; i < zip_array.length && x<array1.length; i += 2, x+=1){
				zip_array[i] = array2[x];
			}
	return zip_array; 
	}}
		
		
		

