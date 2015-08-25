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
        if (myCount == myValues.length) {
        	System.err.println("Array is full");
        	System.exit(1);
        }
        else {
        	myValues[myCount] = toBeAdded;
        	myCount++;
        }
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert (int newInt, int pos) {
		if (pos < 0 || pos >= myValues.length) {
			return;
		}
		int nextInt;
		for (int k = pos; k < myValues.length; k++) {
			nextInt = myValues[k];
			myValues[k] = newInt;
			newInt = nextInt;
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
    		System.err.println("Index does not exist");
    		System.exit(1);
    		return 0;
    	}
    	else {
    		return myValues[pos];
    	}
    }
    
    public String toString() {
    	String myString = "";
    	for (int i = 0; i < myCount; i++) {
    		if (i == myCount-1) {
    			myString += myValues[i];
    		}
    		else {
    			myString += myValues[i] + " ";
    		}
    	}
    	return myString;
    }
    
    public void remove (int pos) {
		if (pos < 0 || pos >= myValues.length) {
			return;
		}
		for (int k = pos; k < myValues.length; k++) {
			if (k == myValues.length - 1) {
				myValues[k] = 0;
			}
			else {
				myValues[k] = myValues[k+1];
			}
		}
		myCount--;
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

