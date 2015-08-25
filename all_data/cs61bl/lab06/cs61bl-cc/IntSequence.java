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
    	myCount = 0;
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	if (myCount == myValues.length) {
    		System.err.println("");
    		System.exit(1);
    	}
    	myValues[myCount] = toBeAdded;
    	myCount++;
    }
    
    public void remove(int toBeRemoved) {
    	myCount--;
    	for (int ind = toBeRemoved; ind <= myCount; ind++) {
    		myValues[ind] = myValues[ind + 1];
    	}
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
    	int cur = myValues[insertPos];
        for (int k = insertPos + 1; k <= myCount; k++) {
        	int next = myValues[k];
            myValues[k] = cur;
            cur = next;
            
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }

    // other methods go here
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
    	if (myValues[pos] > myCount) {
    		System.err.println("Value does not exist.");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    
    public String toString() {
    	String result = new String();
    	String space = " ";
    	for (int idx = 0; idx < myCount - 1; idx++) {
    		result = result + myValues[idx] + space;
    	}
    	return result + myValues[myCount-1];
    }
    
    public boolean contains(int value) {
    	for (int idx = 0; idx <= myCount - 1; idx++) {
    		if (myValues[idx] == value) {
    			return true;
    		}
    	}
    	return false;
    }

}

