public class InsertionSort {
    
    // Precondition: elements 0 through k-1 of list are in increasing order.
    // Postcondition: elements 0 through k of list are in increasing order.
    public static void insert (int list[], int k) {

        // YOUR CODE HERE
    	if(k==0){
    		
    	}
    	else{
    	isOK(list, k-1);
    	int pos = 0;
    	for(int i = 0; i<k+1;i++){
    		if (list[k]<=list[i]){
    			pos = i;
    			break;
    		}
    	}
    	int temp = 0;
    	for(int j =pos; j<k+1; j++){
    		
    		if(j == pos){
    			temp = list[pos];
    			list[pos] = list[k];
    		}
    		else{
    			int temp2 = list[j];
    			list[j] = temp;
    			temp = temp2;
    		}
    	}
    	}
    }
    
    // Does nothing when the first k elements of LIST are sorted in
    // increasing order.
    // Throws an IllegalStateException otherwise.
    public static void isOK (int[] list, int k) {

        // YOUR CODE HERE
    	if(k<0 || k>list.length){
    		throw new IllegalStateException("index out of bounds");
    	}
    	int previous = list[0];
    	for(int i =0; i<k+1;i++){
    		if (list[i]< previous){
    			throw new IllegalStateException("array out of order");
    		}
    		previous = list[i];
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
