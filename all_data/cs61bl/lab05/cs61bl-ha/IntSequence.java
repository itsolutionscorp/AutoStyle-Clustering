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
    
    public boolean isEmpty(){
    	if(myCount == 0)
    		return true;
    	else
    		return false;
    }
    
    public int size(){
    	return myCount;
    }
    
    public int elementAt(int pos){
    	if(pos < 0 || pos > myCount){
    		System.err.println("Index out of bounds");
    		System.exit(1);
    	}
    	return myValues[pos];
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
    	if(myCount > myValues.length){
    		System.err.println("Sequence is full");
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
    	if (insertPos < 0 || insertPos > myCount) {
			return;
		}
		for(int i = myCount; i > insertPos; i--){
			myValues[i] = myValues[i-1];
		}
		myValues[insertPos] = toInsert;
		myCount++;
    }

    public String toString(){
    	String values = "";
    	for(int i = 0; i < myCount; i++){
    		values += myValues[i] + " ";
    	}
    	return values.trim();
    }
    
    public void remove(int pos){
    	if (pos < 0 || pos >= myCount) {
			return;
		}
    	for(int i = pos; i < myCount - 1; i++){
    		myValues[i] = myValues[i+1];
    	}
    	myValues[myCount - 1] = 0;
    	myCount--;
    }
    
    public boolean contains(int k){
    	for(int i = 0; i < myCount; i++){
    		if(myValues[i] == k)
    			return true;
    	}
    	return false;
    }
}

