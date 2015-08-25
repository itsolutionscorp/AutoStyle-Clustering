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
        if (size() == myValues.length){
        	System.err.println("No more open spots in the array.");
        	System.exit(1);
        }
    	myValues[size()] = toBeAdded;
        myCount++;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
    	if (myCount == myValues.length){
    		System.err.println("No more open spots in the array.");
    		System.exit(1);
    	}
    	if ((insertPos < 0) || (insertPos > myCount)){
    		System.err.println("Index out of bounds.");
    		System.exit(1);
    	}
        for (int k = myCount; k >= insertPos + 1; k--) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }

    // other methods go here

    public boolean isEmpty(){
    	if (myCount == 0){
    		return true;
    	}
    	return false;
    }

    public int size(){
    	return myCount;
    }
    
    public int elementAt(int pos){
    	if (pos > size()){
    		System.err.println("Index " + pos + " does not exist.");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    
    public String toString(){
    	String elements = new String();
    	if (myValues.length == 0){
    		System.err.println("The array is empty.");
    		System.exit(1);
    	}
    	if (myCount >= 1){
    		elements = String.valueOf(myValues[0]);
    	}
    	for (int x = 1; x < myCount; x++){
    		elements += " " + String.valueOf(myValues[x]);
    	}
    	return elements;
    }
    
    public int remove (int pos) {
    	int toReturn = myValues[pos];
		if (pos < 0 || pos >= myValues.length) {
			return 0;
		}
		int [] copyOfValues = new int [myValues.length];
		System.arraycopy(myValues, 0, copyOfValues, 0, myValues.length);
		for (int p = 0; p < myValues.length; p++){
			if (p == myValues.length - 1){
				myValues[p] = 0;
			}
			else if (p >= pos){
				myValues[p] = copyOfValues[p+1];	
			}
		}
		myCount--;
		return toReturn;
	}
    
    public boolean contains(int k){
    	boolean includes = false;
    	for (int x = 0; x < myCount; x++){
    		if (myValues[x] == k){
    			includes = true;
    			break;
    		}
    	}
    	return includes;
    }
}

