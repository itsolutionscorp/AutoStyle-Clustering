public class InsertionSort {
    
    // Precondition: elements 0 through k-1 of list are in increasing order.
    // Postcondition: elements 0 through k of list are in increasing order.
    public static void insert (int list[], int k) {

    	if(k == 0){
    		return;
    	}
    	int newElement = list[k];
    	int i = k;
//        while(newElement < list[i] && i>0){
//        	i--;
//        	list[i+1]=list[i];
//        	list[i] = k;
//        }
    	for(int x = k-1; x >= 0; x--){
    		if (newElement < list[x]){
    			int temp = list[x];
    			list[x] = newElement;
    			list[x+1] = temp;
    		}
    	}

    }
    
    // Does nothing when the first k elements of LIST are sorted in
    // increasing order.
    // Throws an IllegalStateException otherwise.
    public static void isOK (int[] list, int k) {

    	int lastElement;
    	int newElement;
        for(int i=0; i<k; i++){
        	lastElement = list[i];
        	newElement = list[i + 1];
        	if(lastElement < newElement){
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
        int[] list = {3, 1, 7, 4, 5, 9, 2, 8, 6};
        list = insertionSort(list);
        for (int k = 0; k < list.length; k++) {
            System.out.print(list[k]);
        }
        System.out.println();
    }

}
