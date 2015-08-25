public class InsertionSort {
    
    // Precondition: elements 0 through k-1 of list are in increasing order.
    // Postcondition: elements 0 through k of list are in increasing order.
    public static void insert (int list[], int k) {
        int placeholder = 0;
        boolean find_success = false;
        int insertpos = 0;
        // YOUR CODE HERE
        if (k>0){
    	if (list[k] < list[k-1]) {
    		
    		placeholder = list[k];
    		
    		while (!find_success){
    				
    		    if(list[insertpos] > placeholder) {	
    		        find_success = true;
    		    } else {
    		    insertpos++;
    		    }
    		}
    			for(int input_index=k; input_index >insertpos ; input_index--){
    				
    				list[input_index] = list[input_index-1];
    					
    			} 
    			list[insertpos] = placeholder;		
        	
        }
        }
    }
    
    // Does nothing when the first k elements of LIST are sorted in
    // increasing order.
    // Throws an IllegalStateException otherwise.
    public static void isOK (int[] list, int k) {
        if (k<0) {
        	throw new IllegalStateException("k cannot be negative");	
        }
        if (k>list.length-1){
        	throw new IllegalStateException("k exceeded maximum length");
        }
    	
    	for (int i = 0; i<=k-1;i++){
        	if (list[i] > list[i+1]){
        		throw new IllegalStateException("List is not sorted");
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
