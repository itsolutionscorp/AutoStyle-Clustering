public class InsertionSort {
    
    // Precondition: elements 0 through k-1 of list are in increasing order.
    // Postcondition: elements 0 through k of list are in increasing order.
    public static void insert (int list[], int k) {
        // YOUR CODE HERE
    	int count = 0;
    	int tmp;
    	for (int i = 0; i < k; i++){
    		if (list[k] > list[i]){
    			count++;
    		}
    	}
    	for (int i = count; i < k; i++){
    		tmp = list[i];
    		list[i] = list[k];
    		list[k] = tmp; 
    	}
    		
    	}

    
    
    // Does nothing when the first k elements of LIST are sorted in
    // increasing order.
    // Throws an IllegalStateException otherwise.
    public static void isOK (int[] list, int k) throws IllegalStateException  {

        // YOUR CODE HERE

		for (int i = 1; i < k; i++){
		    		if(list[i-1] > list[i]){
		    			throw new IllegalStateException();
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
        int[] list = {2, 5, 4, 5, 1, 9, 1, 7, 8, 0};
        list = insertionSort(list);
        for (int k = 0; k < list.length; k++) {
            System.out.print(list[k]);
        }
        System.out.println();
    }

}
