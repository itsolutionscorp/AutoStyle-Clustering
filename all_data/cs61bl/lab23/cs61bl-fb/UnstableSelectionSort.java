public class UnstableSelectionSort {

	public static void selectionSort(Comparable[] arr) {
		Comparable min;
		for (int j = arr.length - 1; j > 0; j--) {
			int latestPos = 0;
			for (int k = 1; k <= j; k++) {
				if (arr[latestPos].compareTo(arr[k]) == -1 ) {
					latestPos = k;
				}
			}
			if (j != latestPos) { 
				Comparable temp = arr[j];
				Comparable[] newX = new Comparable[arr.length];
				for (int k=0; k<newX.length; k++) {
					if (k<j){
						newX[k] = arr[k];
					}else if(k>latestPos) {
						newX[k] = arr[k];
					}else if(j<k && k<latestPos){
						newX[k] = arr[k-1];
					}else if (k==j) {
						newX[k] = arr[latestPos];
					}
				}
				arr = newX;
				
			}
		}
	}
}
