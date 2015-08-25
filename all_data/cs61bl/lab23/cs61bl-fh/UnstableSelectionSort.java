public class UnstableSelectionSort {

	public static void selectionSort(Comparable[] arr) {
		for (int j = 0; j < arr.length; j++) {
			int latestPos = j;
			for (int k = arr.length - 1; k >= j; k--) {
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

	public static void main(String[] args) {
		Thing[] arr = { new Thing(1, "a"), new Thing(2, "b"),
				new Thing(1, "c"), new Thing(0, "d"), new Thing(2, "e") };
		System.out.println(arr);
		for (Thing thing : arr) {
			System.out.print(thing + " ");
		}
		System.out.println();
		selectionSort(arr);
		for (Thing thing : arr) {
			System.out.print(thing + " ");
		}

	}

}
