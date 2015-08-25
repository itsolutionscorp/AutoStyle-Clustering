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
        if (myCount == myValues.length) {
        	System.err.println("Sequence full");
        	System.exit(1);
        }
        myValues[myCount] = toBeAdded;
        myCount += 1;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
    	if (insertPos < 0 || insertPos >= myCount) {
			System.out.println("invalid index");
    		return;
		}
		for (int i = myCount; i > insertPos; i--) {
			myValues[i] = myValues[i-1];
		}
		myValues[insertPos] = toInsert;
		myCount += 1;
    }

    // other methods go here
    public boolean isEmpty() {
    	if (myCount == 0) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public int size() {
    	return myCount;
    }
    
    public int elementAt(int pos) {
    	if (pos < 0 || pos > myCount - 1) {
    		System.err.println("invalid index");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    
    public String toString() {
    	String output = new String(myValues[0] + "");
    	for (int i=1; i<myCount; i++){
    		output += new String(" " + myValues[i]);
    	}
    	return output;
    }
    
    public int remove(int pos) {
    	if (pos < 0 || pos >= myCount) {
    		System.err.println("invalid index");
			System.exit(1);
		}
    	int removed = myValues[pos];
    	for (int i = pos; i < myCount; i++) {
    		myValues[i] = myValues[i+1];
    	}
    	myCount -= 1;
    	return removed;
    }
    
    public boolean contains(int k) {
    	for (int i = 0; i < myCount; i++) {
    		if (k == myValues[i]) {
    			return true;
    		}
    	}
    	return false;
    }
    
}

