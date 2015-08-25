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
        // YOUR CODE HERE
    	myValues[myCount] = toBeAdded;
    	myCount++;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
	public void insert (int newInt, int pos) {
		if (pos < 0 || pos > myCount) {
			return;
		}
		int prev = myValues[pos];
		int next = 0;
		if ((pos+1)<myCount) {
			next = myValues[pos + 1];
		} 
		myValues[pos] = newInt;
		myCount += 1;
		for(int c = pos+1; c < myCount; c++){
			myValues[c] = prev;
			prev = next;
			if ((c+1)<myCount){
				next = myValues[c+1];
			}
		}
		
		}

    // other methods go here
    public boolean isEmpty(){
    	for (int c = 0; c<myCount; c++){
			if (myValues[c] != 0){
				return false;
			}
		}
		return true;
	
    }
    
    public int size(){
    	return myCount;
    }
    
    public int elementAt(int pos){
    	if (pos>=myCount){
    		System.err.println("invalid position");
    		System.exit(1);
    	}
		
		int element = myValues[pos];
    	return element;

    }
    
    public void remove (int pos) {
		if (pos < 0 || pos >= myCount) {
			return;
		}
		int next = 0;
		if ((pos+1)<myCount){
				next = myValues[pos+1];
		}
		for (int i = pos; i<myCount; i++){
			myValues[i]=next;
			if ((i+2)<myCount){
				next = myValues[i+2];
			} else {
				next = 0;
			}
		}
		myCount -= 1;
		
	}
    
    public boolean contains(int k) {
    	if (isEmpty() == false){
    		for (int counter = 0; counter < myCount; counter++){
    			if (myValues[counter] == k){
    				return true;
    			}
    		}
    		return false;
    	}
   		return false;
    }
	
	

    
    @Override
    public String toString(){
    	String result = "";
    	for (int c = 0; c<myCount; c++){
    		if (c==(myCount-1)) {
    			result = result + myValues[c];
    		} else {
    			result = result + myValues[c] + " ";
    		}
    	}
    	return result;
    }
    
    
}

