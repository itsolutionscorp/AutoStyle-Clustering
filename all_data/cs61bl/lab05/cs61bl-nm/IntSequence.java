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
    	if(myCount < myValues.length)
    	{
	    	myValues[myCount] = toBeAdded;
	    	myCount++;
	    }
    	else
    	{
    		System.err.println("");
    		System.exit(1);
    	}
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
/*    public void insert(int toInsert, int insertPos) {
        for (int k = insertPos + 1; k <= myCount; k++) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }*/

    // other methods go here
    public boolean isEmpty()
    {
    	if (myCount == 0)
    		return true;
    	return false;
    }
    
    public int size()
    {
    	return myCount;
    }
    
    public int elementAt(int pos)
    {
    	
    	if (pos > myCount)
    	{
	    	System.err.println("The value you entered is out of bounds.");
	    	System.exit(1);
    	}
	    return myValues[pos];
    }
    
    public String toString()
    {
    	String int_string = new String();
    	for(int i = 0; i < myCount; i++)
    	{
    		if (i == myCount - 1)
    			int_string = int_string + myValues[i];
    		else
    			int_string = int_string + myValues[i] + " ";
    	}
    	return int_string;
    }
    public int remove (int pos) {
		// YOUR CODE HERE
    	int removed = myValues[pos];
		for(int i=pos;i < myValues.length-1;i+=1) {
			myValues[i] = myValues[i+1];
		}
		myValues[myValues.length-1]	= 0;
		myCount = myCount - 1;
		return removed;
	}
 // Insert toInsert into the sequence at position insertPos,
 // shifting the later elements in the sequence over to make room
 // for the new element.
 // Assumptions: the array isn't full, i.e. myCount < myValues.length.
 //   Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int pos) {
		// YOUR CODE HERE
		for(int i=myCount-1;i>=pos;i-=1) {
			myValues[i+1] = myValues[i];
		}
		myValues[pos]= toInsert;
		myCount += 1;
	}
    public boolean contains(int k){
    	for(int i=0; i<myCount;i++){
    		if (myValues[i]==k){
    			return true;
    		}
    	}
    	return false;
    }

}

