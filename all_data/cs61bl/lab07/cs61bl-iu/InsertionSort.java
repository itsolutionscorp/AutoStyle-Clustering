public class InsertionSort {
    
    // Precondition: elements 0 through k-1 of list are in increasing order.
    // Postcondition: elements 0 through k of list are in increasing order.
    public static void insert (int list[], int k) {
    	isOK(list, k-1);
    	int[] newArr = new int[list.length];
    	int index = 0;
    	while (index < k){
    		if (k == index || list[k] <= list[index]){
	    		newArr[index] = list[k];
	    		for (int i = index + 1; i < list.length; i++){
	    			if (i <= k){
	    				newArr[i] = list[i - 1];
	    			}
	    			else if (i > k){
	    				newArr[i] = list[i];
	    			}
	    		}
	    		
	    		for (int i = 0; i < list.length; i++){
	    			list[i] = newArr[i];
	    		}
	    		break;
    		}
    		newArr[index] = list[index];
    		index++;
    	}
    }
    
    // Does nothing when the first k elements of LIST are sorted in
    // increasing order.
    // Throws an IllegalStateException otherwise.
    public static void isOK (int[] list, int k) {
    	for (int i = 0; i < k - 1; i++){
    		if (list[i] > list[i+1]){
    		throw new IllegalStateException("IllegalStateException");
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
