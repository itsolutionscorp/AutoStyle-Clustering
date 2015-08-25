public class InsertionSort {
    
    // Precondition: elements 0 through k-1 of list are in increasing order.
    // Postcondition: elements 0 through k of list are in increasing order.
    public static void insert (int list[], int k) {
    	
        int toInsert = list[k];
    	for (int i = 0; i < k; i++) {
    		if (list[i] > toInsert) {
    			int hold = list[i];
    			list[i] = toInsert;
    			for (int j = k - 1; j > i; j--) {
    				list[j+1] = list[j];
    			}
    			list[i+1] = hold;
    			break;
    		}
    	}        

    }
    
    // Does nothing when the first k elements of LIST are sorted in
    // increasing order.
    // Throws an IllegalStateException otherwise.
    public static void isOK (int[] list, int k) {
    	int prev = list[0];
    	for(int i = 1; i < k+1; i++){
    		if (list[i] < prev){
    			throw new IllegalStateException("not in order");
    		}
    		prev = list[i];
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
