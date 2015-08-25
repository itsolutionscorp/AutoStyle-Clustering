public class UnstableSelectionSort {

	public static void selectionSort(Comparable[] arr) {
		for (int j = arr.length - 1; j > 0; j--) {
			int latestPos = 0;
			for (int k = 1; k <= j; k++) {
				if (arr[latestPos].compareTo(arr[k]) == -1) {
					latestPos = k;
				}
			}
//			System.out.println("latestPos : " + latestPos);
//			System.out.println("j : " + j);
//			System.out.println(arr[latestPos]);
			if (j != latestPos) {
				Comparable temp = arr[j];
				arr[j] = arr[latestPos];
				arr[latestPos] = temp;
				int idx = j;
				while (idx < arr.length - 2) {
					if (arr[idx].compareTo(arr[idx+1]) == 0) {
						Comparable t = arr[idx];
						arr[idx] = arr[idx + 1];
						arr[idx+1] = t;
					}
					idx++;
				}
			}
		}
	}
	
	

	public static void main(String[] args) {
		Comparable[] test = new Comparable[]{9, 7, 3, 9, 9, 4, 5, 7, 9, 2, 3, 5};
		selectionSort(test);
		for (Comparable i : test) {
			System.out.print(i + " ");
		}
	}
}
