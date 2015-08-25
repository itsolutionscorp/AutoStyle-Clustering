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
    	myValues[myCount]=toBeAdded;
    	myCount++;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
        for (int k=insertPos+1; k<myCount; k++) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }
    
	public void remove (int k) {
		if (k < 0 || k >= myValues.length) {
			return;
		}
		for (int i =k;i<myCount;i++){
			myValues[i]=myValues[i+1];	
		}
		myValues[myCount]=0;
		myCount --;
	}
	
	public boolean contains (int k) {
		if (k < 0 || k >= myValues.length) {
			return false;
		}
		for(int i = 0;i<myCount;i++){
			if (myValues[i]==k){
				return true;
			}
		}
		return false;
	}

	public boolean isEmpty() {
		if (myCount == 0){
				return true;
		}
		else{
			return false;
		}		
	}
	
    public int size(){
    	return myCount;
    }
    
    public int elementAt(int pos){
    	return myValues [pos];
    }

    public String toString(){
    	String output = new String();
    	for (int i=0; i<myCount; i++)
    		output += myValues[i] + "";
    	return output;
    }
    // other methods go here

}

