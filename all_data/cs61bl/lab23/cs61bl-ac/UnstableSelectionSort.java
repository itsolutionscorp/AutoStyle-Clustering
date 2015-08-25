import java.util.ArrayList;
import java.util.Arrays;

public class UnstableSelectionSort {

	public static void selectionSort(Comparable[] arr) {
		for (int j = arr.length - 1; j > 0; j--) {
			int latestPos = 0;
			
			for (int k = 1; k <= j; k++) {
				if (arr[latestPos].compareTo(arr[k]) <= 0 ) {
					latestPos = k;// find the smallest location
				}
			}
			// change it with the smallest location 
			if (j != latestPos) {
				Comparable temp = arr[j];
				arr[j] = arr[latestPos];
				arr[latestPos] = temp;
			}
		}
	}
//

}
