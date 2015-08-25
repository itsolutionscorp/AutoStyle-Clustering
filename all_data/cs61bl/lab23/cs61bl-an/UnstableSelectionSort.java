public class UnstableSelectionSort {

	public static void selectionSort(Comparable[] arr) {
		for (int j = arr.length - 1; j > 0; j--) {
			int latestPos = 0;
			for (int k = 1; k <= j; k++) {
				if (arr[latestPos].compareTo(arr[k]) == -1|| arr[latestPos].compareTo(arr[k]) == 0) {
					latestPos = k;
				}
				
			}
			if (j != latestPos) {
				Comparable temp = arr[j];
				Comparable temp1 = arr[latestPos];
				arr[j] = temp1;
				for(int i =latestPos;i<j-1;i++){
					arr[i] = arr[i+1];
				}
				arr[j-1]=temp;
			}
			for(int i =0;i<6;i++){
				System.out.print(arr[i].toString()+"++");}
				System.out.println("________");
		}
	}
}
