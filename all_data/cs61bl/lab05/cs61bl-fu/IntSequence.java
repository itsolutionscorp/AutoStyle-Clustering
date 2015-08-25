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
        if (myCount < myValues.length) {
            myValues[myCount] = toBeAdded;
            myCount ++;
        }
        else {
            System.err.println("No memory to add new value.");
            System.exit(1);
        }
    }

    // Buggy Version: Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive. 
    /*
    public void insert(int toInsert, int insertPos) {
        for (int k = insertPos + 1; k <= myCount; k++) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }*/
    
    // Tells if the IntSequence has zero elements stored in it.
    public boolean isEmpty () {
        return myCount == 0;
    }
    
    // Returns the number of elements stored in the IntSequence.
    public int size () {
        return myCount;
    }
    
    // Returns the Element at the specified position.
    public int elementAt (int pos) {
        if (pos < 0 || pos >= myCount) {
            System.err.println("Requested element is outside of bounds.");
            System.exit(1);
        }
        return myValues[pos];
    }

    public String toString () {
        String toReturn = "";
        for (int i = 0; i < myCount; i ++) {
            toReturn += myValues[i] + " ";
        }
        if (!toReturn.equals("")) {
            toReturn = toReturn.substring(0,toReturn.length() - 1);
        }
        return toReturn;
    }
    
    public int remove (int pos) {
    	if (myCount == 0) {
    		System.err.println("Can't remove element from an empty set.");
    		System.exit(1);
    	}
    	if (pos < 0 || pos >= myCount) {
    		System.err.println("Argument out of Range:");
    		System.exit(1);
    	}
        int store = myValues[pos];
        for (int i = pos; i < myCount - 2; i++) {
            myValues[i] = myValues[i+1];
        }
        myCount --;
        return store;
    }
    
    public boolean equals (Object other) {
        if (!(other instanceof IntSequence)) { return false; }
        IntSequence cOther = (IntSequence)other;
        if (myCount != cOther.myCount) { return false; }
        for (int i = 0; i < myCount - 1; i ++) {
            if (myValues[i] != cOther.myValues[i]) {return false;}
        }
        return true;
    }
    
 // Delete the value at the given position in the argument array,
 	// shifting all the subsequent elements down, and storing a 0
 	// as the last element of the array.
 	public static void remove (IntSequence seq, int pos) {
 		if (seq == null || seq.myCount == 0) { return; }
        if (pos < 0 || pos >= seq.myCount) {
 			return;
 		}
        for (int i = pos; i < seq.myCount - 2; i ++) {
             seq.myValues[i] = seq.myValues[i+1];
        }
         seq.myValues[seq.myCount-1] = 0;
         seq.myCount --;
 	}
 	
 	/* New Version:
 	 * Inserts value newInt at position pos.
 	 */
	public void insert (int newInt, int pos) {
        if (pos < 0 || pos > myCount || pos >= myValues.length ||
        		myCount + 1 > myValues.length) {
			System.err.println("Position out of range.");
			System.exit(1);
		}
        myCount ++;
        for (int i = myCount - 1; i > pos; i --) {
            myValues[i] = myValues[i-1];
        }
        myValues[pos] = newInt;
	}
	
	public boolean contains (int value) {
		for (int i = 0; i < myCount; i ++) {
			if (myValues[i] == value) {
				return true;
			}
		}
		return false;
	}
    

}

