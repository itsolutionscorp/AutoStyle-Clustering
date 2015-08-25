public class IntSequence {
	
	

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        // YOUR CODE HERE
    	myCount = 0;
    	myValues = new int[capacity];
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	if (myCount < myValues.length){
    		myValues[myCount] = toBeAdded;
    	} else{
    		System.err.println("No open spots in array.");
    		System.exit(1);
    	}
    	myCount++;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert (int num, int pos) {
		if (pos < 0 || pos >= myCount) {
			return;
		}
		// YOUR CODE HERE
		int temp = 0;
		for (int i = 0; i < this.size()+1; i++){
			
		    if(i < pos){
				continue;
			} else if (i == pos){
				temp = myValues[i];
				myValues[i] = num;
			}
			else{
				int temp2 = myValues[i];
				myValues[i] = temp;
				temp = temp2;
			}
		    
		}
		myCount  = myCount+ 1;
	}

    // other methods go here
    
    public boolean isEmpty() {
    	for (int i = 0; i < myValues.length; i++) {
			if (myValues[i] == 0){
				continue;
		}else {
			return false;
		}
		
	}
		return true;
	}
    
    public int size(){
    	return myCount;
    }
    
    
    
    public int elementAt(int pos){
    	if (pos < 0 || pos > this.size() - 1){
    		System.err.println("Invalid index.");
    		System.exit(1);
    		
    	}
    	return myValues[pos];
    }
    
    
    public String toString(){
    	String sequence = "";
    	for (int i = 0; i < myCount; i++){
    		sequence += myValues[i] + " ";
    	}
    	return sequence;
    }
    
    public int remove (int pos) {
		if (pos < 0 || pos >= this.size()) {
			System.err.println("Invalid index.");
    		System.exit(1);
		}
		int value = myValues[pos];
	for (int i = 0; i < myCount; i++){
		if (i == myValues.length-1){
			myValues[i] = 0;
		} else if(i < pos){
			continue;
		} else if (i >= pos){
			myValues[i] = myValues[i+1];
				
		}
	}
	myCount -=1;
		
	return value;
	}
    
    public boolean contains(int k){
    	for (int i = 0; i < this.myCount; i++){
    		if (myValues[i] ==k){
    			return true;
    		}
    		
    	}
    	return false;
    }
    
}
    	
    
    



