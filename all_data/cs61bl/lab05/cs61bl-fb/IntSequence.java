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
    	for (int i = 0; i <=myValues.length - 1; i++ ) {
    		myValues[i] = 0;
    	}
    	myCount = 0; //how many element I have
    	
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        
    	if (myCount > myValues.length-1) {
    		System.err.println("");
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
        for (int k = myCount-1; k >= 0; k--) {
        
        	/*if (k>myValues.length-1) {
        	break;
        	}*/
        	myValues[k+1] = myValues[k];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }

    // other methods go here
    public boolean isEmpty() {
    	return myCount == 0;    	
    }
    public int size() {
    	
    	return myCount;
    }
    public int elementAt(int pos){
    	if (pos > myCount) {
    		System.err.println("Invalid Position!");
    		System.exit(1);
    	}
    	
    	return myValues[pos];
    }
    public void remove (int pos) {
		for (int i = pos; i < myValues.length-1; i++) {
			myValues[i] = myValues[i+1];
		}
		myValues[myCount-1] = 0;
		myCount--;
	}
    
    public boolean contains(int k) {
    	boolean contain = false;
    	for (int i = 0; i <= myCount - 1; i++) {
    		if (myValues[i] == k) {
    			contain = true;
    		}
    	}
    	return contain;
    }
	//
    public String toString() {
    	String returnString= new String();
    	if (myCount == 0) {
    		returnString ="";
    	}
    	
    	for (int i = 0; i <= myCount-1; i++) {
    		if (returnString.length() == 0) {
    			returnString = ""+ myValues[i];
    		}
    		else{
    			returnString = returnString + " " + myValues[i];	
    		}
    		
    	}		
    	return returnString;
    	
    }
}

