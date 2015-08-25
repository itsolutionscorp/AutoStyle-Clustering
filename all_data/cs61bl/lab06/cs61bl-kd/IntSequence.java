public class IntSequence {

	    // instance variables
	    private int[] myValues; // sequence elements
	    int myCount;            // number of array cells used by the sequence

	    // constructor
	    // capacity: actual size of the array or the (temporary) maximum
	    // number of elements it can hold
	    public IntSequence (int capacity) {
	    	myValues = new int[capacity];
	    	myCount = 0;
	    }

	    // other methods would go here
	    public boolean isEmpty() {
	    	if (myCount == 0) 
	    	{
	    		return true;
	    	} 
	    	else 
	    	{
	    		return false;
	    	}
	    }
	    
	    public int size() {
	    	return myCount;
	    }
	    
	    public int elementAt(int pos) {
	    	if (pos < 0 || pos >= myValues.length) 
	    	{
	    		System.err.println("Index does not exist");
	    		System.exit(1);
	    	}
	    	return myValues[pos];
	    }
	    
	 // Add the argument to the sequence by placing it in the first
	 // unused spot in the array and incrementing the count.
	 // Assume that the sequence isn't full.
	 public void add (int toBeAdded) {
		 if (myCount == myValues.length) 
		 {
			 System.err.println("No more open spots in the array");
			 //System.exit(1);
			 return;
		 }
		 myValues[myCount] = toBeAdded;
		 myCount += 1;
	 }
	
	 public String toString () {
		 String myString = "";
		 for (int i = 0; i < myCount; i += 1)
		 {
			 myString = myString + myValues[i] + " ";
		 }
		 return myString;
	 }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
    	if (myValues.length == myCount) {
    		int[] temp_myValues = new int[myCount*2];
    		for (int i = 0; i < myCount; i++) {
    			temp_myValues[i] = myValues[i];
    		}
    		myValues = temp_myValues;
    	}
        for (int k = myCount; k > insertPos; k--) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }
    
    public int remove (int pos) {
		if (pos < 0 || pos >= myCount) {
			System.err.println("out of range");
			//System.exit(1);
			return -1;
		}
		int returnValue = myValues[pos];
		for (int i = 0; i < myCount; i++)
		{
			if (i < pos)
			{
				continue;
			}
			else if (i==myCount -1)
			{
				myValues[i] = 0;
			}
			else
			{
				myValues[i] = myValues[i+1];
			}
		}
	    myCount--;
		return returnValue;
	}

    // other methods go here
    public boolean contains (int k) {
    	for (int i = 0; i < myCount; i++) {
    		if (k == myValues[i]) 
    		{
    			return true;
    		}
    		else
    		{
    			continue;
    		}
    	}
    	return false;
    }

}

