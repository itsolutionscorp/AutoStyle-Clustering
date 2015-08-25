import java.util.Iterator;



public class Sequence <T> implements Iterable <T>{

    // instance variables
    protected T[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
        myValues = (T[]) new Object[capacity];
        myCount = 0;
    }
    
    public Iterator <T> iterator(){
    	return new sequenceIterator();
    }
    private class sequenceIterator implements Iterator<T> {
    	private int index = 0;
    	public boolean hasNext() {
    		if (index >= myCount){
    			return false;
    		}
    		return true;
    	}
    	public T next() {
    		T rtn;
    		rtn = myValues[index];
    		if (this.hasNext()){
    			index++;
    		}
    		return rtn;
    	}
    }
    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
        // YOUR CODE HERE
    	myValues[myCount] = toBeAdded;
    	myCount++;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(T toInsert, int insertPos) {
        for (int k = insertPos + 1; k <= myCount; k++) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }

    // other methods go here
    public boolean isEmpty(){
    	for (int c = 0; c<myCount; c++){
			if (myValues[c] != null){
				return false;
			}
		}
		return true;
	
    }
    
    public int size(){
    	return myCount;
    }
    
    public T elementAt(int pos){
    	if (pos>=myCount){
    		System.err.println("invalid position");
    		System.exit(1);
    	}
		
		T element = myValues[pos];
    	return element;

    }
    
    @Override
    public String toString(){
    	String result = "";
    	for (int c = 0; c<myCount; c++){
    		if (c==(myCount-1)) {
    			result = result + myValues[c];
    		} else {
    			result = result + myValues[c] + " ";
    		}
    	}
    	return result;
    }
    
    
}
