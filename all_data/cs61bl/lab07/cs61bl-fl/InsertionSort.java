public class InsertionSort {

	// Precondition: elements 0 through k-1 of list are in increasing order.
	// Postcondition: elements 0 through k of list are in increasing order.
	public static void insert (int list[], int k) {

		// YOUR CODE HERE

		//CITATION: http://www.algolist.net/Algorithms/Sorting/Insertion_sort
		int inserting = list[k];
		int i = k;
		while (i > 0 && list[i - 1] > inserting) {
			list[i] = list[i - 1];
			i--;
		}
		list[i] = inserting;




		//    	int i =0;
		//    	while (list[k]>list[i]){
		//    		i++;	// i is position where new item should be
		//    	}
		//    	int temp, temp2;
		//    	for (; i<k; i++){
		//    		temp = list[i];
		//    		temp2 = list[i+1];
		//    		
		//    	}



		//    	for (int i=0; i<k; i++){
		//    		if (list[i]>inserting){
		//    			for (; i<k; i++){
		//    				int temp = list[i];
		//    				
		//    			}
		//    		}
		//    	}
	}

	// Does nothing when the first k elements of LIST are sorted in
	// increasing order.
	// Throws an IllegalStateException otherwise.
	public static void isOK (int[] list, int k) {

		// YOUR CODE HERE
		for (int i=0; i<k; i++){
			if ((k!=0) && list[i]>list[i+1]){
				throw new IllegalStateException("Item "+i+"is greater than its following item");
			}
		}
	}

	public static int[] insertionSort(int[] list) {
		int[] rtn = new int[list.length];
		for (int k = 0; k < list.length; k++) {
			rtn[k] = list[k];
		}
		for (int k = 0; k < rtn.length; k++) {
			insert(rtn, k);
			try {
				isOK(rtn, k);
			} catch (IllegalStateException e) {
				System.err.println("inconsistency at position " + k);
			}
		}
		return rtn;
	}

	public static void main (String[] args) {
		int[] list = {3, 1, 7, 4, 5, 9, 2, 8, 6};
		list = insertionSort(list);
		for (int k = 0; k < list.length; k++) {
			System.out.print(list[k]);
		}
		System.out.println();
	}

}
