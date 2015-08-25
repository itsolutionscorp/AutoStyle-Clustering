public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		int i = pos;
		while (i < values.length - 1) {
			values[i] = values[i+1];
			i++;
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
		// YOUR CODE HERE
		int i = values.length - 1;
		while (i > pos) {
			values[i] = values[i-1];
			i--;
		}
		values[pos] = newInt;
	}
	
	public static int[] zip (int[] a, int[] b) {
		int len = a.length + b.length;
		int[] c = new int[len];
		int i = 0;
		int j = 0;
		while (i < len) {
			c[i] = a[j];
			i++;
			c[i] = b[j];
			i++;
			j++;
		}
		return c;
	}
}
