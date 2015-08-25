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
        for (int k = myCount; k > insertPos; k--) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }

    // other methods go here
    public boolean isEmpty() {
    	return myCount == 0;
    }
    
    public int size() {
    	return myCount;
    }
    
    public int elementAt(int pos) {
    	if (pos > myValues.length - 1) {
    		System.err.println("Error: Out of bounds.");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    
    public String toString() {
    	String conversion = "";
    	for (int count = 0; count < myCount; count++) {
    		conversion = conversion + myValues[count];
    		if (count != myCount - 1) {
    			conversion = conversion + " ";
    		}
    	}
    	return conversion;
    }
    
    public void remove(int pos) {
		for (int count = pos; count <= this.size() - 2; count++) {
			myValues[count] = myValues[count + 1];
		}
		myValues[this.size() - 1] = 0;
		myCount--;
	}
    
    public boolean contains(int k) {
    	if (this.isEmpty()) {
    		return false;
    	} else {
    		boolean found = false;
    		for (int count = 0; count < this.size(); count++) {
    			if (myValues[count] == k) {
    				found = true;
    				break;
    			}
    		}
    		return found;
    	}
    }

}

