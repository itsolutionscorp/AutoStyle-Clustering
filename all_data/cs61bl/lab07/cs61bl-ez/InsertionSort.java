public class InsertionSort {

	// Precondition: elements 0 through k-1 of list are in increasing order.
	// Postcondition: elements 0 through k of list are in increasing order.
	public static void insert(int list[], int k) {

		int counter = 0;

		while (k > counter) {
			if (list[k] < list[k - 1]) {
				int temp = list[k - 1];
				list[k - 1] = list[k];
				list[k] = temp;
			}
			k--;
		}
	}

	// Does nothing when the first k elements of LIST are sorted in
	// increasing order.
	// Throws an IllegalStateException otherwise.
	public static void isOK(int[] list, int k) {

		int counter = 0;
		if (k < 0 || k > list.length) {
			throw new IllegalStateException("Illegal State Exception!");
		}

		while (counter < k - 1) {
			if (list[counter] < list[counter + 1]) {
				counter++;
			} else {
				throw new IllegalStateException("Illegal State Exception!");
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
