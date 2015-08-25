public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        myValues = new int[capacity];
        myCount = 0;
    }

    // Returns true when sequence is empty and returns false otherwise
    public boolean isEmpty() {
    	return myCount == 0;
    }

    // Returns the number of values in the sequence
    public int size() {
    	return myCount;
    }
    
    // Returns the value at the given position in the sequence
    public int elementAt(int pos) {
    	if (pos < 0 || pos >= myCount){
    		System.err.println("index out of bounds");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    
	 // Add the argument to the sequence by placing it in the first
	 // unused spot in the array and incrementing the count.
	 // Assume that the sequence isn't full.
	 public void add (int toBeAdded) {
	     if (myCount == myValues.length) {
	    	 System.err.println("out of bounds");
	    	 System.exit(1);
	     } else {
	    	 myValues[myCount] = toBeAdded;
	    	 myCount++;
	     }
	 }

    // Returns a String that contains the elements of the sequence 
    // separated by blanks
    public String toString() {
    	String result = new String();
    	if (myCount > 0) {
	    	result += myValues[0];
	    	for (int k = 1; k < myCount; k++) {
	    		result += " " + myValues[k];
	    	}
    	}
    	return result;
    }
	 
    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
    	if (insertPos < 0 || insertPos > myCount){
    		System.err.println("index out of bounds");
    		System.exit(1);
    	}
    	for (int k = myCount; k > insertPos; k--) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }
    
    
    // Removes the element at position pos and returns it
    public int remove(int pos) {
    	if (pos < 0 || pos >= myCount){
    		System.err.println("index out of bounds");
    		System.exit(1);
    	}
    	int result = myValues[pos];
    	int loopNum = myCount;
    	if (myCount == myValues.length) {
    		loopNum--;
    	}
    	for (int current = pos; current < loopNum; current++) {
    		myValues[current] = myValues[current + 1];
    	}
    	myValues[loopNum] = 0;
    	myCount--;
    	return result;
    }
    
    // Returns true if k is in the sequence, return false otherwise
    public boolean contains(int k) {
    	for (int i = 0; i < myCount; i++) {
    		if (myValues[i] == k) {
    			return true;
    		}
    	}
    	return false;
    }
    
    
}

