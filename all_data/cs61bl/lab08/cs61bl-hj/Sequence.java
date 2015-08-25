import java.util.Iterator;
import java.util.NoSuchElementException;

public class Sequence<T> implements Iterable<T> {	

    // instance variables
    protected T[] myValues;     // sequence elements
    int myCount;                // number of array cells used by sequence
    
    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
        myValues = (T[]) new Object[capacity];
        myCount = 0;
    }
    
    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
     	if (myCount >= myValues.length){
     		System.err.println("sequence is full");
    		System.exit(1);
    	} else {
    		myValues[myCount] = toBeAdded;
    		myCount++;
        }
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
    	if (myCount == 0){
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public int size(){
    	return myCount;
    }
    
    public T elementAt(int pos) {
    	if (pos < 0 || pos >= myCount){
    		System.err.println("Position not in sequence");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    
    
    public String toString(){
    	String result = "";
     	for(int i = 0; i < myCount; i++){
     		if( i == myCount - 1) {
     			result = result + myValues[i];
     		} else {
    		result = result + myValues[i] + " ";
     		}
     	}	
     	return result;
    	
    }
    
    public boolean contains(T k) {
    	boolean contain = false;
    	for (int i = 0; i < (myValues.length - 1 ); i++) {
    		if(k == myValues[i]) {
    			contain = true;
    			break;
    		}
    	}
    	return contain;
    }
    
    public Iterator<T> iterator() {
    	return new SeqIterator();
    }
    
    private class SeqIterator implements Iterator<T> {
    	private int currentindex;
    	
    	public SeqIterator() {
    		currentindex = 0;
    	}
    	
    	public boolean hasNext() {
    		return currentindex <= myCount - 1; 
    	}
    	
    	public T next() {
    		if (!hasNext()) {
    			throw new NoSuchElementException();
    		} else {
    			int i = currentindex;
    			currentindex ++;
    			return myValues[i];
    		}
    	}
    	public void remove() {
    		throw new UnsupportedOperationException();
    	}
    	
    }
    
    
    public static void main (String[] arg) {
    	Sequence<String> s = new Sequence<String>(5);
    	s.add("haha");
    	s.add("hehe");
    	System.out.println(s.toString());
    	
    	
    	Sequence<Integer> n = new Sequence<Integer>(5);
    	n.add(5);
    	n.add(99);
    	n.add(100);
    	n.add(123);
    	n.add(33);
    	System.out.println(n.toString());
    	
    	Iterator<Integer> iter = n.iterator();
    	System.out.println(iter.hasNext());
    	if(iter.hasNext()) {
    		System.out.println(iter.next());
    	}
    	System.out.println(iter.hasNext());
    	if(iter.hasNext()) {
    		System.out.println(iter.next());
    	}
    	System.out.println(iter.hasNext());
    	if(iter.hasNext()) {
    		System.out.println(iter.next());
    	}
    	System.out.println(iter.hasNext());
    	if(iter.hasNext()) {
    		System.out.println(iter.next());
    	}
    	System.out.println(iter.hasNext());
    	if(iter.hasNext()) {
    		System.out.println(iter.next());
    	}
    	System.out.println(iter.hasNext());
    	iter.next(); // an exception will be thrown
    }
    
}
