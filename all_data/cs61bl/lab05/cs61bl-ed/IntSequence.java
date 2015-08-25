public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        myCount=0;
        myValues = new int [capacity];
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
    	if (myCount == myValues.length)
    	{
    		System.err.println("No more space!");
    		System.exit(1);
    	}
    	else
    	{
    		myValues[myCount]=toBeAdded;
            myCount++;
    	}
        
    }

    /* Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int toInsert, int insertPos) {
        for (int k = insertPos + 1; k <= myCount; k++) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }*/

    public boolean isEmpty()
    {
    	if (myCount==0)
    	{
    		return true;
    	}
    	else{return false;}
    }
    public int size ()
    {
    	return myCount;
    }
    
    public int elementAt (int pos)
    {
    	if (pos<myCount&& pos>=0)
    	{
    		return myValues[pos];
    	}
    	else 
    	{
    		System.err.println("Index out of bounds");
    		System.exit(1);
    		return 0;
    	}
    }
    public String toString()
    {
    	String str = "";
    	for (int i=0;i<myCount-1;i++)
    	{
    		str = str +("" + myValues[i]+ " ");
    	}
    	str=str + ""+ myValues[myCount-1];
    	System.out.println(str);
    	return str;
    }
    
    public void remove (int pos) {
		if (pos < 0 || pos >= myValues.length) {
			return;
		}
		else
		{
			for (int i =0;i<myValues.length-pos-1;i++)
			{
				myValues[i+pos]=myValues[i+pos+1];
			}
			myValues[myValues.length-1]=0;
			myCount--;
		}
		
	}
    
 // Insert toInsert into the sequence at position insertPos,
 // shifting the later elements in the sequence over to make room
 // for the new element.
 // Assumptions: the array isn't full, i.e. myCount < myValues.length.
 //   Also, insertPos is between 0 and myCount, inclusive.
    public void insert (int newInt, int pos) {
		if (pos < 0 || pos > myCount) {
			return;
		}
		else
		{
			myCount++;
			for(int i=0;i<myCount-pos-1;i++)
			{
				myValues[myCount-(i+1)]=myValues[myCount-(i+2)];
			}
			myValues[pos]=newInt;
		}
		// YOUR CODE HERE
    }
    public boolean contains (int i)
    {
    	for (int k=0;k<myCount;k++)
    	{
    		if (myValues[k]==i)
    		{
    			return true;
    		}
    	}
    	return false;
    }

}

