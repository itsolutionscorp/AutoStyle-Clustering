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
    	this.myCount = 0;
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	if (this.myCount == this.myValues.length) {
    		System.err.println("The sequence is full already!");
    		System.exit(1);
    	}
    	this.myValues[this.myCount] = toBeAdded;
    	this.myCount++;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
//    public void insert(int toInsert, int insertPos) {
//        for (int k = insertPos + 1; k <= myCount; k++) {
//            myValues[k] = myValues[k-1];
//        }
//        myValues[insertPos] = toInsert;
//        myCount++;
//    }
    
    public void insert (int newInt, int pos) {
		if (pos < 0 || pos >= myValues.length) {
			System.err.println("Invalid Index for Inserting!");
			System.exit(1);
		}
		// YOUR CODE HERE
		boolean found = false;
		int temp = 0;
		for (int i = 0; i < myValues.length; i++) {
			if (found) {
				int currentValue = myValues[i];
				myValues[i] = temp;
				temp = currentValue;
			}
			if (i == pos) {
				found = true;
				temp = myValues[i];
				myValues[pos] = newInt;	
			}
		}
		this.myCount++;
	}

    // other methods go here
    public boolean isEmpty() {
    	return this.myCount == 0;
    }
    
    public int size() {
    	return this.myCount;
    }
    
    public int elementAt(int pos) {
    	if (pos >= this.myCount) {
    		System.err.println("No value exists at that index.");
    		System.exit(1);
    	} 
    	return this.myValues[pos];
    }
    
    public String toString() {
    	String result = "";
    	
    	for (int i = 0; i < this.myCount; i++) {
    		if (i == this.myCount-1) {
    			result += this.myValues[i];
    		} else {
    			result += this.myValues[i] + " ";
    		}	
    	}
    	
    	return result;
    }
    
    public int remove (int pos) {
		if (pos < 0 || pos >= myValues.length) {
			System.err.println("Invalid Index!");
			System.exit(1);
		}
		// YOUR CODE HERE
		int result = 0;
		boolean found = false;
		for (int i = 0; i < myValues.length; i++) {
			if (found) {
				if (i < myValues.length-1)
					myValues[i] = myValues[i+1];
				else 
					myValues[i] = 0;
				continue;
			}
			if (i == pos) {
				found = true;
				result = myValues[i];
				if (i == myValues.length-1)
					myValues[i] = 0;
				else
					myValues[i] = myValues[i+1];
			} 	
		}
		this.myCount--;
		return result;
	}
    
    public boolean contains(int val) {
    	for (int i = 0; i < this.myCount; i++) {
    		if (this.myValues[i] == val) {
    			return true;
    		}
    	}
    	return false;
    }
}

