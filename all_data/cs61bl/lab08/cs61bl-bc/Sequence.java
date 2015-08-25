import java.util.Iterator;

public class Sequence <V>  implements Iterable{
	// instance variables
    private V[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
        // YOUR CODE HERE
    	myValues = (V[]) new  Object[capacity];
    	myCount = 0;
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(V toBeAdded) {
    	if (myCount == myValues.length){
    		System.out.println("sequence is full, integer cannot be added");
    		System.exit(1);
    	}
    	myValues[myCount] = toBeAdded;
    	myCount++;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public  void insert ( V toInsert, int insertPos) {
		if (insertPos < 0 || insertPos >= myValues.length) {
			return;
		}
		myCount++;
		for (int i= myCount-1; i> insertPos ; i--){
			myValues[i] = myValues[i-1];
		}
		myValues[insertPos] = toInsert;
	
	}

    // other methods go here

    public boolean isEmpty(){
    	if (myCount == 0)
    		return true;
    	return false;
    }
    
    public int size(){
    	return myCount;
    }
    
    public V elementAt(int pos){
    	return myValues[pos];
    }
    
    public String toString(){
    	String s = "";
    	for (int i=0; i < myCount ; i++){
    		s+= myValues[i]+" ";
    	}
    	return s.trim();
    	
    }
    
    public boolean contains(V k){
    	for (int i= 0 ; i < myCount; i++){
    		if( myValues[i] == k){
    			return true;
    		}
    	}
    	return false;
    }
    
    public void remove (int pos) {
		if (pos < 0 || pos >= myValues.length ) {
			return;
		}
		
		for (int i = pos; i < myCount -1; i++){
				myValues[i] = myValues[i+1];

		}
		
		myCount--;
	}
    public Iterator iterator(){
		Iterator<V> newIter = new SequenceIterator<V>();
		return newIter;
		
	}
    
    private class SequenceIterator <V> implements Iterator<V>{
    	int nextToReturn = 0;
    	
    	public boolean hasNext(){
    		return nextToReturn < myValues.length;
	
    	}
    	public V next(){
    		int count = nextToReturn;
    		nextToReturn ++;
    		return (V) myValues[count];
    		
    	}
    }

}
