public class UnstableSelectionSort {
	public static void main(String[] args) {
		String[] one = new String[]{"a", "b", "a", "c", "a", "b"};
		selectionSort(one);
		for (int i = 0; i < one.length; i += 1) {
			System.out.print(one[i]);
		}
	}

	public static void selectionSort(Comparable[] arr) {
		for (int j = arr.length - 1; j > 0; j--) {
			int latestPos = 0;
			for (int k = 1; k <= j; k++) {
				if (arr[latestPos].compareTo(arr[k]) <= 0) {
					latestPos = k;
				}
			}
			if (j != latestPos) {
				Comparable temp = arr[j];
				arr[j] = arr[latestPos];
				arr[latestPos] = temp;
			}
		}
	}
}
