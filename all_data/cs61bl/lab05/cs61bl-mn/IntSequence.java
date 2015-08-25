public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        this.myValues = new int[capacity];
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
    	if (this.myCount == this.myValues.length) {
    		System.err.println("Maximum capacity for set has been reached");
    		System.exit(1);
    		return;
    	}
        this.myValues[myCount] = toBeAdded;
        this.myCount++;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
    	if (insertPos < 0 || insertPos >= this.myCount) {
    		System.err.println("Index Out of Bound");
    		System.exit(1);
    	}
        for (int k = this.myValues.length-1; k > insertPos; k--) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        if (this.myCount != this.myValues.length) {
        	myCount++;
        }
    }

    // other methods go here
    
    public boolean isEmpty() {
    	return this.myCount == 0;
    }
    public int size() {
    	return this.myCount;
    }
    
    public int elementAt(int pos) {
    	if (pos<0 || pos >= this.myCount) {
    		System.err.println("Index Out of Bound");
    		System.exit(1);
    	}
    	return this.myValues[pos];
    }
    
    public void remove(int pos) {
    	if (pos<0 || pos >= this.myCount) {
    		System.err.println("Index Out of Bound");
    		System.exit(1);
    		return;
    	}
    	for (int i=pos;i<this.myCount-1;i++) {
    		this.myValues[i] = this.myValues[i+1];
    	}
    	this.myCount--;
    }
    
    public boolean contains(int k) {
    	for (int i=0; i<this.myCount; i++) {
    		if (this.myValues[i] == k) {
    			return true;
    		} else {
    			continue;
    		}
    	}
    	return false;
    }
    
    public String toString() {
    	String result = "";
    	for (int i=0;i<this.myCount-1;i++) {
    		result += this.myValues[i] + " ";
    	}
    	result += this.myValues[this.myCount-1] + "";
    	return result;
    }
}

