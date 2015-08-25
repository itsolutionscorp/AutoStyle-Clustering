public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount; // number of array cells used by sequence

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
        	System.err.println("intSequence is full");
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
    	if (insertPos > myCount) {
    		return;
    	}
    	for (int n, k = insertPos; k <= myCount; k++) {
			n = myValues[k];
			myValues[k] = toInsert;
			toInsert = n;
		}
    	myCount += 1;
    	
    }

    // other methods go here
    public boolean isEmpty() {
    	return myCount == 0;
    }
    
    public int size() {
    	return myCount;
    }
    
    public int elementAt(int pos) {
    	if (pos > myCount-1 || pos < 0) {
    		System.err.println("invalid position");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    
    public String toString() {
    	String result = "" + this.elementAt(0);
    	for (int k = 1; k < myCount; k++) {
    		result += " " + this.elementAt(k);
    	}
    	return result;
    }
    
    public int remove(int pos) {
    	int k = pos;
    	int temp = myValues[pos];
		int n;
		while (k+1 < myCount) {
			n = myValues[k+1];
			myValues[k] = n;
			k++;
		}
		myCount -= 1;
		return temp;
    }
    
    public boolean contains(int n) {
    	boolean check = false;
    	for (int k = 0; k < myCount; k++) {
    		if (myValues[k] == n) {
    			check = true;
    		}
    	}
    	return check;
    }
}

