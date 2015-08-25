public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        // YOUR CODE HERE
    	myValues = new int[capacity];
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	if (myCount >= myValues.length) {
    		System.err.println("Array is full!");
    		System.exit(1);
    	} else {
    		myValues[myCount] = toBeAdded;
    		myCount++;
    	}
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
    	if (insertPos >= myCount || insertPos < 0) {
    		System.err.println("Insert position out of bounds.");
    		System.exit(1);
    	}
		for (int k = size(); k > insertPos; k--) {
			myValues[k] = myValues[k - 1];
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
    	if (pos >= myCount || pos < 0) {
    		System.err.println("elementAt position out of bounds.");
    		System.exit(1);
    		return 1;
    	} else {
    		return myValues[pos];
    	}
    }
    
    public String toString() {
    	String contents = "" + myValues[0];
    	for (int k = 1; k < size(); k++) {
    		contents = contents + " " + myValues[k];
    	}
    	return contents;
    }
    
	public int remove(int pos) {
		if (pos >= myCount || pos < 0) {
    		System.err.println("Removal position out of bounds.");
    		System.exit(1);
    		return 1;
		}
		int returnVal = myValues[pos];
		for (int k = pos; k < size() - 1; k++) {
			myValues[k] = myValues[k + 1];
		}
		myCount--;
		return returnVal;
		}
	
	public boolean contains(int val) {
		for (int k = 0; k < size(); k++) {
			if (myValues[k] == val) {
				return true;
			}
		}
		return false;
	}
}

