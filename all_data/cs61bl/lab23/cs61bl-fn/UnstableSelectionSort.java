public class UnstableSelectionSort {

	public static void selectionSort(Comparable[] arr) {
		int n = arr.length;
		for (int i = 1; i < n; i++) {
			boolean swap = false;
			if (arr[i].compareTo(arr[i - 1]) == -1) {
				swap = true;
				Comparable temp = arr[i - 1];
				arr[i - 1] = arr[i];
				arr[i] = temp;
			}
			if (swap) {
				i = 0;
			}
		}
	}

	public static void main(String[] args) {
		// Pair p1 = new Pair(1, "1");
		// Pair p2 = new Pair(2, "b");
		// Pair p3 = new Pair(2, "c");
		// Pair p4 = new Pair(2, "d");
		// Pair p5 = new Pair(3, "e");
		// Pair[] arr1 = { p1, p2, p3, p4, p5 };
		// Pair[] arr2 = { p1, p4, p3, p2, p5 };
		// Pair[] arr3 = { p1, p3, p4, p2, p5 };
		// selectionSort(arr1);
		// selectionSort(arr2);
		// selectionSort(arr3);
		// for (int i = 0; i < arr1.length; i++) {
		// System.out.println("arr1" + arr1[i] + " arr2 " + arr2[i] + " arr3 "
		// + arr3[i]);
		// }
		Pair[] test = new Pair[10];
		test[0] = new Pair(2, "first 2");
		test[1] = new Pair(5, "first 5");
		test[2] = new Pair(2, "second 2");
		test[3] = new Pair(1, "first 1");
		test[4] = new Pair(1, "second 1");
		test[5] = new Pair(1, "third 1");
		test[6] = new Pair(3, "first 3");
		test[7] = new Pair(2, "third 2");
		test[8] = new Pair(4, "first 4");
		test[9] = new Pair(3, "second 3");
		UnstableSelectionSort.selectionSort(test);
		for (Pair a : test) {
			System.out.println(a.toString());
		}

	}
}
