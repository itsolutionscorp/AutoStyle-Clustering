public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		int counter = pos;
		while (counter < values.length - 1) {
			values[counter] = values[counter + 1];
			counter++;
		}
		values[counter] = 0;
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		int counter = values.length - 1;
		while (counter > pos) {
			values[counter] = values[counter - 1];
			counter--;
		}
		values[pos] = newInt;
		
	}
	
	public static int [] zip (int [] array1, int [] array2) {
		int createdArrayLength = array1.length + array2.length;
		int [] createdArray = new int [createdArrayLength];
		for (int counter = 0; counter < createdArray.length; counter++) {
			if (counter % 2 == 0) {
				createdArray[counter] = array1[counter / 2];
			} else {
				createdArray[counter] = array2[counter / 2];
			}
		}
		return createdArray;
		
	}
}
