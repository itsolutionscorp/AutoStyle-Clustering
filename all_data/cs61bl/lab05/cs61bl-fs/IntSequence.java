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

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
    	if (myCount == myValues.length) {
    		System.err.println("Sequence full");
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
    public void insert(int toInsert, int pos) {
    	if (pos < 0 || pos >= myCount) {
			return;
		}
		for (int i = 0, j = 0; i <= myCount; i++) {
			if (i == pos) {
				j = myValues[i];
				myValues[i] = toInsert;
			} else if (i > pos) {
				int k;
				k = myValues[i];
				myValues[i] = j;
				j = k;
			}
		}
		myCount++;
    }

    // other methods go here
    public boolean isEmpty(){
    	return myCount == 0;
    }
    public int size(){
    	return myCount;
    }
    public int elementAt(int pos){
    	if (pos >= myCount) {
    		System.err.println("Index out of range");
    		System.exit(1);
    	}
    	return myValues[pos];
    } 
    public String toString() {
    	String result = new String();
    	if (isEmpty()) {
    		return result;
    	}
    	result = Integer.toString(myValues[0]);
    	for (int i = 1; i < myCount; i++) {
    		result = result + " " + Integer.toString((myValues[i]));
    	}
    	return result;
    }
    public void remove(int pos) {
    	
		if (pos < 0 || pos >= myCount) {
			return;
		}
		for (int i = 0; i < myCount-1; i++) {
			if (i >= pos){
				myValues[i] = myValues[i+1];
			}
		}
		myValues[myCount-1]= 0; 
		myCount--;
	}
    public boolean contains(int k) {
    	boolean result = false;
    	for (int i = 0; i < myCount; i ++) {
    		if (myValues[i] == k) {
    			result = true;
    		}
    	}
    	return result;
    }
}
