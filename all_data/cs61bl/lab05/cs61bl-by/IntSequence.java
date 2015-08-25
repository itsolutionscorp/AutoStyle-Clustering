public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        // YOUR CODE HERE
    	myValues =  new int[capacity];
    	myCount = 0;
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	if (myValues.length == myCount){
    		System.err.println("Can't add, full capacity");
    		System.exit(1);
    	} else {
    	myValues[myCount]=toBeAdded;
    	myCount++;
    	}
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
        for (int k = myCount; k > insertPos; k--) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }

    // other methods go here
    public boolean isEmpty(){
    	if (myCount == 0){
    		return true;
    	} else {
    		return false;
    	}
    }

    public int size() {
    	return myCount;
    }
    
    public int elementAt(int pos){
    	if (pos < myCount){
    		return myValues[pos];
    	} else {
    		System.err.println("Position not included");
    		System.exit(1);
    		return 0; //to compile
    	}
    }
    
    public String toString(){
    	if (myCount == 0){
    		return "";
    	} else {
    	String s = new String("");
    	for (int k=0; k<myCount-1;k++){
    		s+=myValues[k] + " ";		
    		}
    	return s + myValues[myCount-1];
    	}
    }
    
	public int remove (int pos) {
		if (pos < 0 || pos >= myValues.length) {
			System.err.println("attempt to remove invalid pos");
			System.exit(1);
		}
		int a = myValues[pos];
		for(int k = 0; k<= myValues.length-1; k++){
			if (k<pos){
				continue;
			} else if (k==myValues.length-1){
				myValues[k] = 0;
			} else {
				myValues[k] = myValues[k+1];
			}
		}
		myCount --;
		return a;
	}

	public boolean contains(int k){
		for (int i=0; i < myCount; i++){
			if (k==myValues[i]){
				return true;
			}
		}
		return false;
	}
}

