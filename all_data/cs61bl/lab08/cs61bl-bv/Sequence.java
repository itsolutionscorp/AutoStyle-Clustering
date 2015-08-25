import java.lang.Iterable;
import java.util.Iterator;

public class Sequence <T> implements Iterable<T>{
	private Object[] myValues;
	private int myCount;
	
	public Sequence (int capacity) {
		myValues = new Object[capacity];
		myCount = 0;
		
		
		
	}
	
	public void add(T toBeAdded) {
		if (myCount == myValues.length) {
    		System.err.println("Array is full. Sorry.");
    		System.exit(1);
		}
		myValues[myCount] = toBeAdded;
		myCount++;
	}
	
	public void insert(T toInsert, int insertPos) {
		T nextValue = (T)myValues[0];
        for (int k = 0; k <= myCount; k++) {
        	if (k == insertPos) {
        		myValues[k] = toInsert;
        		toInsert = nextValue;
        		insertPos++;
        	}
        	nextValue = (T)myValues[k+1];
        }
        myCount++;
	}
	
    public boolean isEmpty() {
    	return myCount == 0;
    }
    
    public int size() {
    	return myCount;
    }
    
    public T elementAt(int pos) {
    	if (pos >= myCount) {
    		System.err.println("No value at that index.");
    		System.exit(1);
    	}
    	return (T)myValues[pos];
    }
    
    public String toString() {
    	String sequenceStr = new String();
    	sequenceStr = (T)myValues[0] + "";
    	for (int i = 1; i < myCount; i++) {
    		sequenceStr = sequenceStr + " " + (T)myValues[i];
    	}
    	return sequenceStr;
    }
    
    public void remove(int pos) {
    	if (pos < 0 || pos >= myCount) {
    		System.err.println("Index doesn't exist.");
    		System.exit(1);
    	}
    	for (int i = 0; i < myCount; i++) {
    		T store = (T) myValues[i+1];
    		if (i == pos) {
    			myValues[i] = store;
    			pos++;
    		}
    	}
    	myCount--;
    }
    
    public boolean contains(T k) {
    	boolean has_k = false;
    	for (int i = 0; i <= myCount; i++) {
    		if ((T)myValues[i] == k) {
    			has_k = true;
    		}
    	}
    	return has_k;
    }
    
    public Iterator<T> iterator() {
    	return (Iterator<T>) new SequenceIterator();
    }
    
    private class SequenceIterator implements Iterator<T>{
    	
    	private int curr_index = 0;
    	    	
    	public boolean hasNext() {
    		return curr_index + 1 < myCount;
    	}
    	
    	public T next() {
    		if (curr_index >= myCount) {
    			System.out.println("Reached end of iterator.");
    			return null;
    		} else {
    			T current_value = (T)myValues[curr_index];
    			curr_index++;
    			return current_value;
    	}
    }
    }
    
    public static void main(String[] args) {
    	Sequence<Integer> i = new Sequence<Integer>(4);
    	i.add(1);
    	i.add(2);
    	Iterator<Integer> a = i.iterator();
    	System.out.println(a.next());
    	System.out.println(a.next());
    	System.out.println(a.next());
    	
    	Iterator<Integer> b = i.iterator();
    	System.out.println(b.next());
    	System.out.println(b.next());
    	System.out.println(b.next());
    	i.add(3);
    	System.out.println(b.next());
    }
}
