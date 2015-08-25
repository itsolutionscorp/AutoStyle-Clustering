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
    	if (myCount > myValues.length) {
    		System.err.println("There are no more open spots in the array.");
    		System.exit(1);
    	}
    	myValues[myCount] = toBeAdded;
    	myCount++;
    }
    
    public int remove(int pos) {
    	int removed = myValues[pos];
    	for (int i = pos; i < myCount; i++) {
    		if (i == myCount - 1) {
    			myValues[i] = 0;
    		} else {
    			myValues[i] = myValues[i+1];
    		}
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
    	if (insertPos <= myCount && insertPos >= 0) {
    		for (int i = myValues.length-1; insertPos <= i; i--) {
    			if (i == insertPos) {
    				myValues[i] = toInsert;
    			} else {
    				myValues[i] = myValues[i-1];
    			}
    		}
    		myCount++;
    	}
    }

    // other methods go here
    public boolean isEmpty() {
    	//returns true when sequence is empty, return false otherwise
    	if (myCount == 0) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public int size() {
    	//returns number of values in sequence
    	return myCount;
    }
    
    public int elementAt(int pos) {
    	//returns value at given position in sequence
    	if (pos < 0 || pos > myValues.length) {
    		System.err.println("Element at this index does not exit.");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    
    public String toString() {
    	String s = " ";
    	for (int i = 0; i < myCount; i++) {
    		s += myValues[i] + " ";
    	}
    	s = s.trim();
    	return s;
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

