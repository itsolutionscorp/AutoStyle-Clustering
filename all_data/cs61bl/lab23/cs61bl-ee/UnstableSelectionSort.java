import java.util.Arrays;

public class UnstableSelectionSort {

	public static void selectionSort(Comparable[] arr) {
		for (int j = arr.length - 1; j > 0; j--) {
			int latestPos = 0;
			for (int k = 0; k < j; k++) {
				if (arr[k].compareTo(arr[k+1]) == 1) {
					Comparable temp = arr[k];
					arr[k] = arr[k+1];
					arr[k+1] = temp;
				}
			}
		}
	}

	public static void main(String[] args){
		Comparable[] a;
		a = new Comparable[5];
		a[0] = 2;
		a[1] = 1;
		a[2] = 4;
		a[3] = 3;
		a[4] = 0;
		selectionSort(a);
		System.out.println(Arrays.toString(a));
	}
}
