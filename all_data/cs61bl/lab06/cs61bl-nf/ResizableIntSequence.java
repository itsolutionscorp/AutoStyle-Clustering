public class ResizableIntSequence extends IntSequence {
	
	final static private int TH = 3; // one-third
	
    
    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public ResizableIntSequence(int capacity) { // takes in argument which = max 
    	super(capacity);
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    @Override
    public void add(int toBeAdded) {
    	if (myCount == myValues.length) {
    		grow();
    	}
    	super.add(toBeAdded);
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    @Override
    public void insert(int toInsert, int insertPos) {
		if (insertPos >= 0 && insertPos <= myCount) { // can insert
	    	if (myCount == myValues.length) {
	    		grow();
	    	}
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
    
	// given the position of the sequence element to remove as an argument,
    // removes the specified element and returns it
    @Override
    public int remove (int pos) {
		if (pos >= 0 && pos < myCount) { // can remove within this range 
	    	int victim = myValues[pos];
			for (int i = pos; i < myCount - 1; ++i) {
				myValues[i] = myValues[i + 1];
			}
			myValues[myCount - 1] = 0;
			--myCount;
			shrink(); // shrink here!
			return victim;
		}
    	System.err.println("Invalid argument: pos out of range. Cannot remove the element"); 
    	System.exit(1); 
    	return -1;
	}

    
    
    private void grow() {
    	int[] myValues2 = new int[myValues.length * 2]; // double the size
    	for (int i = 0; i < myValues.length; i++) {
    		myValues2[i] = myValues[i];
    	}
    	myValues = myValues2;
    	// don't double myCount!
    }
    
    private void shrink() {
    	if (myCount <= myValues.length / TH) {
        	int[] myValues2 = new int[myValues.length / TH];
        	for (int i = 0; i < myCount; i++) {
        		myValues2[i] = myValues[i];
        	}
        	myValues = myValues2;
        	// don't double myCount!
    	}
    }
	

    
}
