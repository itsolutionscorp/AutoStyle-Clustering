public class InsertionSort {
    
    // Precondition: elements 0 through k-1 of list are in increasing order.
    // Postcondition: elements 0 through k of list are in increasing order.
    public static void insert (int list[], int k) {
    	for (int i = 0; i < k; i++) {
    		if (list[i] > list[k]) {
    			int holder = list[i];
    			int holder2;
    			list[i] = list[k];
    			for (int j = i+1; j < k+1; j++) {
    				holder2 = list[j];
    				list[j] = holder;
    				holder = holder2;
    			}
    			break;
    			
    		}
    	}

    }
    
    // Does nothing when the first k elements of LIST are sorted in
    // increasing order.
    // Throws an IllegalStateException otherwise.
    public static void isOK (int[] list, int k) {
    	if (k >= list.length || k < 0) {
    		throw new IllegalStateException("Error - not in range!");
    	} else {
    		for (int i = 1; i < k; i++){
    			if (list[i] < list[i-1]) {
    				throw new IllegalStateException("Error - not in order!");
    			}
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
