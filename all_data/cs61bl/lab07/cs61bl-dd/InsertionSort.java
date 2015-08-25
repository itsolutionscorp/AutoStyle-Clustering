import java.util.Arrays;

public class InsertionSort {
    
    // Precondition: elements 0 through k-1 of list are in increasing order.
    // Postcondition: elements 0 through k of list are in increasing order.
    public static void insert (int list[], int k) {

        // YOUR CODE HERE
    	int temp = 0;
    	for(int i = 0; i < k; i++){
    		if(list[k] < list[i]){
    			temp = list[i];
    	    	list[i] = list[k]; 
    	    	list[k] = temp;
    		}
    	}
    }
    
    // Does nothing when the first k elements of LIST are sorted in
    // increasing order.
    // Throws an IllegalStateException otherwise.
    public static void isOK (int[] list, int k) {

        // YOUR CODE HERE
    	int greatest = -999;
    	for(int i = 0; i < k; i++){
    		if(list[i] < greatest){
    			throw new IllegalStateException();
    		}
    		greatest = list[i];
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
        
        //test
        int[] test = {0, 1, 2, 3, 4, 6, 7, 5};
        InsertionSort.insert(test, 7);
        for(int i = 0; i < test.length; i++){
        	System.out.print(test[i]);
        
    }}

}
