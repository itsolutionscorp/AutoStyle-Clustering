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
    	if (size() == myValues.length) {
    		System.err.println("Error: Sequence Full (LOL)");
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
		for (int i = size(); i>= insertPos;i--) {
			if (i == insertPos) {
				myValues[i] = toInsert;
				myCount++;
			} else {
				myValues[i] = myValues [i-1];
			}
		}
	}

    // other methods go here
    public boolean isEmpty() {
    	return myCount == 0;
    }
   
    public int size() {
    	return myCount;
    }
   
    public int elementAt(int pos) {
    	if (pos < 0 || pos > size()) {
    		System.err.println("Error: Index Out of Range (QQ)");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    
    public String toString() {
    	String result = myValues[0] + " ";
    	for (int i=1; i<myCount-1; i++) {
    		result = result + myValues[i] + " ";
    	}
    	return result + myValues[myCount-1];
    }
    public int remove(int pos) {
    	int result = elementAt(pos);
    	if (pos < 0 || pos > size()) {
    		System.err.println("Error: Index Out of Range (GG)");
    		System.exit(1);
    	}
    	for (int i = pos; i<= size()-1;i++) {
    		if (i == size()-1) {
    			myCount--;
    		} else {
    			myValues[i]=myValues[i+1];
    		}
    	}
    	return result;
    }    
    public boolean contains(int number) {
    	for (int i = 0; i < size(); i++) {
    		if (myValues[i] == number) {
    			return true;
    		}
    	}
    	return false;	
    }
}

