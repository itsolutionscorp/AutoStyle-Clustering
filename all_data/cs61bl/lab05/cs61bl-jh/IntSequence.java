public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
       this.myCount = 0;
       this.myValues = new int[capacity];
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	if(this.myCount == this.myValues.length){
    		System.err.println("sequence is full");
    		System.exit(1);
    	}
    	this.myValues[this.myCount] = toBeAdded;
    	this.myCount++;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
    	int pre = myValues[insertPos];
    	int temp;
        for (int k = insertPos + 1; k <= myCount; k++) {
        	temp = myValues[k];
            //pre = myValues[k-1];
        	myValues[k] = pre;
        	pre = temp;
        	
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }
    public void remove(int position){
 
    	for (int i = position; i < this.myCount; i++) {
			this.myValues[i] = this.myValues[i+1];
		}
    	this.myCount--;
    }
    
    // other methods go here
    public boolean isEmpty(){
    	if (myCount == 0 ){
    		return true;
    	}
    	return false;
    }
    
    public int size(){
    	return this.myCount;
    }
    
    public int elementAt(int pos){
    	if(pos > this.myCount-1){
    		System.err.println("there is no element at position " + pos);
    		System.exit(1);
    	}
    	return this.myValues[pos];
    }
    
    public String toString(){
    	String newString = "";
    	int i = 0;
    	for (; i < this.myCount - 1; i++) {
    		newString += this.myValues[i] + " ";
    	}
    	newString += this.myValues[i];
    	return newString;
    }
    public boolean contains(int n) {
    	for (int i = 0; i < this.myCount; i++) {
    		if (i == n) {
    			return true;
    		}
    	}
    	return false;
    }
}

