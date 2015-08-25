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
    
    public boolean isEmpty() {
    	return (myCount == 0);
    }
    
    public int size() {
    	return myCount;
    }
    
    public int elementAt(int pos) {
    	if ((pos >= 0) && (pos < myCount)) {
    		return (myValues[pos]);
    	}
    	else {
    		System.err.println("Index is out of bounds.");
    		System.exit(1);
    		return 0;
    	}
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	if (myCount < myValues.length) {
    		myValues[myCount] = toBeAdded;
        	myCount++;
    	}
    	else {
    		System.err.println("myValues is full.");
    		System.exit(1);
    	}
    	
    }
    
    public String toString() {
    	String myString = "";
    	if (myCount > 0) {
    		for (int k = 0; k < myCount - 1; k++) {
        		myString = myString + myValues[k] + " ";
        	}
        	myString = myString + myValues[myCount-1];
    	}
    	else {
    		myString = null;
    	}
    	
    	return myString;
    }
    
	public int remove (int pos) {
		
		int removed;
		if (pos < 0 || pos >= myCount) {
			System.err.println("Index out of bounds.");
			System.exit(1);
		}
		
		removed = myValues[pos];
		for(int k = pos; k < myCount-1; k++) {
			myValues[k] = myValues[k+1];
		}
		
		myCount--;
		return removed;
	}

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
    	int[] temp = new int[myCount];
    	
    	for (int k = 0; k < myCount; k++) {
			temp[k] = myValues[k];
		}
    	
        for (int k = insertPos + 1; k <= myCount; k++) {
            myValues[k] = temp[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }

    public boolean contains (int k) {
    	boolean doesContain = false;
    	for (int i = 0; i < myCount; i++) {
    		if (myValues[i] == k) {
    			doesContain = true;
    		}
    	}
    	return doesContain;
    	
    }
    // other methods go here

}

