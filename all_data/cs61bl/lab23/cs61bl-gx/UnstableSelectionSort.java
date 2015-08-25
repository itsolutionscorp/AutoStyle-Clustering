import java.util.Arrays;

public class UnstableSelectionSort {

	public static void selectionSort(Comparable[] arr) {
		for (int j = arr.length - 1; j > 0; j--) {
			int latestPos = 0;
			for (int k = 1; k <= j; k++) {
				if (arr[latestPos].compareTo(arr[k]) == -1) {
					latestPos = k;
				}
			}
			if (j != latestPos) {
				Comparable temp = arr[latestPos];
				for (int i = latestPos; i < j; i++) {
					arr[i] = arr[i + 1];
				}
				arr[j] = temp;
			}
		}
	}
	public static void main(String[] args) {
		Comparable[] arr1 = new Comparable[]{5, 6, 7, 8, 4, 3, 2, 3};
		selectionSort(arr1);
		System.out.println(Arrays.toString(arr1));
	}
}