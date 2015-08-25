public class InsertionSort {
    
    // Precondition: elements 0 through k-1 of list are in increasing order.
    // Postcondition: elements 0 through k of list are in increasing order.
    public static void insert (int list[], int k) {

        // YOUR CODE HERE
    	int k_num = list[k];
    	int old_num = list[0];
    	for (int i = 0; i < k+1; i++) {
    		if (list[i] > k_num) {
    			old_num = list[i];
    			list[i] = k_num;
    			list[k] = old_num;
    			k_num = old_num;
    		} else {
    			old_num = list[i];
    		}
    	}
    }
    
    // Does nothing when the first k elements of LIST are sorted in
    // increasing order.
    // Throws an IllegalStateException otherwise.
    public static void isOK (int[] list, int k) {

        // YOUR CODE HERE
    	int prev_number = list[0];
    	for (int i = 0; i <= k; i++) {
    		if (k < 0 || k > list.length) {
    			throw new IllegalArgumentException();
    		}
    		else if (list[i] < prev_number) {
    			throw new IllegalStateException();
    		}
    		else {
    			prev_number = list[i];
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
