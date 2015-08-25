public class InsertionSort {
    
    // Precondition: elements 0 through k-1 of list are in increasing order.
    // Postcondition: elements 0 through k of list are in increasing order.
	
	public static void insert (int list[], int k) {
    		int current = list[k];
    		int pos = 0;
    		
    		for (int i = 0; i < k; i++) {
    			if (list[i] <= current && list[i + 1] >= current) {
    				pos = i + 1;
    				continue;
    			}
    		}
    		if (pos != k) {
    			int temp = current;
    			
    			int store = 666;
    			for (int m = pos; m <= k; m++) {
				if (m == k) {
					list[m] = temp;
				} else {
					store = list[m];
					list[m] = temp;
					temp = store;
				}
    				
    			}
    			
    		}

    }
    
    // Does nothing when the first k elements of LIST are sorted in
    // increasing order.
    // Throws an IllegalStateException otherwise.
    public static void isOK (int[] list, int k) {
    		for (int i = 0; i < k; i++) {
    			if (list[i] > list[i + 1]) {
    				throw new IllegalStateException("list up to kth index isn't sorted");
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
