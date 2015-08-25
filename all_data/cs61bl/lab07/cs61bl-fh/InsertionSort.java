import java.util.Arrays;

public class InsertionSort {
    
    // Precondition: elements 0 through k-1 of list are in increasing order.
    // Postcondition: elements 0 through k of list are in increasing order.
    public static void insert (int[] list, int k) {

        // YOUR CODE HERE

    	for (int index = 0; index <= k; index++) {
    		if (index == 0) {
    	    	if (list[k] <= list[0]) {
    	    		insertAt (list, 0, k);
    	    		break;
    	    	}
    		} else if (index == k) {
    			break;    
    		} else if (list[index - 1] <= list[k] && list[k] <= list [index]) {
    			insertAt (list, index, k);
    			break;
                           //Keeps element indexed k where it is.
    		}
    	}
    }
    
   public static void insertAt (int[] list, int to, int from) { //helper method that inserts parameter X at index k
	   int[] temp = new int[list.length];
	   /*for (int index = 0; index < to; index++) {
		   temp[index] = list[index];
	   } */
	   for (int index = 0; index < list.length; index++) {
		   temp[index] = list[index];
	   }
	   list[to] = temp[from];
	   

	   int indexTo = 0;
	   int indexFrom = 0;
	   while (indexTo <= (list.length - 1)) {
		   if (indexTo == to) {
			   indexTo++;
		   }
		   if (indexFrom == from) {
			   indexFrom++;
		   }
		   
		   list[indexTo] = temp[indexFrom];
		   
		   indexTo++;
		   indexFrom++;
	   } 
	   
	   /*for (int index = to + 1; index <= (list.length - 1); index++) {  // list.length is one more than the largest index of list, which is the largest index of temp.
		   temp[index] = list[index - 1];
	   }
	   
	   for (int index = from; index < list.length; index++) {
		   temp[index] = list[index];
	   } */
   }
    
    // Does nothing when the first k elements of LIST are sorted in
    // increasing order.
    // Throws an IllegalStateException otherwise.
    public static void isOK (int[] list, int k) {

        if (k >= list.length || k < 0) {
        	throw new IllegalStateException("K is outside the list index");
        }
    	for (int index = 0; index < k; index++) {
    		if (list[index] > list[index + 1]) {
    			throw new IllegalStateException("Elements are not in increasing order");
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

