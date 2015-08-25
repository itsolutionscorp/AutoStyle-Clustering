import java.util.Arrays;

public class UnstableSelectionSort {
	//ORIGINAL UNSTABLE VERSION
	// public static void selectionSort(Comparable[] arr) {
	// for (int j = arr.length - 1; j > 0; j--) {
	// int latestPos = 0;
	// for (int k = 1; k <= j; k++) {
	// if (arr[latestPos].compareTo(arr[k]) == -1) {
	// latestPos = k;
	// }
	// }
	// if (j != latestPos) {
	// Comparable temp = arr[j];
	// arr[j] = arr[latestPos];
	// arr[latestPos] = temp;
	// }
	// System.out.println(Arrays.toString(arr));
	// }
	// }
	

	// STABLE VERSION
	public static void selectionSort(Comparable[] arr) {
		for (int j = arr.length - 1; j > 0; j--) {
			int latestPos = 0;
			for (int k = 1; k <= j; k++) {
				if (arr[latestPos].compareTo(arr[k]) != 1) {
					latestPos = k;
				}
			}
			if (j != latestPos) {
				Comparable big = arr[latestPos];
				for (int i = latestPos; i < j; i++) {
					arr[i] = arr[i + 1];
					//System.out.println(Arrays.toString(arr));
				}
				arr[j] = big;
			}
			// System.out.println(Arrays.toString(arr));
			// System.out.println("next loop");
		}
	}

	public static void main(String[] args) {
		Integer[] list = new Integer[] { 6, 3, 1, 3, 8, 4 };
		selectionSort(list);
		System.out.println(Arrays.toString(list));
	}
}
