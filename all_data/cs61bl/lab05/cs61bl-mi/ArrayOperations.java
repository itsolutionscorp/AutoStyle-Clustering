public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		for (int i = pos; i < values.length; i++) {
			if (i == values.length - 1){
				values[i] = 0;
			}
			else{
			values[i] = values[i+1];}
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
		int store1 = 0;
		int store2 = 0;
		for (int i =pos; i <values.length; i++) {
			if (i == pos) {
				store1 = values[i];
				store2 = values[i];
				values[i] = newInt;
			}
			else {
				store2 = values[i];
				values[i] = store1;
				
			}
			store1 = store2;

			}
		}
	
	public static int[] zip(int[] array1, int[] array2) {
		int[] resultArray = new int[2*array1.length];
		int counter = 0;
		for (int i = 0; i < array1.length; i++) {
			resultArray[counter] = array1[i];
			counter++;
			resultArray[counter] = array2[i];
			counter++;
		}
		return resultArray;
	}
	}
	
	
	

