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
    
    public boolean isEmpty() {
    	if (myCount == 0) {
    		return true;
    	}
    	return false;
    }
    
    public int size() {
    	return myCount;
    }
    
    public int elementAt(int pos) {
    	if (pos < 0 || pos > myValues.length - 1) {
    		System.err.println("The index is invalid :(");
    		System.exit(1);
    	}
    	return this.myValues[pos];
    }
    

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
    	if (myCount == myValues.length) {
    		System.err.println("Array is full.");
    		System.exit(1);
    	}
    	myValues[myCount] = toBeAdded;
    	myCount++;
    }
    
	public int remove(int pos) {
		int result = myValues[pos];
		if (pos < 0 || pos >= myCount) {
			System.err.println("Out of bounds");
			System.exit(1);
		}
		for (int k = pos; k <= myCount - 1; k++) {
			myValues[k] = myValues[k + 1];
		}
		myValues[myCount - 1] = 0;
		myCount--;
		return result;
	}
    
    public String toString() {
    	String result = new String();
    	int n = 0;
    	for (int k = 0; k < myCount; k++) {
    		if (n == 0) { // don't judge
    			result = result + myValues[k];
    			n++;
    		}
    		else {
    			result = result + " " + myValues[k];
    		}
    	}
    	return result;
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
    public boolean contains(int k) {
    	boolean hasInt = false;
    	for (int index = 0; index < myCount; index++) {
    		if (myValues[index] == k) {
    			hasInt = true;
    		}
    	}
    	return hasInt;
    }
}

