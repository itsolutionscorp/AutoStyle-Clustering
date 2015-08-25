import java.util.Arrays;

public class UnstableSelectionSort {

	public static void selectionSort(Comparable[] arr) {
		for (int j = arr.length - 1; j > 0; j--) {
			int latestPos = 0;
			for (int k = j; k >= 1; k--) {
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
	
	public static void main(String[] args) {
		Integer[] arr1 = new Integer[5];
		for (int i = 0; i < arr1.length; i++) {
			arr1[i] = (int) (10 * Math.random());
		}
		System.out.println("Original array: " + Arrays.toString(arr1));
		selectionSort(arr1);
		if (arr1 != null) {
			System.out.println("Should be sorted: " + Arrays.toString(arr1));
		}
	}
}
