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
    	if(myCount == 0){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    public int size(){
    	return myCount;
    }
    
    public int elementAt(int pos){
    	if(pos + 1 > myCount){
    		System.err.println("Index does not exist.");
    		System.exit(1);
    	}
    	return myValues[pos];
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	if(myCount == myValues.length){
    		System.err.println("No more open spot.");
    		System.exit(1);
    	}
    	myValues[myCount] = toBeAdded;
    	myCount += 1;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
       if(insertPos < 0 || insertPos >= myCount){
    	   System.err.println("Index does not exist.");
    	   System.exit(1);
       }else if(myCount == myValues.length){
    	   System.err.println("No more open spot");
    	   System.exit(1);
       }
       for(int i = myCount;i >= insertPos; i--){
    	   if(i == insertPos){
    		   myValues[i] = toInsert;
    		   break;
    	   }
    	   myValues[i] = myValues[i - 1];
       }
       myCount += 1;
    }
    
    public void remove(int pos) {
		if (pos < 0 || pos >= myCount || myCount == 0) {
			System.err.println("Index does not exist.");
			System.exit(1);
		}
		for(int i = pos; i < myCount - 1; i++){
			myValues[i] = myValues[i + 1];
		}
		myCount -= 1;
	}
    
    public boolean contains(int k){
    	for(int item: myValues){
    		if(item == k){
    			return true;
    		}
    	}
    	return false;
    }
    
    @Override
    public String toString(){
    	String result = "";
    	for(int i = 0; i < myCount; i++){
    		if(i != 0){
    			result += " ";
    		}
    		result += myValues[i];
    	}
    	return result;
    }
}

