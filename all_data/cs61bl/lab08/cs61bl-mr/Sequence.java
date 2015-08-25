import java.util.Iterator;


public class Sequence<T> implements Iterable<T> {

    // instance variables
    protected T[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence (int capacity) {
        // YOUR CODE HERE
    	this.myValues = (T[]) new Object[capacity];
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
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

    public T elementAt(int pos)
    {
    	if (pos <= myCount){
    		return this.myValues[pos];
    	} else {
    		System.err.println("The position is out of the bound of the sequence.");
    		System.exit(1);
    		return null;
    	}
    }
    
    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(T toInsert, int insertPos) {
        T prev = myValues[insertPos];
        T curr;
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
    
    public boolean contains(T k) {
    	for(int i = 0; i<myCount; i++) 
    	{
    		if (k.equals(myValues[i]))
    		{
    			return true;
    		} 
    	}
    	return false;
    }
    
    public SequenceIterator<T> iterator()
    {
    	SequenceIterator<T> result = new SequenceIterator<T>();
        return result;
    }
    
    
    private class SequenceIterator<T> implements Iterator<T>
    {
    	private int index;
    	
    	public SequenceIterator() {
    		index = 0;
    	}
    	
    	private void initIterator() {
    		index = 0;
    	}
        public boolean hasNext() {
    		return index<myCount;
    	}
    	public T next() {
    		T a = (T) myValues[index];
    		index++;
    		return a;
    	}
    	
    	public void remove() {
    		
    	}
    	
    }
}

