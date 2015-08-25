public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        // YOUR CODE HERE
    	this.myCount = 0;
    	this.myValues = new int[capacity];
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	if (this.myCount == this.myValues.length) {
    		System.err.println("Error Size has met capacity");
    		System.exit(1);
    	}
    	this.myValues[myCount] = toBeAdded;
    	this.myCount++;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
	public void insert (int newInt, int pos) {
		if (pos < 0 || pos > this.myCount || pos == this.myValues.length) {
    		System.err.println("Error Position in sequence does not exist");
    		System.exit(1);
		}
		for (int k = this.myCount; k > pos; k--) {
			if (k == this.myValues.length) {
		        this. myCount--;
			} else {
				this.myValues[k] = this.myValues[k-1];
			}
		}
		this.myValues[pos] = newInt;
        this. myCount++;
    }

    // other methods go here
    public void remove ( int pos) {
		if (pos < 0 || pos >= this.myCount) {
    		System.err.println("Error Position in sequence  does not exist");
    		System.exit(1);
		}
		for (int k = pos; k < this.myCount-1; k++) {
			this.myValues[k] = this.myValues[k+1];
		}
		this.myCount--;
	}
    
    public boolean contains(int k) {
    	for (int i = 0; i < this.myCount; i++) {
    		if (this.myValues[i] == k) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public boolean isEmpty() {
    	return this.myCount == 0;
    }
    
    public int size() {
    	return this.myCount;
    }
    
    public int elementAt(int pos) { 
    	if (pos >= this.myCount || pos < 0) { 
    		System.err.println("Error Position in sequence does not exist");
    		System.exit(1);
    	}
    	return this.myValues[pos];
    }
    
    public String toString() {
    	String seqString = "";
    	for (int k = 0 ; k < this.myCount; k++) {
    		if (k != this.myCount-1) {
    			seqString = seqString+this.myValues[k] + " ";
    		} else {
    			seqString = seqString + this.myValues[k];
    		}
    	}
    	return seqString;
    }
}

