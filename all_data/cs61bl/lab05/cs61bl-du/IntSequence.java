public class IntSequence {

    // instance variables
    private int[] myValues;   // sequence elements
    private int myCount;                // number of array cells used by sequence

    @Override
    public String toString() {
    	String result = new String();
    	for (int i = 0; i < this.myCount; i++) {
    		result = result + " " + this.myValues[i];
    	}
    	return result.trim();
    }
    
    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        myValues = new int[capacity];
        myCount = 0;
    }

    public boolean isEmpty() {
    	if (myCount == 0) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public int size() {
    	return this.myCount;
    }
    
    public int elementAt (int pos) {
    	if (pos > this.myCount - 1) {
    		System.err.println ("Index out of bounds.");
    		System.exit(1);
    	}
    	return this.myValues[pos];
    }
    
    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
    	if (this.myCount == this.myValues.length) {
    		System.err.println("Sequence capacity reached.");
    		System.exit(1);
    	}
        this.myValues[this.myCount] = toBeAdded;
        this.myCount += 1;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
    	myCount++;
        for (int i = this.myCount - 1; i > insertPos; i--) {
            myValues[i] = myValues[i-1];
        }
        myValues[insertPos] = toInsert;
    }
    
	public void remove (int pos) {
		for (int i = pos; i < this.myCount - 1; i++) {
			myValues[i] = myValues[i + 1];
		}
		this.myCount -= 1;
	}
    
    public boolean contains(int k) {
    	for (int i = 0; i <= this.myCount - 1; i++) {
    		if (this.myValues[i] == k) {
    			return true;
    		}
    	}
    	return false;
    }

}

