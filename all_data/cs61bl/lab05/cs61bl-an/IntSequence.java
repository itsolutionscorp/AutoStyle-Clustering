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
    
	public int remove (int pos) {
		if (pos < 0 || pos > myCount - 1) {
			System.err.println("Error, invalid position");
			System.exit(1);
			return 0;
		}else{
			// YOUR CODE HERE
			int removed = myValues[pos];
			for(int i = pos + 1; i <= myCount; i++){
				
				if(i == myCount){
					myValues[myCount - 1] = 0;
					break;
				}else{
					myValues[i-1] = myValues[i];
					
				}
				
			}
			myCount--;
			return removed;
		}
	}

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
	public void insert (int newInt, int pos) {
		if (pos < 0 || pos > myCount - 1) {
			System.err.println("Error, invalid position");
			System.exit(1);
		}
		// YOUR CODE HERE
		for(int i = myCount; i >= pos; i--){
			if(i == pos){
				myValues[pos] = newInt;
				break;
			}else{
				myValues[i] = myValues[i - 1];
			}
		}
		myCount++;
	}

    // other methods go here
    public boolean isEmpty(){
    	for(int i = 0; i < myValues.length; i++){
    		if(myValues[i] != 0){
    			return false;
    		}
    	}
    	return true;
    }
    
    public int size(){
    	return myCount;
    }
    
    public int elementAt(int pos){
    	if(pos < myValues.length){
    		return myValues[pos];
    	}else{
    		System.err.println("Error, index does not exit");
    		System.exit(1);
    		return 0;
    	}
    }
 
    public String toString(){
    	String string = "";
    	for(int i = 0; i <= myValues.length-1; i++){
    		if(i == myValues.length-1){
    			string = string + myValues[i];
    		}else{
    			string = string + myValues[i] + " ";
    		}
    	}
    	return string;
    }
    
    public boolean contains(int k){
    	for(int i = 0; i <= myCount - 1; i++){
    		if(myValues[i] == k){
    			return true;
    		}
    	}
    	return false;
    }
}

