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
				Comparable temp = arr[j];
				arr[j] = arr[latestPos];
				for(int i=latestPos;i<j;i++){
					arr[i] = arr[i+1];
					
				}
				//arr[j] = arr[latestPos];
				//arr[latestPos] = temp;
				arr[j-1] = temp;
			}
		}
	}
	
	/*private static int randomDigit() {
		return (int) (10 * Math.random());
	}
	
	public static void main(String[] args) {
		Integer[] arr1 = new Integer[20];
		for (int i = 0; i < arr1.length; i++) {
			arr1[i] = new Integer(randomDigit());
		}
		System.out.println("Original array: " + Arrays.toString(arr1));
		selectionSort(arr1);
		if (arr1 != null) {
			System.out.println("Should be sorted: " + Arrays.toString(arr1));
		}
	}*/
}
