public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence (size, actually)
    
    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) { // takes in argument which = max 
        myValues = new int[capacity]; // instantiation of new array and assignment to pointer "myValues" 
        myCount = 0; 
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
    	if (myCount < myValues.length) { 
    		myValues[myCount] = toBeAdded; 
    		++myCount; 
    		return;
    	}
    	System.err.println("Invalid argument: no more space left in array."); 
    	System.exit(1); 
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
		if (insertPos >= 0 && insertPos <= myCount) { // can insert
			for (int i = myCount; i > insertPos; --i) {
				myValues[i] = myValues[i - 1];
			}
			myValues[insertPos] = toInsert;
			++myCount; // don't forget to update instance variable!
			return;
		}
    	System.err.println("Invalid argument: no more space left in array, or insertPos out of range."); 
    	System.exit(1); 
    }

    // other methods go here
    
    // returns true when this sequence is empty and returns false otherwise
    public boolean isEmpty() {
    	if (myCount == 0) {
    		return true;
    	}
    	return false; 
    }
    
    // returns the number of values in this sequence 
    public int size() {
    	return myCount; 
    }
    
    // returns the value at the given position in the sequence
    // Note: If someone asks for the elementAt an index that does not exist,
    // you should call System.err.println and include a description of the error 
    // and call System.exit(1) to exit the method. 
    // The same is true for any case where a method is called with incorrect input.
    public int elementAt(int pos) {
    	if (pos >= 0 && pos <= myCount - 1) { // length is 6, but index of last element is 5 
    		return myValues[pos]; 
    	}
    	System.err.println("Invalid argument: pos out of range. Index does not exist."); 
    	System.exit(1); 
    	return -1;
    }
    
	// given the position of the sequence element to remove as an argument,
    // removes the specified element and returns it
    public int remove (int pos) {
		if (pos >= 0 && pos < myCount) { // can remove within this range 
	    	int victim = myValues[pos];
			for (int i = pos; i < myCount - 1; ++i) {
				myValues[i] = myValues[i + 1];
			}
			myValues[myCount - 1] = 0;
			--myCount;
			return victim;
		}
    	System.err.println("Invalid argument: pos out of range. Cannot remove the element"); 
    	System.exit(1); 
    	return -1;
	}
    
    // contains returns true if k is one of the elements of this sequence,
    // and returns false otherwise
    public boolean contains(int k) {
    	for (int i = 0; i < myCount; ++i) {
    		if (myValues[i] == k) {
    			return true;
    		}
    	}
    	return false;
    }
    
	// return a String that contains the elements of the sequence separated by blanks. 
    // Please make sure that there is just one space between each element,
    // and no trailing spaces on either side of the string.
	@Override
	public String toString() {
		String result = new String();
		for (int i = 0; i < myCount - 1; ++i) {
			result = result.concat(myValues[i] + " ");
		}
		result = result.concat(myValues[myCount -1] + ""); // last element (without space) 
		return result; 
	} 
	

    
}
