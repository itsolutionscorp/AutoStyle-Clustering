public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        // YOUR CODE HERE
    	myValues=new int[capacity];
    	myCount=0;
    	
    }
    
    
    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public boolean isEmpty() {
    	return myCount==0;
    }
    
    public int size() {
    	return myCount;
    }
    public int elementAt(int pos) {
    	if (pos<0){
    		System.err.println("cannot index negative numbers");
    		System.exit(1);
    	}
    	
    	if (pos>myCount-1) {
    		System.err.println("index does not exist");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	if (myCount == myValues.length) {
    		System.err.println("the sequence is full");
    		System.exit(1);
    	}
    	myValues[myCount] = toBeAdded;
    	myCount++;
    	
    	
    }
	public int remove (int pos) {
		if (pos < 0 || pos >= myCount) {
			System.err.println("index does not exist");
    		System.exit(1);
		}
		// YOUR CODE HERE
		int []temp= new int[myCount+1];
		for (int k=0; k < myCount; k++){
			temp[k]=myValues[k];
			temp[myCount]=0;
		}
		for (int n=pos; n < myCount; n++){
			 
			myValues[n]=temp[n+1];
		}
		myCount--;
		return temp[pos];
		

}

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.


    
	public void insert (int newInt, int pos) {
		if (pos < 0 || pos > myCount) {
			System.err.println("index does not exist");
    		System.exit(1);
		}
		// YOUR CODE HERE
		int []temp= new int[myCount+1];
		for (int k=1; k < myCount+1; k++){
			temp[k]=myValues[k-1];
		}
		myCount++;
		for (int n=pos+1; n < myCount; n++){
			 
			myValues[n]=temp[n];

		}
		myValues[pos] = newInt;
}
	public boolean contains(int n) {
		for (int i=0; i<myCount; i++) {
			if (myValues[i]== n) {
				return true;
			}		
		}
		return false; 
		
	}
	
    
    public String toString(){
    	String temp = "";
    	if (myCount > 0) {
    		for (int n=0; n<myCount-1; n++) {
        		temp = temp + myValues[n] + " ";
            }
        	temp = temp + myValues[myCount-1];
    	}
    	return temp;

    // other methods go here

    }
}
