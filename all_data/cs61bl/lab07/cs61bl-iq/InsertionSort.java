public class InsertionSort {
    
    // Precondition: elements 0 through k-1 of list are in increasing order.
    // Postcondition: elements 0 through k of list are in increasing order.
    public static void insert (int list[], int k) {
    	
    	if (k < 0) {
    		throw new IllegalStateException("Can't search a negative k");
    	} else if (k > list.length - 1) {
    		throw new IllegalStateException("There aren't that many values in the list");
    	}
    	
    	int insertedValue = list[k];
    	for (int element = k - 1; element >= 0; element--) {
    		if (list[element] > insertedValue) {
    			list[element + 1] = list[element];
    			list[element] = insertedValue;
    		}
    	}
    	
    }
    
    // Does nothing when elements 0 through k of list are sorted in
    // increasing order.
    // Throws an IllegalStateException otherwise.
    public static void isOK (int[] list, int k) {
    	
    	if (k < 0) {
    		throw new IllegalStateException("Can't search a negative k");
    	} else if (k > list.length - 1) {
    		throw new IllegalStateException("There aren't that many values in the list");
    	}

    	for (int element = 0; element < k; element++) {
    		if (list[element] > list[element + 1]) {
    			throw new IllegalStateException("Not in order");
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
