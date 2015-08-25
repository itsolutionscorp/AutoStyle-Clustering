public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        myValues = new int[capacity];
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
    	myValues[myCount] = toBeAdded;
        myCount++;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
    	if (insertPos > myValues.length || insertPos < 0) {
    		System.err.println("Position not in sequence");
    		System.exit(1);
    	}
    	for (int k = insertPos + 1; k <= myCount; k++) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }

    // other methods go here
    
    public boolean isEmpty(){
    	if (myCount==0){
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public int size(){
    	return myCount;
    }
    
    public int elementAt(int pos){
    	if (pos > myValues.length || pos < 0) {
    		System.err.println("Position not in sequence");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    
    public String toString() {
    	String result = "";
    	for (int i=0; i<myCount;i++){
    		result += myValues[i] + " ";
    	}
    	return result.trim();
    }
    
    public int remove(int pos) {
		if (myCount <= 0) {
			System.err.println("Position not in sequence");
    		System.exit(1);
		}
		int result = myValues[pos];
		for (int i = 0; i < myValues.length; i++) {
			int next;
			if (i == pos) {
				for (int j = i; j < myValues.length; j++) {
					if (j == myValues.length-1){
						myValues[j] = 0;
						myCount--;
					} else {	
					next = myValues[j+1];
					myValues[j] = next;
					}
				}

			}
		}
		return result;
	}
    
    public boolean contains(int k) {
    	for (int i = 0; i < myCount; i++) {
    		if (myValues[i] == k) {
    			return true;
    		}
    	}
    	return false;
    } 	

}

