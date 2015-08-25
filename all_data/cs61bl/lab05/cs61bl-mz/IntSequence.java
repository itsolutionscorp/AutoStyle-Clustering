public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
    	this.myValues = new int[capacity];
    	this.myCount = 0;
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        this.myValues[myCount] = toBeAdded;
        myCount++;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int pos) {
		int curr = 0;
		myCount++;
		for (int i = 0; i < myCount; i++) {
			curr = myValues[i];
			if (i == pos) {
				myValues[i] = toInsert;
				break;
			}
		}
		int current = 0;
		for (int j = pos + 1; j < myCount; j ++) {
			current = myValues[j];
			myValues[j] = curr;
			curr = current;
		}
    }

    // Returns true if the sequence is empty. False otherwise.
    public boolean isEmpty() {
		if (myCount == 0) {
			return true;
		}
		return false;
    }
    
    // Returns the size of the current array, not the capacity 
    // but the amount that is used. the Number of values in the sequence.
    public int size() {
    	return myCount;
    }

    // Returns the value at the given position in the sequence.
    // e.g. If the sequence contains 3, 1, and 4, elementAt(0) returns 3.
    public int elementAt(int pos) {
    	if (pos > myCount || pos < 0) {
    		System.err.println("This is not a valid position.");
    		System.exit(1);
    	}
    	return this.myValues[pos];
    }
    
    // Returns a String with the proper format for the sequence.
    public String toString() {
    	String ret = "";
    	for (int i = 0; i < myCount; i++) {
    		if (i == myCount - 1) {
    			ret += this.myValues[i];
    		} else {
    			ret += this.myValues[i] + " ";
    		}
    	}
    	return ret;
    }
    
    // Removes the int at the given position arguement.
    // Also shift's the array down to the left.
    public void remove(int pos) {
		if (pos < 0 || pos >= myCount) {
			return;
		}
		for (int i = 0; i < myCount; i++) {
			if (i == pos) {
				for (int k = i; k+1 < myCount; k++) {
					myValues[k] = myValues[k+1];
				}
			}
		}
		myValues[myCount - 1] = 0;
		myCount--;
    }
    
    // Returns a boolean that represents where the Sequence contains that int.
    public boolean contains(int k) {
    	for (int i = 0; i < myCount; i ++) {
    		if (myValues[i] == k) {
    			return true;
    		}
    	}
    	return false;
    }
}

