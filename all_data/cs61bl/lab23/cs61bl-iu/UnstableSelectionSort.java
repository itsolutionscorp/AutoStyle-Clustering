public class UnstableSelectionSort {

public static void selectionSort(Comparable[] arr) {
	for (int j = arr.length - 1; j > 0; j--) {
		Comparable[] repeats = new Comparable[arr.length];
		int count = 0;
		int latestPos = 0;
		for (int k = 1; k <= j; k++) {
			if (arr[latestPos].compareTo(arr[k]) == -1) {
				latestPos = k;
			} else if (arr[latestPos].compareTo(arr[k]) == 0){
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
		//tests i wrote 
		Comparable[] arr1 = new Comparable[5];
		arr1[0] = 4; 
		arr1[1] = 2;
		arr1[2] = 3;
		arr1[3] = 4;
		arr1[4] = 1;
		for(int i = 0; i < arr1.length; i++) {
			System.out.print(arr1[i] + " ");
		}
		System.out.println();
		selectionSort(arr1);
		System.out.println("after sort it should be 1 2 3 4 4");
		for(int i = 0; i < arr1.length; i++) {
			System.out.print(arr1[i] + " ");
		}
	}
}
