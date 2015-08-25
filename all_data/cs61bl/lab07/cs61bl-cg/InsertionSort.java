import java.util.Arrays;

public class InsertionSort {
    
    // Precondition: elements 0 through k-1 of list are in increasing order.
    // Postcondition: elements 0 through k of list are in increasing order.
    public static void insert (int list[], int k) {
    	int temp = -1;
    	int iterstop = -1;
    	int temp2;
    	System.out.println("k: "+ k);
    	if (list[k] != k){
    		temp = list[k];
    		list[k] = k;
	    	for (int i = k + 1; i < list.length; i++){
	    		temp2 = list[i];
	    		list[i] = temp;
	    		temp = temp2;
    	}
	    System.out.println(Arrays.toString(list));
    	}
    }
    
    // Does nothing when the first k elements of LIST are sorted in
    // increasing order.
    // Throws an IllegalStateException otherwise.
    public static void isOK (int[] list, int k) {
    	int max = -1;
    	for (int i = 0; i < k ; i++){
    		if(list[i] > max){
    			max = list[i];
    		}
    		else{
    			throw new IllegalStateException("Inconsistency at position " + i);
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
