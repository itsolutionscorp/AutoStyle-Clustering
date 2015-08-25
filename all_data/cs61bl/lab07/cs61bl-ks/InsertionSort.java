import java.util.Arrays;

public class InsertionSort {
    
    // Precondition: elements 0 through k-1 of list are in increasing order.
    // Postcondition: elements 0 through k of list are in increasing order.
    public static void insert (int[] list, int k) {
// [0 1 2 3 5 2 ]; 6 > 0 1 2 2 3 5
    	if (k==0){return;} // if k == 0 , then its sorted no matter what
        int numba = list[k];
        int index = k;
        if (list[0]>=numba){
        	index = -1;
        	for (int i = k; i>index+1;i--){
        		list[i]=list[i-1];
        	} 	
        } else{
        while (list[index] >= numba){
        	index-- ;	
        	}      
        for (int i = k; i > index; i--){
        	list[i] = list[i-1];}
        }
    	list[index+1] = numba;
    	System.out.println(Arrays.toString(list));
    }
    
    // Does nothing when the first k elements of LIST are sorted in
    // increasing order.
    // Throws an IllegalStateException otherwise.
    public static void isOK (int[] list, int k) {
    	if (k<0 || k> list.length) {
    		throw new IllegalStateException("invalid k value");
    	}
        for(int i=0; i < k;i++){
        	if(list[i] > list[i+1]){
        		throw new IllegalStateException("wrong order, bruh!");
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
