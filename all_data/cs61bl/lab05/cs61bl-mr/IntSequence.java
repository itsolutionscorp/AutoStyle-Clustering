public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        // YOUR CODE HERE
    	this.myValues = new int[capacity];
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        // YOUR CODE HERE
    	if (myCount < this.myValues.length) {
    		this.myValues[myCount] = toBeAdded;
    		myCount++;
    	} else {
    		System.err.println("The sequence is full.");
    		System.exit(1);
    	}
    }
    

    public boolean isEmpty()
    {
    	if (myCount == 0) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public int size()
    {
    	return myCount;
    }

    public int elementAt(int pos)
    {
    	if (pos <= myCount){
    		return this.myValues[pos];
    	} else {
    		System.err.println("The position is out of the bound of the sequence.");
    		System.exit(1);
    		return 0;
    	}
    }
    
    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
        int prev = myValues[insertPos];
        int curr;
        for (int k = insertPos + 1; k <= myCount; k++) {
        	curr = myValues[k];
        	myValues[k] = prev;
        	prev = curr;
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }

    // other methods go here
    public String toString()
    {
    	String myString = new String();
    	for (int i = 0;i < myCount-1; i++)
    	{
    		myString = myString +this.myValues[i]+" ";
    	}
    	if (myCount>0) {
    		myString = myString + this.myValues[myCount-1];
    	}
    	return myString;
    }
    
    public void remove(int pos) 
    {
    	for (int i = pos; i < myCount-1; i ++) {
    		this.myValues[i] = this.myValues[i+1];
    	}
    	myCount--;
    }
    
    public boolean contains(int k) {
    	for(int i = 0; i<myCount; i++) 
    	{
    		if (k == myValues[i]) 
    		{
    			return true;
    		} 
    	}
    	return false;
    }
}

