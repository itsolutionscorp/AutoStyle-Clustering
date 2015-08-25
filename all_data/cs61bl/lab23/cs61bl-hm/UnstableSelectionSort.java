public class UnstableSelectionSort {

	public static void selectionSort(Comparable[] arr) {
		for (int j = arr.length - 1; j > 0; j--) {
			int latestPos = 0;
			for (int k = 1; k <= j; k++) {
				if (arr[latestPos].compareTo(arr[k]) <= 0) {
					latestPos = k;
				}
			}
			if (j != latestPos) {
				Comparable temp = arr[latestPos];
				for (int h = latestPos; h < j; h++) {
					arr[h] = arr[h + 1];
				}
				arr[j] = temp;
			}
		}
	}
//
//	public static void main(String args[]) {
//		Pair[] testing = new Pair[] { new Pair("6a", 6), new Pair("5a", 5), new Pair("5b", 5), new Pair("2", 2),
//				new Pair("3", 3), new Pair("8", 8),
//				new Pair("4", 4), new Pair("10", 10),
//				new Pair("6b", 6) };
//		selectionSort(testing);
//		for (Pair p : testing) {
//			System.out.println(p);
//		}
//	}
}