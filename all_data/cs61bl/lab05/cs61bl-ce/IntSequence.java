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
    
    public boolean isEmpty() {
    	//return true when sequence is empty, false otherwise
    	if (this.myCount == 0){
    		return true;
    	} else 
    		return false;
    }
    
    public int size() {
    	//number of values in the sequence
    	return this.myCount;
    }
    
    public int elementAt(int pos) {
    	if (pos > myCount -1) {
    		System.err.println("Outside scope of array");
    		//System.exit(1);
    		return -1;
    	}
		return myValues[pos];
    }
    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	if (myCount == myValues.length) {
    		System.err.println("No more availabe spots in array cells");
    		//System.exit(1);
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
        for (int k = insertPos + 1; k <= myCount; k++) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }
    // other methods go here
    public String toString() {
    	String myString = "";
    	for (int k = 0; k < myValues.length - 2; k ++) {
    		myString = myString + myValues[k] + " "; 
    	}
    	myString = myString + myValues[myValues.length-1];
    	return myString;
    }
    
    public IntSequence remove (int badNumber) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		{for (int i = 0; i < values.length - 1; i++)
			if (i < pos) {
				continue;
			} else 
			values[i]= values[i+1];
		}
		values[values.length - 1]= 0;
	}

}

