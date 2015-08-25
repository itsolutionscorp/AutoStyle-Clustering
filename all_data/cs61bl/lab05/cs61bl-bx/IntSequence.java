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
        myValues[myCount] = toBeAdded;
        myCount++;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
    	int[] result = new int[myCount];
        
    	for(int i = 0; i < myCount; i++){
        	result[i]= myValues[i];
        }
    	        
    	for (int k = insertPos + 1; k <= myCount; k++) {
            myValues[k] = result[k-1];
        }
    	myValues[insertPos] = toInsert;
        myCount++;
    }

    public boolean isEmpty(){
    	if (myCount == 0){
    		return true;
    	}
    	return false;
    }

    public int size() {
    	return myCount;
    }
    
    public int elementAt(int pos){
    	if(pos > myCount || pos < 0){
    		System.err.println("Position invalid");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    
	public void delete(int pos) {
		if (pos < 0 || pos >= myCount) {
			return;
		}
		for (int i = pos; i < myCount - 1; i++) {
			myValues[i] = myValues[i + 1];
		}
		myValues[myCount - 1] = 0;
		myCount--;
	}
	
	public boolean contains(int k){
		for (int i = 0; i < myCount; i++){
			if (myValues[i] ==k){
				return true;
			}
		}
		return false;
	}
    
    public String toString() {
    	String string1 = new String();
    	for(int i = 0; i < myCount; i++) {
    		string1 += i;
    		string1 += " ";
    	}
    	return string1;
    }
}

