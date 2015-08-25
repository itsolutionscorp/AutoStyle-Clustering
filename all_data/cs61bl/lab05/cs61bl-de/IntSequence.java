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
        myValues[myCount] = toBeAdded;
        myCount++;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
    	for(int i = myCount; i > insertPos; i--){
			myValues[i] = myValues[i-1];
		}
        myValues[insertPos] = toInsert;
        myCount++;
    }

    // other methods go here
    public boolean isEmpty(){
    	if(myCount == 0) {
    		return true;
    	}
    	return false;
    }
    
    public int size(){
    	return myCount;
    }
    
    public int elementAt(int pos){
    	if(pos < 0 || pos >= myCount) {
    		System.err.println("Element at index " + pos + "does not exist");
        	System.exit(1);
    	}
    	return myValues[pos];
    }
    
    public int remove(int pos) {
    	if (pos < 0 || pos >= myCount) {
			return -1;
		}
		for(int i = pos; i < myCount-1; i++){
			myValues[i] = myValues[i+1];
		}
		myCount--;
		return 0;
    }
    
    public boolean contains(int k) {
    	for(int i = 0; i < myCount; i++){
			if(myValues[i] == k){
				return true;
			}
		}
		return false;    	
    }
    
    public String toString() {
		String s = "";
		for(int i=0; i<myCount; i++){
			s = s + myValues[i] + " ";
		}
    	return s.trim();
    }
}

