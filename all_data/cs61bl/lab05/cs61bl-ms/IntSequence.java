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

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        if (myCount < myValues.length){
        	myValues[myCount] = toBeAdded;
        	myCount += 1;
        } else {
    		System.err.println("Sequence Capacity reached1");
    		System.exit(1);
        }
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
    	if (insertPos < 0 || insertPos > myCount) {
    		System.err.println("Invalid Insert Position!");
    		System.exit(1);
		} else if (myCount == myValues.length){
    		System.err.println("The array is full!");
    		System.exit(1);
		}
		for (int i = myCount; i >= 0 ; i--) {
			if (i > insertPos) {
				myValues[i] = myValues[i-1];
			} else if (i == insertPos) {
				myValues[i] = toInsert;
			}
		}
		myCount += 1;
    }

    public boolean isEmpty() {
    	if (myCount == 0){
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public int size(){
    	return myCount;
    }
    
    public int capacity(){
    	return myValues.length;
    }
    
	public String toString() {
    	String a = "";
    	if (myCount == 0) {
    		return "Empty Sequence!";
    	} else {
    		for (int i = 0; i < myCount; i++){
    			if (i == myCount-1){
    				a += myValues[i];
    			} else {
    				a = a + myValues[i] + " ";	
    			}
    		}
    		return a;
		}
	}

	/*
	 * returns the value at the given position in the sequence
	 * 
	 * e.g. If the sequence contains 3, 1, and 4, elementAt(0) returns 3.
	 * 
	 * Note: If someone asks for the elementAt an index that does not exist, you
	 * should call System.err.println and include a description of the error and
	 * call System.exit(1) to exit the method. The same is true for any case
	 * where a method is called with incorrect input.
	 */
	public int elementAt(int pos) {
		if (pos >= myCount || pos < 0) {
			System.err.println("Element does not exist!");
			System.exit(1);
		}
		return myValues[pos];
	}

	public void remove(int pos) {
		if (pos >= myCount || pos < 0 || myCount == 0) {
			System.err.println("Element does not exist!");
			System.exit(1);
		} else {
			for (int i = 0; i < myCount; i++) {
				if (i >= pos) {
					myValues[i] = myValues[i+1];
				}
			}
			myCount -= 1;
		}
	}
	
	public boolean contains(int num){
		boolean temp = false;
		for (int i = 1; i < myCount; i++){
			if (myValues[i] == num){
				temp = true;
				break;
			}
		}
		return temp;
	}

}

/**/
