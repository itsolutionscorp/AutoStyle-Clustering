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
    	if (myCount == myValues.length) {
    		System.err.println("Max capacity has been reached");
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
    
    public void insert (int pos, int newInt) {
		if (pos < 0 || pos >= myValues.length) {
			return;
		}
		for (int n = myValues.length - 1; n > pos; n--) {
			myValues[n] = myValues[n-1];
		}
		myValues[pos] = newInt;
		myCount++;
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
    	if (pos > myValues.length || pos < 0) {
    		System.err.println("Out of Range");
    		System.exit(1);
    	}
    		return myValues[pos];
    }
    
	public void remove (int pos) {
		if (pos < 0 || pos >= myValues.length) {
			return;
		}
		for (int n = pos; n<myValues.length-1; n++){
			myValues[n] = myValues[n+1];
		}
		myValues[myValues.length -1] = 0;
		myCount--;
	}
	
    public String toString() {
    	String myString = new String();
    	for (int k = 0; k < myCount; k++) {
    		myString = myString + myValues[k] + " "; 
    	}
    	return myString;
    }
    
    public boolean contains(int n) {
    	boolean contain = false;
    	for (int k = 0; k <= myCount; k++) {
    		if (myValues[k] == n) {
    			contain = true;
    			break;
    		}
    	}
    	return contain;
    }
    
    public static void main(String[] args) {
    	IntSequence s = new IntSequence(5);
		
		s.add(11);
		s.add(12);
		s.add(-2);
		s.add(-11);
		s.insert(1, 2);
		System.out.println(s.toString());
    }
}

