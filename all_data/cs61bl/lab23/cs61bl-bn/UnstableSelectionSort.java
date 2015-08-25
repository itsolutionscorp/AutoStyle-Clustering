public class UnstableSelectionSort {

	public static void selectionSort(Comparable[] arr) {
		for (int j = arr.length - 1; j > 0; j--) {
			int latestPos = 0;
			for (int k = 1; k <= j; k++) {
				if (arr[latestPos].compareTo(arr[k]) <=0) {
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

//	private static int randomDigit() {
//		return (int) (10 * Math.random());
//	}
//
//	public static void main(String[] args) {
//		UnstableSelectionSort u = new UnstableSelectionSort();
//		String[] arr1 = new String[20];
//		for (int i = 0; i < arr1.length; i++) {
//			arr1[i] = "" + randomDigit();
//		}
//		for (int j = 0; j < 20; j++) {
//			System.out.print(arr1[j]);
//		}
//		selectionSort(arr1);
//		System.out.println();
//		for (int k = 0; k < 20; k++) {
//			System.out.print(arr1[k]);
//		}
//	}

}
