public class UnstableSelectionSort {

	public static void selectionSort(Comparable[] arr) {
		for (int j = 0; j < arr.length - 1; j++) {
			while (arr[j].compareTo(arr[j + 1]) == 1) {
				Comparable temp = arr[j];
				arr[j] = arr[j + 1];
				arr[j + 1] = temp;
				j--;
				if (j < 0) {
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		NumberedWord nw1 = new NumberedWord(1, "hi");
		NumberedWord nw2 = new NumberedWord(2, "hello");
		NumberedWord nw3 = new NumberedWord(2, "bye");
		NumberedWord nw4 = new NumberedWord(3, "later");
		NumberedWord nw5 = new NumberedWord(4, "greetings");
		NumberedWord nw6 = new NumberedWord(1, "farewell");
		Comparable[] arr1 = new Comparable[6];
		arr1[0] = nw1;
		arr1[1] = nw3;
		arr1[2] = nw2;
		arr1[3] = nw4;
		arr1[4] = nw5;
		arr1[5] = nw6;
		System.out.println(arr1[0].toString() + arr1[1].toString() + arr1[2].toString() + arr1[3].toString() + arr1[4].toString() + arr1[5].toString());
		UnstableSelectionSort s = new UnstableSelectionSort();
		s.selectionSort(arr1);
		System.out.println(arr1[0].toString() + arr1[1].toString() + arr1[2].toString() + arr1[3].toString() + arr1[4].toString() + arr1[5].toString());
	}
}