public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    private int myCount;                // number of array cells used by sequence

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
    		System.err.println("Index out of range");
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
    		System.err.println("Index out of range");
    		System.exit(1);
    	}
    	for (int i = myValues.length - 1; i > insertPos; i -= 1) {
			myValues[i] = myValues[i-1];
		}
		myValues[insertPos] = toInsert;
		myCount += 1;
    }

    // other methods go here
    
    public int remove(int pos) {
    	if (pos < 0 || pos >= myCount) {
    		System.err.println("Index out of range");
    		System.exit(1);
    		return 0;
		}
    	int toBeRemoved = myValues[pos];
    	for (int i = pos; i < myCount - 1; i += 1) {
		myValues[i] = myValues[i+1];
    	}
    	myValues[myCount-1] = 0;
    	myCount -= 1;
    	return toBeRemoved;
    }
    
    
    public boolean isEmpty() {
    	return (myCount == 0);
    }
    
    public int size() {
    	return myCount;
    }
    
    public int elementAt(int pos) {
    	if (pos < 0 || pos >= myCount) {
    		System.err.println("Index out of range");
    		System.exit(1);
    		return 0;
    	}
    	return myValues[pos];
    }
    
    public String toString() {
    	if (myCount > 0) {
    		String myVals = "";
    		for (int i = 0; i < myCount; i += 1) {
    			if (i == myCount - 1) {
    				myVals += myValues[i];
    			} else {
    				myVals += myValues[i] + " ";
    			}
    		}
    		return myVals;		
    	}
    	return "";
    }
    
    public boolean contains(int k) {
    	for (int i = 0; i < myCount; i += 1) {
    		if (myValues[i] == k) {
    			return true;
    		}
    	}
    	return false;
    }
}

