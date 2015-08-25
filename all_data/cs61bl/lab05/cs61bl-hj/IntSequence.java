public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        myValues = new int[capacity]; 
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        if (myCount == myValues.length){
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
        for (int k = myCount; k > insertPos; k--) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }

    public boolean isEmpty(){
    	if(myCount == 0){
    		return true;
    	}
    	return false;
    }
    
    public int size(){
    	return myCount;
    }
    
    public int elementAt(int pos){
    	if (pos >= myCount || pos < 0){
    		System.err.println("Not a valid position");
        	System.exit(1);
    	}
    	return myValues[pos];
    }
    
    public String toString(){
    	String intString = "";
    	for (int i = 0; i < myCount; i++){
    		if (i == myCount - 1){
    			intString = intString + myValues[i];
    		}
    		else{
    			intString = intString + myValues[i] + " ";
    		}
    	}
    	return intString;
    }
    
    public int remove(int pos){
    	if(pos<0 || pos >= myCount){
    		System.err.println("Not a valid position");
        	System.exit(1);
		}
    	int removed = myValues[pos];
    	myCount -= 1;
    	for (int i = pos; i < myCount; i++){
    		myValues[i] = myValues[i+1];
    	}
    	return removed;
    }
    
    public boolean contains(int x){
    	for(int i = 0; i < myCount; i++){
    		if(myValues[i]==x){
    			return true;
    		}
    	}
    	return false;
    }
}

