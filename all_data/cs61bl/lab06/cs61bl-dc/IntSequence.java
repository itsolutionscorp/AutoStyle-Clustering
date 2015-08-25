public class IntSequence {

    // instance variables
    int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        myValues = new int[capacity];
        myCount = 0;
    }
    
    public boolean isEmpty()
    {
    	return myCount == 0;
    }
    
    public int size()
    {
    	return myCount;
    }
    
    public int elementAt(int pos)
    {
    	if (pos < 0 || pos >= myCount)
    	{
    		System.out.println("IndexOutOfBoundsException");
    		System.exit(1);
    	}
    	return myValues[pos];
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
    	if (myCount == myValues.length)
    	{
    		System.out.println("Not enough space.");
    		System.exit(1);
    	}
        myValues[myCount] = toBeAdded;
        myCount++;
    }

    public String toString()
    {
    	String str = new String();
    	for (int i = 0; i < myCount; i++)
    	{
    		str += myValues[i] + " ";
    	}
    	return str.trim();
    }
    
    public void remove(int pos)
    {
    	if (pos < 0 || pos >= myCount)
    	{
    		System.out.println("IndexOutOfBoundsException");
    		System.exit(1);
    	}
    	myValues[pos] = 0;
    	for (int i = pos; i < myCount - 1; i++)
		{
			int temp = myValues[i];
			myValues[i] = myValues[i + 1];
			myValues[i + 1] = temp;
		}
    	myCount--;
    }
    
    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(int pos, int newInt)
    {
    	if (pos < 0 || pos > myCount)
    	{
    		System.out.println("IndexOutOfBoundsException");
    		System.exit(1);
    	}
    	if (myCount == myValues.length)
    	{
    		System.out.println("Not enough space.");
    		System.exit(1);
    	}
    	myCount++;
    	int temp = myValues[pos];
		myValues[pos] = newInt;
		for (int i = pos + 1; i < myCount; i++)
		{
			int temp2 = myValues[i];
			myValues[i] = temp;
			temp = temp2;
		}
    }
    public boolean contains(int k)
    {
    	for (int i = 0; i < myCount; i++)
	    	if (myValues[i] == k)
	    		return true;
    	return false;
    }
}

