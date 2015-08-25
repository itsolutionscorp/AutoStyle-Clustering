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
    	if (myCount == myValues.length) {
    		System.err.println("No more open spots in sequence.");
    		System.exit(1);
    	}
        this.myValues[myCount] = toBeAdded;
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
    	if (pos > myCount - 1) {
    		System.err.println ("Index does not exist.");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    
    public String toString() {
		String result = new String(); 
		for (int i = 0; i < myCount - 1; i++) {
			result += myValues[i] + " ";
		}
		result += myValues[myCount - 1];
    	return result;
    }
    
	public int remove (int pos) {
		if (pos < 0 || pos >= myCount - 1) {
    		System.err.println ("Index does not exist.");
    		System.exit(1);
		}
		// YOUR CODE HERE
		int retVal = myValues[pos];
		for (; pos < myCount - 1; pos++) {
			myValues[pos] = myValues [pos + 1];
		}
		myCount--;
		return retVal;
	}
	
	public boolean contains (int k) {
		for (int i = 0; i < myCount ; i++) {
			if (myValues[i] == k) {
				return true;
			}
		}
		return false;
	}
    
}
