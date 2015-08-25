public class InsertionSort {

	// Precondition: elements 0 through k-1 of list are in increasing order.
	// Postcondition: elements 0 through k of list are in increasing order.
	public static void insert (int list[], int k) {
		int temp1 = -1;
		int temp2 = -1;
		if (k != 0) {
			if (list[k] < list[k-1]) {
				//don't insert unless you need to
				for (int i = 0; i < k; i ++) {
					if (list[k] < list[i]) {
						temp1 = list[i];
						list[i] = list[k];

						for (int h = i + 1; h <= k; h ++) {
							temp2 = list[h];
							list[h] = temp1;
							temp1 = temp2;
						}
					}
				}
			}
		}

	}

	// Does nothing when the first k elements of LIST are sorted in
	// increasing order.
	// Throws an IllegalStateException otherwise.
	public static void isOK (int[] list, int k) {
		int lastsmallest = list[0];
		for (int i = 1; i <= k; i++) {
			if (list[i] < lastsmallest){
				throw new IllegalStateException("not sorted properly");
			}
			else {
				lastsmallest = list[i];
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

	public static void main (String[] args) {
		int[] list = {3, 1, 7, 4, 5, 9, 2, 8, 6};
		list = insertionSort(list);
		for (int k = 0; k < list.length; k++) {
			System.out.print(list[k]);
		}
		System.out.println();
	}

}
