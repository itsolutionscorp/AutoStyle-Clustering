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
        // YOUR CODE HERE
    	if (myValues.length > myCount){
    		myValues[myCount] = toBeAdded;
    		myCount ++;
    	}
//    	else if(myValues.length == myCount){
//    		myValues[myCount] = toBeAdded;
//    	}
    	else{
    		System.err.println("No more space avalible");
    		System.exit(1);
    	}
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
    		if (insertPos < 0 || insertPos >= myValues.length) {
    			return;
    		}
    		for (int c = 0, save = myValues[insertPos], temp= 0; c < myValues.length; c++){
    			
    			if (c < insertPos) {
    				continue;
    				}
    			else if (c == insertPos) {
    				myValues[c] = toInsert; 
    				}
    				else if (c== myValues.length - 1){
    					myValues[c] = save;

    					}
    				else{
    					temp = myValues[c];
    					myValues[c] = save;
    					save = temp;
    				}

    		}			
    }
    
    public  void remove(int[] myValues, int pos) {
		if (pos < 0 || pos >= myValues.length) {
			return;
		}
		for (int c = 0, save; c < myValues.length; c ++){
			if (c < pos) {
				continue;
				}
			else if (c == myValues.length - 1) {
				save = 0;
				myValues[c] = 0;
				myCount--;
				}
			else if (c == pos){
				save = myValues[c + 1];
				myValues[c] = save;
				pos++;
				}
			}
    	}

    // other methods go here
    public boolean isEmpty(){
    	if(myValues == null){
    		return true;
    	}
    	else if(myCount==0){
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
    	if (pos <= myCount - 1){
    		return myValues[pos];
    	}
    	else{
    		System.err.println("Out of bounds");
    		System.exit(1);
    		return 0;
    	}
    	
    }
    public boolean contains(int k){
    	for(int c=0 ;c < myCount ;c++){
    		if(myValues[c] == k){
    			return true;
    		}
    		else if(c == myCount - 1){
    			return false;
    		}
    		else{
    			continue;
    		}
    	}
		return false;
    }
    
    public String toString(){
    	String s = new String();
    	for (int c = 0; c < myCount; c++){ 
			s = (myValues[c] + " ");
			}
    	return s;
    }
}

