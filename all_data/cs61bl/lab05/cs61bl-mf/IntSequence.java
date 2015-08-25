public class IntSequence {

    // instance variables
    private int[] myValues;   // sequence elements
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
    	if (myCount < myValues.length) {
    		myValues[myCount] = toBeAdded;
    		myCount++;
    	} else {
    		System.err.println("List is full");
    		System.exit(1);
    	}
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
    	if (insertPos < 0) {
			System.err.println("Invalid position.");
			System.exit(1);
    	}
		for (int index = this.size(); index > insertPos; index--) {
			myValues[index] = myValues[index - 1];
		}
		myValues[insertPos] = toInsert;
		myCount ++;
    }
    public boolean isEmpty() {
    	return myCount == 0;
    }
    
    public int size() {
    	return myCount;
    }
    
    public int elementAt(int pos) {
    	if (pos >= myCount) {
    		System.err.println("Position index does not exist.");
    		System.exit(1);
    		return 0;
    	} else {
    		return myValues[pos];
    	}
    }
    
    public String toString() {
    	String returnString = "";
    	for (int item = 0; item < myCount - 1; item++) {
    		returnString += myValues[item] ;
    		returnString += " ";
    	}
    	returnString += myValues[this.size() - 1];
    	return returnString;
    }
    
    public int remove(int pos) {
    	if (this.isEmpty()) {
    		System.err.println("The sequence is empty.");
    		System.exit(1);
    		return 0;
		} else if (pos >= myCount || pos < 0) {
			System.err.println("The specificed sequence index does not exist.");
			return 0;
		} else {
			int returnValue = myValues[pos];
			for (int index = pos; index < (myValues.length - 1); index++) {
				myValues[index] = myValues[index + 1];
		}
		myValues[(this.size() - 1)] = 0;
		myCount --;
		return returnValue;
		}
    }
    
    public boolean contains(int k) {
    	for(int item = 0; item < this.size(); item++) {
    		if (item == k) {
    			return true;
    		}
    	} return false;
    }
}


