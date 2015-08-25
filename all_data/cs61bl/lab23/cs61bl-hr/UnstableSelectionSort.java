public class UnstableSelectionSort {

	public static void selectionSort(Comparable[] arr) {
		for (int j = 0; j < arr.length - 1; j++) {
			int latestPos = j;
			for (int k = j + 1; k <= arr.length - 1; k++) {
				if (arr[latestPos].compareTo(arr[k]) == 1) {
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
