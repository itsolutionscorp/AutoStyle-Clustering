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
        	System.err.println("No more open spots in the array.");
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
        this.add(myValues[myCount - 1]);
    	for (int k = myCount - 1; k > insertPos; k--) {
        	myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
    }

    public boolean isEmpty() {
    	if (myCount == 0) {
    		return true;
    	} return false;
    }
//returns true when this sequence is empty and returns false otherwise

    public int size() {
    	return myCount;
    }
//returns the number of values in this sequence
//Note: There is a distinction between size and capacity.

    public int elementAt(int pos) {
    	if (pos >= myValues.length || pos < 0) {
    		System.err.println("Index does not exist.");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
//returns the value at the given position in the sequence
//e.g. If the sequence contains 3, 1, and 4, elementAt(0) returns 3.
//    Note: If someone asks for the elementAt an index that does not exist, you should call
//    System.err.println and include a description of
//    the error and call System.exit(1) to exit the method.
//    The same is true for any case where a method is called with incorrect input.\

	public int remove (int pos) {
		if (pos < 0 || pos >= myValues.length) {
			System.err.println("Index does not exist.");
			System.exit(1);
		}
		if (pos >= myCount) {
			return 0;
		}
		else {
			int returnValue = myValues[pos];
			for (int i = pos; i < myCount; i++) {
				if (pos == myCount - 1) {
					myValues[i] = 0;
					break;
				}
				myValues[i] = myValues[i + 1];
				}
			myCount--;
			return returnValue;
		}
	}
    
	public boolean contains (int k) {
		for (int i = 0; i < myCount; i++) {
			if (myValues[i] == k) {
				return true;
			}
		} return false;
	}
	
	public String toString() {
		String s = new String("");
		for (int i = 0; i < myCount; i++) {
			if (i == myCount - 1) {
				s = s + myValues[i];
				break;
			}
			s = s + myValues[i] + " ";
		}
		return s;
	}
}

