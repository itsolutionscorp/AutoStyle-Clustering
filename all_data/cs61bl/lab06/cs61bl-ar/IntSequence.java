public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        // YOUR CODE HERE
    	this.myValues = new int[capacity];
    	this.myCount = 0;
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	if(this.myCount >= this.myValues.length){
    		System.err.println("Not enough empty spots!");
    		System.exit(1);
    	}
    	this.myValues[this.myCount] = toBeAdded;
    	this.myCount ++;
    }
    


    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert (int newInt, int pos) {
		if (pos < 0 || pos >= this.myValues.length) {
			return;
		}
		int store  = 0;
		int buffer = 0;
		for(int arrayPos = 0; arrayPos < this.myValues.length ; arrayPos ++){
			if (arrayPos == pos){
				store = this.myValues[arrayPos];
				this.myValues[arrayPos] = newInt;
			}else if(arrayPos > pos){
				buffer = this.myValues[arrayPos];
				this.myValues[arrayPos] = store;
				store = buffer;
			}
			
		}
		this.myCount ++;
	}

    // other methods go here
    public boolean isEmpty(){
    	return this.myCount == 0;
    }
    
    public int size(){
    	return this.myCount;
    }
    
    public int elementAt(int pos){
    	if(pos > this.myCount - 1){
    		System.err.println("Invalid index!");
    		System.exit(1);
    	}
    	return this.myValues[pos];
    }
    
    public String toString(){
    	String result = "";
    	for (int k = 0;k<this.myCount - 1;k++){
    		result += this.myValues[k];
    		result += " ";
    	}
    	result += this.myValues[this.myCount - 1];
    	return result;
    }
    
    public void remove(int pos){
    	 
    	for (int k = pos; k < this.myCount; k++){
    		this.myValues[k] = this.myValues[k + 1];
    	}
    	this.myCount --;
    }
    
    public boolean contains(int k){
    	for (int i = 0; i < this.myCount; i ++){
    		if (this.myValues[i] == k){
    			return true;
    		}
    	}
    	return false;
    }

}

