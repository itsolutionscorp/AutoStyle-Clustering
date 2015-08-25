public class InsertionSort {
    
    // Precondition: elements 0 through k-1 of list are in increasing order.
    // Postcondition: elements 0 through k of list are in increasing order.
    public static void insert (int list[], int k) {
    //	System.out.println("x");
    	int index = -1;
    	int num = list[k];
    	for(int i = 0; i < k; i++) {
    		//System.out.println(i + " 1");
    		if (num<= list[i]) {
    			index = i;
    		}
    	}

    	if(index == -1) return;    	
    	for(int i = k - 1; i >= index; i--) {
    	//	System.out.println(i + " 2");
    		list[k] = list[k - 1];
    	}

    	list[index] = k;
        
    	
    }
    
    // Does nothing when the first k elements of LIST are sorted in
    // increasing order.
    // Throws an IllegalStateException otherwise.
    public static void isOK (int[] list, int k) {

        for (int j=0; j<k; j++){
        	if(list[j]>list[j+1]){
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
