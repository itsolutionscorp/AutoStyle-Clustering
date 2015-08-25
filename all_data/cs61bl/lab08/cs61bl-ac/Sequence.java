import java.util.Iterator;


public class Sequence <T> implements Iterable<T> {
	
	 // instance variables
    protected Object[] myValues;   // sequence elements
    int myCount = 0;     // number of array cells used by sequence

    public Sequence (int capacity) {
        myValues = new Object[capacity];  
    }

    public void add(T toBeAdded) {
    	if (myCount < myValues.length){ 
    		myValues[myCount] = toBeAdded;
    		myCount += 1;
    	} else {
    		System.err.println("Sequence is full.");
    		System.exit(1);
    	}
    }
    
    public void insert (T newAdd, int pos) {
		if (pos < 0 || pos >= myCount) {
			return;
		}
		for (int k = myCount; k > pos; k-=1) {
			myValues[k] = myValues[k-1];
		}
		myValues[pos] = newAdd;
		myCount += 1;
	}
	

    public boolean isEmpty() {
    	if (myCount == 0) {
    		return true;
    	} else {
    		return false;
    	}
    }

    public int size() {
    	return myCount;
    }
    
    public T elementAt(int pos) {
    	if (pos < myCount) {
    		T ans = (T) myValues[pos];
    		return ans;
    	} else {
    		System.err.println("No value at that index.");
    		System.exit(1);
    		return null;
    	}
    }
    public String toString() {
    	String myString = new String();
    	for (int i = 0; i<myCount; i++) {
    		if (i == myCount - 1) {
    			myString += myValues[i];
    		} else {
    		myString += myValues[i] + " ";
    	}
    	}
    	return myString;
    }

    public T remove(int pos) {
    	T oldThing = (T) myValues[pos];
    	if (pos < 0 || pos >= myCount) {
    		return null;
    		}
    	for (int k = (pos+1); k<myCount; k+=1) {
    		myValues[k-1] = myValues[k];
    		}
    	myValues[myValues.length-1] = 0;
    	myCount -= 1;
    	return oldThing;
    }
    
    public boolean contains(T thing) {
    	for (int i = 0; i < myCount; i++) {
    		if (myValues[i] == thing) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public class SequenceIterator implements Iterator<T> {
    	private int iterIndex = 0;
    	
    	public SequenceIterator() {
    	}
    	
		public boolean hasNext() {
			if (iterIndex + 1 > myCount) {
				return false;
			}
			return true;
		}

		public T next() {
			T rtnT = (T) myValues[iterIndex];
			iterIndex++;
			return rtnT;
		}	
    }
    
	public Iterator<T> iterator() {
		Iterator<T> iterNew = new SequenceIterator();
		return iterNew;
	}

}
