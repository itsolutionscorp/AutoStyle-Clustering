public class IntSequence {

    // instance variables
	// change to public for testing the constructor
    public int[] myValues;   // sequence elements
    public int myCount;        // number of array cells used by sequence
    public int myCapacity;
    
    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        // YOUR CODE HERE
    	myValues = new int[capacity];
    	myCount = 0;
    	myCapacity = capacity;
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	if (myCount == myCapacity) {
    		System.err.println("Array is full. Sorry.");
    		System.exit(1);
    	}
    	myValues[myCount] = toBeAdded;
    	myCount++;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
    	int nextValue = myValues[0];
        for (int k = 0; k <= myCount; k++) {
        	if (k == insertPos) {
        		myValues[k] = toInsert;
        		toInsert = nextValue;
        		insertPos++;
        	}
        	nextValue = myValues[k+1];
        }
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
    	if (pos >= myCount) {
    		System.err.println("No value at that index.");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    
    public String toString() {
    	String sequenceStr = new String();
    	sequenceStr = myValues[0] + "";
    	for (int i = 1; i < myCount; i++) {
    		sequenceStr = sequenceStr + " " + myValues[i];
    	}
    	return sequenceStr;
    }
    
    public void remove(int pos) {
    	if (pos < 0 || pos >= myCount) {
    		System.err.println("Index doesn't exist.");
    		System.exit(1);
    	}
    	for (int i = 0; i < myCount; i++) {
    		int store = myValues[i+1];
    		if (i == pos) {
    			myValues[i] = store;
    			pos++;
    		}
    	}
    	myCount--;
    }
    
    public boolean contains(int k) {
    	boolean has_k = false;
    	for (int i = 0; i <= myCount; i++) {
    		if (myValues[i] == k) {
    			has_k = true;
    		}
    	}
    	return has_k;
    }
    
}




