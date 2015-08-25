import java.util.Iterator;

public class Sequence <T> implements Iterable<T> {

    // instance variables
    protected T[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
        myValues = (T[]) new Object[capacity];
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
        if (myCount == myValues.length) {
        	System.err.println("Array is full");
        	System.exit(1);
        }
        else {
        	myValues[myCount] = toBeAdded;
        	myCount++;
        }
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert (T newInt, int pos) {
		if (pos < 0 || pos >= myValues.length) {
			return;
		}
		T nextInt;
		for (int k = pos; k < myValues.length; k++) {
			nextInt = myValues[k];
			myValues[k] = newInt;
			newInt = nextInt;
		}
		myCount++;
	}

    // other methods go here
    public boolean isEmpty() {
    	return myCount == 0;
    }
    
    public int size() {
    	return myCount;
    }
    
    public T elementAt(int pos) {
    	if (pos >= myCount) {
    		System.err.println("Index does not exist");
    		System.exit(1);
    		return null;
    	}
    	else {
    		return myValues[pos];
    	}
    }
    
    public String toString() {
    	String myString = "";
    	for (int i = 0; i < myCount; i++) {
    		if (i == myCount-1) {
    			myString += myValues[i];
    		}
    		else {
    			myString += myValues[i] + " ";
    		}
    	}
    	return myString;
    }
    
    public void remove (int pos) {
		if (pos < 0 || pos >= myValues.length) {
			return;
		}
		for (int k = pos; k < myValues.length; k++) {
			if (k == myValues.length - 1) {
				myValues[k] = null;
			}
			else {
				myValues[k] = myValues[k+1];
			}
		}
		myCount--;
	}
    
    public boolean contains(T k) {
    	for (int i = 0; i < myCount; i++) {
    		if (myValues[i] == k) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public static void main(String[] args) {
    	Sequence<String> s = new Sequence<String>(4);
    	s.add("apple");
    	s.add("banana");
    	s.add("date");
    	System.out.println(s.toString());
    	s.insert("carrot", 2);
    	System.out.println(s.toString());
    	s.remove(1);
    	System.out.println(s.toString());
    	
    	Sequence<Integer> s2 = new Sequence<Integer>(5);
    	s2.add(0);
    	s2.add(1);
    	s2.add(3);
    	System.out.println(s2.toString());
    	s2.insert(2, 2);
    	System.out.println(s2.toString());
    	s2.remove(1);
    	System.out.println(s2.toString());
    	
    	
    	//Iterator Tests
    	Iterator<String> sIterator = s.iterator();
    	while (sIterator.hasNext()) {
    		System.out.println(sIterator.next());
    	}
    	
    	Iterator<Integer> s2Iterator = s2.iterator();
    	while (s2Iterator.hasNext()) {
    		System.out.println(s2Iterator.next());
    	}
    }
    
    public Iterator<T> iterator() {
    	return new SequenceIterator();
    }
    
    private class SequenceIterator implements Iterator <T>{
    	
    	int nextIndexToReturn;
    	
    	public SequenceIterator() {
    		nextIndexToReturn = 0;
    	}
    	
    	public boolean hasNext() {
    		return nextIndexToReturn < myCount;
    	}
    	
    	public T next() {
    		T rtnValue = myValues[nextIndexToReturn];
    		nextIndexToReturn++;
    		return rtnValue;
    	}
    }
}

