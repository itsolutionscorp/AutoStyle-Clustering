public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        // YOUR CODE HERE
    	this.myValues = new int[capacity];
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	myValues[myCount] = toBeAdded;
    	myCount++;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    /**
     * public void insert(int toInsert, int insertPos) {
     * for (int k = insertPos + 1; k <= myCount; k++) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
        }
     */
    public void insert(int pos, int newInt) {
		if (pos < 0 || pos >= myValues.length) {
			return;
		}
		// YOUR CODE HERE
		myCount++;
		for (int i =  myCount; i > pos; i--) {
			myValues[i] = myValues[i-1];
		}
		myValues[pos] = newInt;
	}

    // other methods go here
	public boolean isEmpty() {
		if (myCount == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public int size() {
		return myCount;
	}
	
	public int elementAt(int pos) {
		return myValues[pos];
	}
	
	public String toString() {
		String s = String.valueOf(myValues[0]);
		for (int i = 1; i < myCount; i++) {
			s = s + " " + String.valueOf(myValues[i]);
		}
		return s;
	}
	
	public void remove(int pos) {
		for (int i = pos; i < myCount; i++) {
			myValues[i] = myValues[i+1];
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
