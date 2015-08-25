public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return; 
		}
		for (int i = pos; i < values.length - 1; ++i) {
			values[i] = values[i + 1];
		}
		values[values.length - 1] = 0;
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for (int i = values.length - 1; i > pos; --i) {
			values[i] = values[i - 1];
		}
		values[pos] = newInt;
	}
	
	// Given two int arrays array1 and array2 of the same length, 
	// zip should return an array result that is twice as long, 
	// in which the elements of array1 and array2 are interleaved.
	public static int[] zip (int[] arr1, int[] arr2) {
		int[] result = new int[arr1.length + arr2.length];
		int a = 0;
		for (int i = 0; i < arr1.length; ++i) {
			result[a] = arr1[i];
			a++;
			result[a] = arr2[i]; 
			a++; 
		}
		return result; 
	}
}