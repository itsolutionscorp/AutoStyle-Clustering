import java.util.Arrays;

public class UnstableSelectionSort {

	public static void selectionSort(Comparable[] arr) {

		for (int j = 0; j < arr.length; j++) {
			int latestPos = arr.length-1;
			for (int k = arr.length-2; k >= j; k--) {
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
	
	private static int randomDigit() {
		return (int) (10 * Math.random());
	}
	
	public static void main(String[] args){
		Comparable[] arr1 = new Comparable[20];
		for (int i = 0; i < arr1.length; i++) {
			arr1[i] = randomDigit();
		}
		System.out.println("Original array: " + Arrays.toString(arr1));
		selectionSort(arr1);
		if (arr1 != null) {
			System.out.println("Should be sorted: " + Arrays.toString(arr1));
		}

			
	}
}
