public class InsertionSort {
    
    // Precondition: elements 0 through k-1 of list are in increasing order.
    // Postcondition: elements 0 through k of list are in increasing order.
    public static void insert (int list[], int k) {
    	for (int i = k; i > 0; i--) {
    		for (int j = k; j > 0; j--) {
    			if (list[j] < list[j-1]){
    			int temp = list[j-1];
    			list[j-1] = list[j];
    			list[j] = temp;
    			}
    		}
    	}
    }

    public static void isOK (int[] list, int k) {
    	for (int i = k; i > 0; i-- ) {
    		if (list[i] < list[i-1]) {
    			throw new IllegalStateException("Index " + k + "is out of order");
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
