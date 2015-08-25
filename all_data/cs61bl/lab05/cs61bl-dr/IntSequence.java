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
    public boolean isEmpty(){
    	return myCount == 0;
    }
    public int size(){
    	return myCount;
    }
    public int elementAt(int pos){
    	if (pos >= myCount){
    		System.err.println("Element index out of bounds.");
    		System.exit(1);
    	}
    	return myValues[pos];
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	if (myCount >= myValues.length){
    		System.err.println("No more space in myValues.");
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

    public void insert (int toInsert, int insertPos) {
		for (int k = myCount; k > insertPos; k--){
			myValues[k] = myValues[k-1];
		}
		myValues[insertPos] = toInsert;
		myCount++;
	}

    // other methods go here
    public String toString(){
    	String result = "";
    	for (int i = 0; i < myCount; i++){
    		result += myValues[i] + " ";
    	}
		return result.trim();
    	
    }

	public int remove (int pos) {
		int value = myValues[pos];
		if (pos < 0 || pos >= myCount) {
			System.err.println("Index out of bounds.");
			System.exit(1);
		}
		// YOUR CODE HERE
		for (int k = pos; k < myCount; k++){
			myValues[k] = myValues[k+1];
		}
		myCount--;
		return value;
	}
	public boolean contains(int k){
		for (int i = 0; i < myCount; i++){
			if (myValues[i] == k) return true;
		}
		return false;
	}
}

