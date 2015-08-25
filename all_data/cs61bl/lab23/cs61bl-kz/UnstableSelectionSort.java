import java.util.Arrays;

public class UnstableSelectionSort {

	public static void selectionSort(Comparable[] arr) {
//		for (int j = arr.length - 1; j > 0; j--) {
 		for (int j = 0; j < arr.length; j++) {
			int latestPos = 0;
//			for (int k = 1; k <= j; k++) {
			for (int k = arr.length; k > j; k--) {
				if (arr[latestPos].compareTo(arr[k]) == -1) {
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