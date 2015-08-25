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

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	if (myCount >= myValues.length) {
    		System.err.println("no more space in the IntSequence");
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
    	if (insertPos > myCount || myCount >= myValues.length) {
    		System.err.println("Position is out of range");
        	System.exit(1);
    	}
    	
    	int temp1 = myValues[insertPos];
    	int temp2;
        for (int k = insertPos + 1; k <= myCount; k++) {
        	temp2 = myValues[k];
            myValues[k] = temp1;
            temp1 = temp2;
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }

    // other methods go here
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
    	if (pos > myCount) {
    		System.err.println("Position is out of range");
        	System.exit(1);
        	return 0; // only to compile so thinks always will return int
        
      	} else {
      		return myValues[pos];
      	}
    }
    public String toString() {
    	String output = "";
    	for (int k = 0; k < myCount; k++) {
    		output = output + " " + myValues[k];
    	}    	
    	return output.substring(1);
    }
    
    public void remove(int removePos) {
    	if (removePos > myCount) {
    		System.err.println("Position is out of range");
        	System.exit(1);
    	}
    	else {
    		for (int i = removePos; i < myCount; i++) {
    			if (i == myCount - 1) {
    				myValues[i] = 0;
    				continue;
    			}
			myValues[i] = myValues[i+1];
    		}
    		myCount --;
    	}
    }
    
    public boolean contains(int k){
    	for (int i = 0; i <= myCount; i++) {
    		if (myValues[i] == k) {
    			return true;
    		}
    	}
    	return false;
    }
}
    

