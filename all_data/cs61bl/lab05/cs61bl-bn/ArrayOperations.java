import java.util.Arrays;

public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void remove(int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		
		else {
			int[] ref = new int[values.length];
			for (int j = 0; j < values.length; j++) {
				ref[j] = values[j];
			}
			
			for (int k = 0; k < values.length; k++) {
				if (k < pos) {
					values[k] = ref[k];
				}else if (k == values.length-1){
					values[k] = 0;
				}
				else {
					values[k] = ref[k+1];
				} 
			}
		}
	}

	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert(int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		else {
			int[] ref = new int[values.length];
			for (int j = 0; j < values.length; j++) {
				ref[j] = values[j];
			}
			
			for (int k = 0; k < values.length; k++) {
				if (k > pos) {
					values[k] = ref[k - 1];
				} else if (k == pos) {
					values[k] = newInt;
				} else {
					values[k] = values[k];
				}
			}
		}
	}

	public static void main(String[] args) {
	    int[] array1 = {1, 2, 3};
	    int[] array2 = {4, 5, 6};
		System.out.print(Arrays.toString(ArrayOperations.zip(array1, array2)));
	}
	
	public static int[] zip(int[] array1, int[] array2 ) {
		int[] zipped = new int[array1.length + array2.length];
		int acc = 0;
		for (int k = 0; k <array2.length; k++) {
				zipped[acc] = array1[k];
				acc++;
				zipped[acc] = array2[k];
				acc++;
			}
		return zipped;
	}
}
