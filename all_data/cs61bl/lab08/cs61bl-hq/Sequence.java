import java.util.Iterator;
import java.util.NoSuchElementException;


public class Sequence<T> implements Iterable<T> {

	// instance variables
    protected T[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
    	myValues = (T[]) new Object[capacity];
    }

    public Iterator<T> iterator() {
        return new newIterator();
    }
    
    private class newIterator implements Iterator<T> {
    	private int k = 0;
    	private T temp;

        public T next() {
            if (this.hasNext()) {
                temp = myValues[k];
                k++;
                return temp;
            } else {
            	throw new NoSuchElementException();
            }
        }

        public boolean hasNext() {
            return (k <= myCount - 1);
        }

		public void remove() {
			throw new UnsupportedOperationException();
		}
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
        for (int k = myCount; k > insertPos; k--) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }

    // other methods go here
    public boolean isEmpty() {
    	return myCount == 0;
    }
    
    public int size() {
    	return myValues.length;
    }
    
    public T elementAt(int pos) {
    	if (pos > myValues.length - 1) {
    		System.err.println("Error: Out of bounds.");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    
    public String toString() {
    	String conversion = "";
    	for (int count = 0; count < myCount; count++) {
    		conversion = conversion + myValues[count];
    		if (count != myCount - 1) {
    			conversion = conversion + " ";
    		}
    	}
    	return conversion;
    }
    
    public void remove(int pos) {
		for (int count = pos; count <= this.size() - 2; count++) {
			myValues[count] = myValues[count + 1];
		}
		myValues[this.size() - 1] = null;
	}
    
    public boolean contains(T k) {
    	if (this.isEmpty()) {
    		return false;
    	} else {
    		boolean found = false;
    		for (int count = 0; count < this.size(); count++) {
    			if (myValues[count] == k) {
    				found = true;
    				break;
    			}
    		}
    		return found;
    	}
    }  
    

    public static void main(String[] args) {
        Sequence<Integer> s = new Sequence<Integer>(3);
        s.add(1);
        s.add(2);
        s.add(3);
        System.out.println(s.toString());
        Sequence<String> s2 = new Sequence<String>(3);
        s2.add("hello");
        s2.add("hola");
        s2.add("bonjour");
        System.out.println(s2.toString());
        s.remove(1);
        System.out.println(s.toString());
        Iterator<String> ess = s2.iterator();
        System.out.println(ess.hasNext());
        System.out.println(ess.next());
        System.out.println(ess.hasNext());
        System.out.println(ess.next());
        System.out.println(ess.hasNext());
        System.out.println(ess.next());
        System.out.println(ess.hasNext()); // should return false
    }
    
}
