public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    
    public IntSequence(int capacity) {
        this.myValues = new int[capacity];
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
    	if (this.myCount >= myValues.length) {
    		System.err.println("Unable to access.");
    		System.exit(1);
    	}
    	else {
    		this.myValues[myCount] = toBeAdded;
    		this.myCount++;
        // YOUR CODE HERE
    	}
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
    	int x = 0;
        for (int k = insertPos + 1; k <= myCount; k++) {
            myValues[myCount-x] = myValues[myCount-x-1];
            x++;
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }
    public void remove(int pos) {
    	int x = 0;
    	for (int y = pos + 1; y <= myCount; y++)
    		this.myValues[pos+x] = this.myValues[pos+x+1];
    	x++;
    	myCount--;
    }
    public boolean isEmpty() {
    	if (this.myCount == 0) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    public int size() {
    	return this.myCount;
    }
    	
    public int elementAt(int pos){
    	if (pos >= this.myCount) {
    		System.err.println("Unable to access.");
    		System.exit(1);
    		return 0;
    	}
    	else 
    		return this.myValues[pos];
		
    }
    
    public String toString() {
    	String s = String.valueOf(myValues[0]);
    	for (int x = 1; x < myCount; x++) {
    		s = s+ " " + String.valueOf(myValues[x]);
    	}
    	return s;
    }
    // other methods go here
    public boolean contains(int k) {
    	for (int x = 0; x < myCount; x++) {
    		if (this.myValues[x] == k) {
    			return true;
    		}
    	}
    	return false;
    	
    }

}


