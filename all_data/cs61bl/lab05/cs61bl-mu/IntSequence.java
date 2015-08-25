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
    	if(myCount < myValues.length){
	    	myValues[myCount] = toBeAdded;
	    	myCount++;
    	}else{
    		System.err.println("Array is full");
    		System.exit(1);
    	}
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int newInt, int pos) {
    	if (pos < 0 || pos >= myValues.length) {
    		System.err.println("Element does not exist at index");
    		System.exit(1);
		}else {
		for(int i  = myValues.length - 1; pos < i; i--){
			myValues[i] = myValues[i-1];
		}
		myValues[pos] = newInt;
		}
        myCount++;
    }

    // other methods go here

    public boolean isEmpty(){  	
	if(myCount == 0){
		return true;
	}else{
		return false;
	}
    }
    
    public int size(){
    	return myCount;
    }
    

    public int elementAt(int pos){
    	if(pos >= myCount){
    		System.err.println("Element does not exist at index");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    
    public String toString(){
    String result= "";
    	for(int i = 0;i < myCount; ++i){
    		result = result +myValues[i];
    		if(i !=myCount-1){
    			result = result +" ";
    		}
    	}
    	return result;
    }
    
    public int remove (int pos) {
    	int k = myValues[pos];
    	if (pos < 0 || pos >= myValues.length) {
    		System.err.println("Element does not exist at index");
    		System.exit(1);
		
		}else{
			
			for(int i = 1; (i+pos) <= myValues.length-1; i++){
				myValues[pos+i-1]= myValues[pos+i];
			}
				myValues[myValues.length-1] = 0;
		}
    	this.myCount--;
    	return k;
		// YOUR CODE HERE
	}
    
    public boolean contains (int k) {
    	for(int i = 0; i< myValues.length; i++){
    		if(myValues[i] == k){
    			return true;
    		}
    	}
    	return false;
    }

}

