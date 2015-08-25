import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Sequence<T> implements Iterable<T>{

    // instance variables
    protected T[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence
    
    private int myPos;
    
    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    @SuppressWarnings("unchecked")
	public Sequence(int capacity) {
        myValues = (T[]) new Object[capacity];
        myCount = 0;
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
        myValues[myCount] = toBeAdded;
        myCount++;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(T toInsert, int insertPos) {
    	for(int i = myCount; i > insertPos; i--){
			myValues[i] = myValues[i-1];
		}
        myValues[insertPos] = toInsert;
        myCount++;
    }

    // other methods go here
    public boolean isEmpty(){
    	if(myCount == 0) {
    		return true;
    	}
    	return false;
    }
    
    public int size(){
    	return myCount;
    }
    
    public T elementAt(int pos){
    	if(pos < 0 || pos >= myCount) {
    		System.err.println("Element at index " + pos + "does not exist");
        	System.exit(1);
    	}
    	return myValues[pos];
    }
    
    public int remove(int pos) {
    	if (pos < 0 || pos >= myCount) {
			return -1;
		}
		for(int i = pos; i < myCount-1; i++){
			myValues[i] = myValues[i+1];
		}
		myCount--;
		return 0;
    }
    
    public boolean contains(T k) {
    	for(int i = 0; i < myCount; i++){
			if(myValues[i].equals(k)){
				return true;
			}
		}
		return false;    	
    }
    
    public String toString() {
		String s = "";
		for(int i=0; i<myCount; i++){
			s = s + myValues[i] + " ";
		}
    	return s.trim();
    }
    
    // Nested class
    private class Iter implements Iterator<T> {

		public boolean hasNext() {
			if(myPos < myCount)
				return true;
			return false;
		}

		public T next() {
			T tmp = myValues[myPos];
			myPos ++;
			return tmp;
		}

		public void remove() {
			// TODO Auto-generated method stub
			
		}

		public void forEachRemaining(Consumer<? super T> action) {
			// TODO Auto-generated method stub
			
		}
		
    }

	public Iterator<T> iterator() {
		myPos = 0;
		return new Iter();
	}

	public void forEach(Consumer<? super T> action) {
		// TODO Auto-generated method stub
		
	}

	public Spliterator<T> spliterator() {
		// TODO Auto-generated method stub
		return null;
	}
}

