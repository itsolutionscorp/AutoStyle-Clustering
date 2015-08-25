public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		int x;
		for (int i = pos; i < values.length; i++){
			if (i+1 == values.length){
				values[i] = 0;
				break;
			}
			x = values [i+1];
			values[i] = x;
		}
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		int x = values[pos];
		values[pos] = newInt;
		for (int i = pos; i < values.length; i++){
			if (i+1 == values.length){
				break;
			}
			int temp = values[i+1];
			values [i+1] = x;
			x = temp;
		}
	}
	
	public static int[] zip (int[] array1, int[] array2) {
		int length1 = array1.length;
		int length2 = array2.length;
		int new_length = length1 + length2;
		int[] result = new int [new_length];
		boolean first = true;
		int array1_counter = 0;
		int array2_counter = 0;
		
		for (int i = 0; i < new_length; i ++){
			if (first){
				result[i] = array1[array1_counter];
				first = false;
				array1_counter++;
			} else {
				result [i] = array2[array2_counter];
				first = true;
				array2_counter++;		
			}
		}
		return result; 
	}
}
