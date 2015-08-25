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
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	if (myCount == myValues.length) {
    		raiseerror("Error: sequence is full!");
    	}
    	myValues[myCount]= toBeAdded;
    	myCount++;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
    	if (myCount == myValues.length) {
    		raiseerror("Error: sequence is full!");
    	}
    	for (int k = myCount; k >=insertPos; k--) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }

    // other methods go here
    public boolean isEmpty() {
    	return myCount==0;
    }
    public int size() {
    	return myCount;
    }
    public int elementAt(int pos) {
    	if (pos>myCount ) {
    		raiseerror("Error: position does not exist!");
    	}
    	return myValues[pos];
    }
    private void raiseerror(String error) {
    	System.out.println(error);
    	System.exit(1);
    }
    public String toString() {
    	String result="";
    	for (int i = 0; i< myCount-1; i++) {
    		result = result+myValues[i]+" ";
    	}
    	result = result+myValues[myCount-1];
    	return result;
    }
    public void remove(int pos) {
    	if (pos>=myCount) {
    		raiseerror("Error: position does not exist!");
    	}
    	for (int i = pos; i<myCount; i++) {
    		myValues[i]=myValues[i+1];
    	}
    	myCount--;
    }
}

