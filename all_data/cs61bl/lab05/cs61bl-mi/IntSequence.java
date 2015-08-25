public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        // YOUR CODE HERE
    	myValues = new int[capacity];
    }
    
    public boolean isEmpty() {
    	return myCount == 0;
    }
    
    public int size() {
    	return myCount;
    }
    

    public int elementAt(int pos) {
    	if (pos < myCount) {
    		return myValues[pos];
    	}
    	else if (pos > myCount && pos < myValues.length){
    		System.err.println("Value does not exist at that element");
    		System.exit(1);
    		return -1; //we left this here because this method must return something, but should exit due to the above line
    	}
    	else {
    		System.err.println("Index is out of range");
    		System.exit(1);
    		return -1; //we left this here because this method must return something, but should exit due to the above line
    	}
    }


    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	if (myCount < myValues.length){ 
    	myValues[myCount] = toBeAdded;
    	myCount++;}
    	else {
    		System.err.println("Set is full");
    		System.exit(1);
    	}
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
        //for (int k = insertPos + 1; k <= myCount; k++) {
        //    myValues[k] = myValues[k-1];
        //}
        //myValues[insertPos] = toInsert;
        //myCount++;
        
        int store1 = 0;
		int store2 = 0;
		for (int i = insertPos; i <myCount + 1; i++) {
			if (i == insertPos) {
				store1 = myValues[i];
				store2 = myValues[i];
				myValues[i] = toInsert;
			}
			else {
				store2 = myValues[i];
				myValues[i] = store1;
				
			}
			store1 = store2;

			}
		myCount++;
    }

    // other methods go here
    
    public String toString() {
    	String sofar = "";
    	for (int i = 0; i < myCount; i++) {
    		if (i != myCount - 1) {
    			sofar = sofar + myValues[i] + " ";
    		}
    		else {
    			sofar = sofar + myValues[i];
    		}
    	}
    	return sofar;
    }
    
	public void remove (int pos) {
		if (pos < 0 || pos >= myCount) {
			return;
		}
		// YOUR CODE HERE
		for (int i = pos; i < myCount; i++) {
			if (i == myCount - 1){
				myValues[i] = 0;
			}
			else{
			myValues[i] = myValues[i+1];}
		}
		myCount--;
	}
	
}

