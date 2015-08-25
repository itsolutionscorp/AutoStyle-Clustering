public class InsertionSort {
    
    // Precondition: elements 0 through k-1 of list are in increasing order.
    // Postcondition: elements 0 through k of list are in increasing order.
    public static void insert (int list[], int k) {
    	int value = list[k];
    	for (int t = k - 1; t >= 0; t --) {
    		if (value < list[t]) {
    			list[t + 1] = list[t];
    			list[t] = value;
    			continue;
     		}
    		break;
    	}


    }
    
    // Does nothing when the first k elements of LIST are sorted in
    // increasing order.
    // Throws an IllegalStateException otherwise.
    public static void isOK (int[] list, int k) {
    	if (k > list.length - 1) {
    		throw new IllegalStateException("k is too large!");
    	}
    	if (k < 0) {
    		throw new IllegalStateException("k cannot be negative!");
    	}
    	int prev = -1;
    	for (int t = 0; t <= k; t++ ) {
    		if (t == 0) {
    			prev = t;
    			continue;
    		} else {
    			if (prev > list[t]) {
    	    		throw new IllegalStateException("List is not in increasing order!");
    			}
    			prev = list[t];
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
        
        int[] arr = {1, 5, 4, 6, 8};
        
        isOK(arr, 3);
    }
    
}
