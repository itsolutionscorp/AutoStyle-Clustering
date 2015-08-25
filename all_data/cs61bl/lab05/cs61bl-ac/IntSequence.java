public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount = 0;     // number of array cells used by sequence

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
    	if (myCount < myValues.length){ 
    		myValues[myCount] = toBeAdded;
    		myCount += 1;
    	} else {
    		System.err.println("Sequence is full.");
    		System.exit(1);
    	}
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive. 
    public void insert (int newInt, int pos) {
		if (pos < 0 || pos >= myCount) {
			return;
		}
		for (int k = myCount; k > pos; k-=1) {
			myValues[k] = myValues[k-1];
		}
		myValues[pos] = newInt;
		myCount += 1;
	}
	

    public boolean isEmpty() {
    	if (myCount == 0) {
    		return true;
    	} else {
    		return false;
    	}
    }

    public int size() {
    	return myCount;
    }
    
    public int elementAt(int pos) {
    	if (pos < myCount) {
    		int ans = myValues[pos];
    		return ans;
    	} else {
    		System.err.println("No value at that index.");
    		System.exit(1);
    		return 0;
    	}
    }
    public String toString() {
    	String myString = new String();
    	for (int i = 0; i<myCount; i++) {
    		if (i == myCount - 1) {
    			myString += myValues[i];
    		} else {
    		myString += myValues[i] + " ";
    	}
    	}
    	return myString;
    }

    public int remove(int pos) {
    	int oldInt = myValues[pos];
    	if (pos < 0 || pos >= myCount) {
    		return 0;
    		}
    	for (int k = (pos+1); k<myCount; k+=1) {
    		myValues[k-1] = myValues[k];
    		}
    	myValues[myValues.length-1] = 0;
    	myCount -= 1;
    	return oldInt;
    }
    
    public boolean contains(int k) {
    	for (int i = 0; i < myCount; i++) {
    		if (myValues[i] == k) {
    			return true;
    		}
    	}
    	return false;
    }
}
