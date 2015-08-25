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
    	if (myCount >= myValues.length) {
    		System.err.println("exceeds capacity");
    		System.exit(1);
    	}
    	myValues[myCount] = toBeAdded;
        myCount+=1; 
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int newInt, int pos) {  
        if (pos < 0 || pos >= myValues.length) {
			return;
		}
		int temp = myValues[0];
		for (int i = 0; i < myValues.length; i++) {
			if (i < pos) { 
				continue;
			}
			else if (i == pos) {
				temp = myValues[i];
				myValues[i] = newInt;
			}
			else if (i > pos) {
				int temp1 = myValues[i];
				myValues[i] = temp;
				temp = temp1;
			}
		}
		myCount++;
    }

    public boolean isEmpty() {
    	return myCount == 0; 
    }

    public int size() {
    	return myCount;
    }
    
    public int elementAt(int pos) { 
    	if (pos > myCount - 1 || pos < 0) { 
    		System.err.println("out of bounds error");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    
    
    public int remove(int pos) {
    	int val = myValues[pos];
    	for (int i = 0; i < myValues.length; i++) {
			if (i < pos) { 
				continue;
			}
			else if (i >= pos && i < myCount) {
				myValues[i] = myValues[i+1];
			}
		}
    	myCount--;
        return val; 
    }
    
    public boolean contains(int k) { 
    	for (int i = 0; i < myValues.length; i ++) { 
    		if (myValues[i] !=k) {
    			continue;
    		}
    		else if (myValues[i] == k) {
    			return true; 
    		}
    	}
    	return false; 
    }
    
    @Override 
    public String toString() {
    	String s = new String(); 
    	for (int i = 0; i < myCount - 1; i++) {
    		s += myValues[i] + " ";
    	}
    	s += myValues[myCount - 1];
    	return s;
    }
}

