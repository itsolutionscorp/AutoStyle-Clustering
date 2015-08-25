public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
    	this.myValues = new int[capacity];
        this.myCount = 0;
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
    	if (this.myCount < this.myValues.length){
    		this.myValues[this.myCount] = toBeAdded;
    		this.myCount++;    		
    	} else {
    		System.err.println("Maximum Capacity Reached");
    		System.exit(1);
    	}
    }
    
    public void remove(int pos){
		if (pos < 0 || pos >= this.myCount) {
			return;
		}
		for (int i=pos; i < this.myCount; i++){
			if (pos < this.myCount-1) {
				this.myValues[i] = this.myValues[i+1];
				pos++; //move position to continue shifting
			}
		}
		if (this.myCount>0)
			this.myCount--;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int pos) {
    	if (pos < 0 || pos >= this.myCount || this.myCount == this.myValues.length) {
    		return;
   		}
   		for (int i = pos; i < this.myCount; i++ ){
   			int temp = this.myValues[i];
   			this.myValues[i]=toInsert;
   			toInsert = temp;
   			pos++;
   		}
   		this.myCount++;
    }
    
    public boolean contains(int k){
    	for (int i = 0; i < this.myCount; i++){
    		if (this.myValues[i]==k){
    			return true;
    		}
    	}
    	return false;
    }
    
    public boolean isEmpty(){
    	if (this.myCount==0)
    		return true;
    	return false;
    }

    public int size(){
    	return this.myCount;
    }
    
    public int elementAt(int pos){
    	if (pos < 0 || pos >= this.myCount){
    		System.err.println("Position out of Bounds");
    		System.exit(1);
    	}
    	return this.myValues[pos];
    }
    
    public String toString(){
    	String result = "";
    	for (int i=0; i<this.myValues.length; i++){
    		if (i==this.myValues.length-1){
    			result = result + this.myValues[i];
    		} else {
    			result = result + this.myValues[i] + " ";    			
    		}
    	}
    	return result;
    }
}

