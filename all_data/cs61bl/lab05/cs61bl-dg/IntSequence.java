public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;
    protected int maxLength;

    // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
    	myValues = new int[capacity];
    	maxLength = capacity;
    	myCount = 0;
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
    	if(myCount == maxLength){
    		System.err.println("Error: Sequence is full");
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

    // other methods go here
    public boolean isEmpty(){
    	return myCount == 0;
    }
    public int size(){
    	return myCount;
    }
    public int elementAt(int pos){
    	if (pos >= myCount){
    		System.err.println("Error: No Value at Specified Position");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    public String toString(){
    	String returnString = new String();
    	for(int element: myValues){
    		returnString += element + " ";
    	}
    	return returnString;
    }
    public int remove(int value){
    	int returnInt = myValues[value];
    	for(int x=value;x<maxLength-1;x++){
    		myValues[x] = myValues[x+1];
    	}
    	myCount--;
    	return returnInt;
    }

    public boolean contains(int k) {
    	for (int elm = 0; elm < myCount; elm++) {
    		if (myValues[elm] == k) {return true;}
    	}
    	return false;
    }
}
