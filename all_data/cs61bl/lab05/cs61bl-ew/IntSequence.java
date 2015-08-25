public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    private int myCount;                // number of array cells used by sequence
    private int myCapacity;
    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        // YOUR CODE HERE
    	myCapacity = capacity;
    	myValues = new int[capacity];
    	myCount = 0;
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	if (myCount == myCapacity) {
    		System.err.println("No more open spots in the array.");
    		System.exit(1);
    	}
    	myValues[myCount] = toBeAdded;
    	myCount++;
    }

//     Insert toInsert into the sequence at position insertPos,
//     shifting the later elements in the sequence over to make room
//     for the new element.
//     Assumptions: The array isn't full, i.e. myCount < myValues.length
//     Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {// (0, 1, 2, 3) (5, 2) (0, 1, 5, 3)
        for (int k = myCount; k > insertPos; k--) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }
    

    // other methods go here
    
    public void remove (int pos) {
		if (pos < 0 || pos >= myCount) {
			return;
		}
		int i = pos;
		while (i < myCount - 1) {
			myValues[i] = myValues[i + 1]; 
			i++;
		}
		myValues[myCount - 1] = 0;
		myCount--;
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
    	if (pos >= myCount) {
    		System.err.println("Index does not exist");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    
    public String toString() {
    	if (myCount == 0) {
    		System.err.println("There is nothing in your sequence.");
    		System.exit(1);
    	}
    	String myString = new String(); 
    	for (int i = 0; i < myCount; i++) {
    		if (i == myCount - 1) {
    			myString = myString + "" + myValues[i];
    		} else {
    			myString = myString + "" + myValues[i] + " ";
    		}
    	}
    	return myString;
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

