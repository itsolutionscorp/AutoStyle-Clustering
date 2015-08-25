import java.util.*;
public class UnstableSelectionSort {

	public static void selectionSort(Comparable[] arr) {
		ArrayList<Comparable> helper = new ArrayList<Comparable>(Arrays.asList(arr));
		for (int j = arr.length - 1; j > 0; j--) {
			int latestPos = 0;
			for (int k = 1; k <= j; k++) {
				if (arr[latestPos].compareTo(arr[k]) == -1) {
					latestPos = k;
				}
			}
			helper.add(0, arr[latestPos]);
//			if (j != latestPos) {
//				Comparable temp = arr[j];
//				arr[j] = arr[latestPos];
//				arr[latestPos] = temp;
//			}
		}
	}
}
