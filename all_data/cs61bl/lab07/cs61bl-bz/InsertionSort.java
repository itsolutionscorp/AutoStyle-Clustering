public class InsertionSort {

	// Precondition: elements 0 through k-1 of list are in increasing order.
	// Postcondition: elements 0 through k of list are in increasing order.
	public static void insert(int list[], int k) {

		// YOUR CODE HERE
		int temp = 0;
		int i = k;

		while (i > 0) {
			if (list[i] < list[i - 1]) {
				temp = list[i];
				list[i] = list[i - 1];
				list[i - 1] = temp;
			}

			i--;
		}
	}

	// Does nothing when the first k elements of LIST are sorted in
	// increasing order.
	// Throws an IllegalStateException otherwise.
	public static void isOK(int[] list, int k) {

		// YOUR CODE HERE
		if (k > list.length - 1 || k < 0) {
			throw new ArrayIndexOutOfBoundsException(
					"Please enter a valid index");
		}
		for (int i = 0; i < k; i++) {
			if (list[i] > list[i + 1]) {
				throw new IllegalStateException(
						"Elements are not in increasing order");

			}
		}
	}

	public static int[] insertionSort(int[] list) {
		int[] rtn = new int[list.length];
		for (int k = 0; k < list.length; k++) {
			rtn[k] = list[k];
		}
		for (int k = 0; k < rtn.length; k++) {
			insert(rtn, k);
			try {
				isOK(rtn, k);
			} catch (IllegalStateException e) {
				System.err.println("inconsistency at position " + k);
			}
		}
		return rtn;
	}

	public static void main(String[] args) {
		int[] list = { 3, 1, 7, 4, 5, 9, 2, 8, 6 };
		list = insertionSort(list);
		for (int k = 0; k < list.length; k++) {
			System.out.print(list[k]);
		}
		System.out.println();
	}

}
