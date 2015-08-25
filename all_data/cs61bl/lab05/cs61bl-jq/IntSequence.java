public class IntSequence {

    // instance variables
    private int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
    	if (capacity>0) {
            myValues = new int [capacity]; 
        } else {
            myValues = new int [0];
        }
            myCount = 0; 
    	}

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	if (myCount >= myValues.length){
            System.err.println("Invalid argument...Exit");
                    System.exit(1);
    	}else {
            myValues[myCount] = toBeAdded;
            myCount ++;
    }
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
        for (int k = insertPos + 1; k <= myCount; k++) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount+=1;
    }

    // other methods go here
    public boolean isEmpty() {
    	if (myCount == 0) {
            return true;
    	}else {
    		return false;
    	}
    }
    
    public int size() {
    	return myCount;
    }
    
    public int elementAt(int pos) {
    	if ((pos >= 0) &&(pos <= myCount)) {
            return myValues[pos];
    	}else {
            System.err.println("Invalid argument for elementAt()...Exit");
            System.exit(1);
            return -1;
    	}
    }
    
    public String toString(){
        String myString = "";
        for (int i= 0;i<myCount;i++){
                myString = myString + String.format("%d", myValues[i]);                 
        }
        return myString;        
    }
    
	
	public int remove(int pos){
        int temp = elementAt(pos);
        for (int k = pos+1; k <= myCount; k++) {
            myValues[k-1] = myValues[k];
        }
        myCount--;
        return temp;
    }
	
	public boolean contains(int a){
		for (int k=0; k <=myCount; k++) {
			if (myValues[k] == a) {
				return true; 
			}
		} return false;
	}

}

