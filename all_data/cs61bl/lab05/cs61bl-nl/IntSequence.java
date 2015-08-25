public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;  // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        // YOUR CODE HERE
    	myValues= new int[capacity];
    	myCount=0;
    	
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
//        for (int k = insertPos + 1; k <= myCount; k++) {
//            myValues[k] = myValues[k-1];
//        }
//        myValues[insertPos] = toInsert;
//        myCount++;
    	ArrayOperations.insert(myValues, insertPos, toInsert);
    }
    
    public void remove(int index) {
    	ArrayOperations.delete(myValues, index);
    }

    public boolean isEmpty(){
    	return myCount==0;
    }
    
    public int size(){
    	return myCount;
    }
    public int elementAt(int pos){
    	if(pos<0 || pos>myValues.length-1){
    		System.err.println("Out of bounds!!!!!!");
    		System.exit(1);
    	}
    	return myValues[pos];
    	
    }
    
    public String toString(){
    	String s="";
    	for(int i=0;i<myCount;i++){
    		s+=myValues[i]+"";
    	}
    	return s;
    }
    public boolean contains(int k) {
    	for(int i = 0; i < myCount; i++) {
    		if (elementAt(i) == k) {
    			return true;
    		}
    	} return false;
    }
    

}

