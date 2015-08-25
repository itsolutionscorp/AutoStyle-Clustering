public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    private int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        // YOUR CODE HERE
    	myValues = new int[capacity];
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	if (myCount == myValues.length) {
    		System.err.println("Array is full.");
    		System.exit(1);
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
    	this.add(0);
        for (int k = myValues.length - 1; k > insertPos; k--) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
    }

    // other methods go here
    public boolean isEmpty(){
    	if (myCount == 0) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public int size(){
    	return myCount;    	
    }
    
    public int elementAt(int pos) {
    	if (pos > myCount - 1 || pos < 0){
    		System.err.println("Index out of bounds.");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    
    public String toString() {
    	if (myCount == 0) {
    		System.err.println("Array is empty.");
    		System.exit(1);
    	}
    	String s = new String();
    	for (int i = 0; i < myCount; i++) {
    		if (i == myCount - 1) {
    			s += myValues[i];
    			return s;
    		}
    		s += myValues[i] + " ";	
    	}
    	System.out.print(s);
    	return s;
    }
    
    public int remove(int intPos) {
    	if (intPos < 0 || intPos >= myValues.length) {
    		System.err.println("Can't remove from that position.");
    		System.exit(1);
    	}
    	int removed = myValues[intPos];
    	for (int i = intPos; i < myValues.length - 1; i++) {
    		myValues[i] = myValues[i+1];
    	}
    	myValues[myValues.length - 1] = 0;
    	myCount --;
    	return removed;    	
    }
    
    public boolean contains(int k) {
    	boolean found = false;
    	for (int i = 0; i < myValues.length; i++) {
    		if (myValues[i] == k) {
    			found = true;
        		break;
    		}	
    	}
    	return found;
    }
    
   

}

