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
    	if (myCount == myValues.length) {
    		System.err.println("");
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
    //
    /*public void insert(int toInsert, int insertPos) {
        for (int k = insertPos + 1; k <= myCount; k++) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }*/

    // other methods go here
    public boolean isEmpty() {
    	return myCount == 0;
    }
    
    public int size() {
    	return myCount;
    }
    
    public int elementAt (int pos) {
    	if (pos > myCount) {
    		System.err.println();
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    
    public String toString () {
    	String rv = new String("");
    	for (int k = 0; k < myCount - 1; k++) {
    		rv += myValues[k] + " ";
    	}
    	return rv + myValues[myCount - 1];
    }
    public int remove (int pos) {
		if (pos < 0 || pos >= myValues.length) {
			System.err.println();
			System.exit(1);
		}
		int rv = myValues[pos];
		for (int k = pos; k<myValues.length; k++) {
			if (k == myValues.length-1) {
				myValues[k] = 0;
				break;
			}
			myValues[k] = myValues[k+1];
		}
		myCount--;
		return rv;
	}
    public void insert (int newInt, int pos) {
    	if (pos < 0 || pos >= myValues.length) {
			System.err.println();
			System.exit(1);
		}
    	int end = myValues[myCount - 1];
    	for (int k = myCount - 1; k >= pos; k--) {
			if (k == pos) {
				myValues[k] = newInt;
			} else {
			myValues[k] = myValues[k-1];
			}
		}
    	myValues[myCount] = end;
    	myCount++;
    	}
    public boolean contains(int num) {
    	for (int k = 0; k < myCount; k++) {
    		if (myValues[k] == num) {
    			return true;
    		}
    	}
    	return false;	
    }
}

