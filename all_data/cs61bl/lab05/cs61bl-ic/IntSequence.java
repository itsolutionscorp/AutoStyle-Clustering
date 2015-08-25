public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        myValues = new int[capacity];
        myCount = 0;
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
    if (myValues.length == myCount) {
    	System.err.println("Sequence array is full");
		System.exit(1);
    } else {
        myValues[myCount] = toBeAdded;
        myCount++;
    }
    }
    
    public boolean isEmpty() {
    	return myCount == 0;
    }
    
    public int size() {
    	return myCount;
    }
    
    public String toString() {
    	String result = "";
    	for (int i = 0; i < myCount; i++) {
    		result += myValues[i];
    		if (i != (myCount - 1)) {
    			result += " ";
    		}
    	}
    	return result;
    }
    
    public int elementAt(int pos) {
    	if (pos < myCount && pos >= 0) {
    		return myValues[pos];
    	} else {
    		System.err.println("Value is either negative or greater than initialized values");
    		System.exit(1);
    		return -1;
    	}
    }
    
    
    public void remove(int pos) {
		if (pos < 0 || pos >= myCount) {
			return;
		}
		for (int i = 0; i < myCount; i++) {
			if (i < pos) {
				continue;
			} else if (i+1 == myCount) {
				myValues[i] = 0;
			} else {
				myValues[i] = myValues[i+1];
			}
		}
		myCount--;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
		if (insertPos < 0 || insertPos > myCount) {
			return;
		}
		for (int i = myCount; i >= 0; i--) {
			if (i < insertPos) {
				break;
			} else if (i == insertPos) {
				myValues[i] = toInsert;
			} else {
				myValues[i] = myValues[i-1];
			}
		}
		myCount++;
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

