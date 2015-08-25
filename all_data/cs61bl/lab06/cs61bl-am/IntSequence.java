public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount=0;                // number of array cells used by sequence
    public int myCap;
    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        myValues = new int[capacity];
        myCap = capacity;
        
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
     if (myCount > myCap){
    	 System.exit(1);
     }else{
       myValues[myCount] = toBeAdded;
       myCount++;}
       
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
    
    public int remove (int pos) {
    	int del = myValues[pos];
		for(int k = pos; k < myCount-1;  k++){
			myValues[k]=myValues[k+1];
		}
		myValues[myCount-1] = 0;	
		myCount--;
		return del;
    }

    public boolean isEmpty() {
    	return (myCount==0);
    }
    public int size() {
    	return myCount;
    }
    public int elementAt(int pos){
    	if (pos < 0 || pos >= myValues.length) {
    		System.err.println();
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    public String toString(){
    	String myString= "";
    	for (int i = 0; i < myCount-1; i++) {
    		myString = myString + myValues[i] + " ";
    	}
    	myString = myString + myValues[myCount];
    	return myString;
    }
    
    public boolean contains(int k){
    	boolean myBool = false;
    	for (int i = 0; i<myCount; i++) {
    		if (myValues[i]==k){
    			myBool = true;
    		}
    	}
    	return myBool;
    }

}

