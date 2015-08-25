public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
    	if (capacity <= 0) {
    		System.err.println("Constructor Error : Array size cannot be same or lesser than zero ");
    		System.exit(1);
    	}
    	myValues = new int[capacity];
    	myCount = 0;
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
    	if (myCount >= myValues.length) {
    		System.err.println("Add Method Error : IntSequence is full");
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
    	if (myCount >= myValues.length) {
    		System.err.println("Insert Method Error : IntSequence is full");
    		System.exit(1);
    	}
    	
    	if (insertPos >= myCount || insertPos < 0) {
    		System.err.println("Insert Method Error : Insert Position can't be larger than myCount or lesser than zero");
    		System.exit(1);
    	}
    	
    	/* Original BUGGY Code
        for (int k = insertPos + 1; k <= myCount; k++) {
            myValues[k] = myValues[k-1];
        } */
        
        for (int i = myCount - 1; i > insertPos; i--) {
			myValues[i] = myValues[i - 1];
		}
        
        myValues[insertPos] = toInsert;
        myCount++;
    }

    // other methods go here

    public boolean isEmpty() {
    	if (myCount == 0) return true;
    	else return false;
    }
    
    public int size() {
    	return myCount;
    }
    
    public int elementAt(int pos) {
    	if (pos >= myCount || pos < 0) {
    		System.err.println("ElementAt Method Error : Argument is larger than the size of IntSequence");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    
    public void remove(int pos) {
    	if (pos < 0 || pos >= myCount) {
    		System.err.println("Remove Method Error : Argument is same or larger than myCount");
    		System.exit(1);
		}
		
		for (int i = pos; i < myCount - 1; i++) {
			myValues[i] = myValues[i+1];
		}
		
		myCount--;
    }
    
    public boolean contains(int k) {
    	for (int i = 0; i < myCount; i++) {
    		if (myValues[i] == k) return true;
    	}
    	return false;
    }
    
    public String toString() {
    	String temp;
    	temp = "";
    	for (int i = 0; i < myCount; i++) {
    		if (i == myCount - 1) temp += myValues[i];
    		else temp += myValues[i] + " ";
    	}
    	
    	return temp;
    }
    

}

