public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        // YOUR CODE HERE
    	myValues = new int[capacity];
    	myCount = 0;
    }
    
    public int size() {
    	return this.myCount;
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	myValues[myCount] = toBeAdded;
    	myCount++;
    	if (myCount > this.size()) {
    		System.err.println("Capacity exceeded. Cannot add to IntSequence.");
    		System.exit(1);
    	}
    }
    
    public int elementAt (int pos) {
    	return myValues[pos];
    }
    
    public boolean isEmpty() {
    	return (myCount == 0);
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
    	for (int i = this.size(); i > insertPos; i--) {
    		myValues[i] = myValues[i-1];
    	}
    	myValues[insertPos] = toInsert;
    	myCount++;
    }
    
    
    public boolean contains (int k) {
    	for (int i = 0; i < this.size(); i++) {
    		if (myValues[i] == k) {
    			return true;
    		}
    	}
    	return false;
}
    
    public void remove (int pos) {
    	for (int i = pos; i < this.size(); i++) {
    		myValues[i] = myValues[i+1];
    	}
    	myCount--;
    }
    

    // other methods go here
    public String toString () {
    	String temp = new String();
    	for (int i = 0; i < this.size()-1; i++) {
    		temp += myValues[i] + " ";
    	}
    	temp += myValues[this.size()-1];
    	return temp;
    }
    
}
