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
        if (myCount < myValues.length){
        	myValues[myCount] = toBeAdded;
        	myCount++;
        }
        else{
        	System.err.println("OUT OF SPACE");
        	System.exit(1);
        }
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
        for (int k = myCount -1; insertPos <= k; k--) {
            myValues[k + 1] = myValues[k];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }

    public boolean isEmpty(){
    	return myCount ==0;
    }
    
    public int size(){
    	return myCount;
    }
    
    public int elementAt(int pos){
    	return myValues[pos];
    }
    public String toString(){
    	String str = "";
    	for (int k = 0; k < myCount; k++){
    		if (k == 0){
    			str = str + myValues[k];
    		}
    		else{
    		str = str + " " + myValues[k];
    		}
    	}
    	return str;
    }
    public int remove (int pos){
    	int popval = myValues[pos];
    	if (pos < 0 || pos >= myCount) {
			return popval;
			}
		for (int k = pos; k < myCount - 1; k++){
			myValues[k] = myValues[k+1];
		}
		myValues[myCount-1] = 0;
    	myCount--;
    	return popval;
    }
    public boolean contains(int k){
    	for(int i = 0; i < myCount; i++){
    		if (myValues[i]== k){
    			return true;
    		}
    	}
    	return false;
    }
}

