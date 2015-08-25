import java.awt.Point;
import java.util.Iterator;

public class Sequence<T> implements Iterable<T> {

	public Iterator<T> iterator() {
		return new SequenceIterator(this);
	}
	
	private class SequenceIterator implements Iterator<T> {
		private Sequence<T> seq = null;
		private int count = 0;
		public SequenceIterator(Sequence<T> s) {
			seq = s;
		}
		
		public boolean hasNext() {
			if (count < seq.myCount) {
				return true;
			} else {
				return false;
			}
		}
		
		public T next() {
			int c = count;
			count++;
			return seq.myValues[c];
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		
	}

	protected T[] myValues;
	int myCount; 
	
	public Sequence(int cap) {
		myCount = 0;
		myValues = (T[]) new Object[cap];
	}
	
    public void add(T toBeAdded) {
        this.myValues[myCount] = toBeAdded;
        myCount++;
    }
    
    public void insert(T toInsert, int pos) {
		T curr = null;
		myCount++;
		for (int i = 0; i < myCount; i++) {
			curr = myValues[i];
			if (i == pos) {
				myValues[i] = toInsert;
				break;
			}
		}
		T current;
		for (int j = pos + 1; j < myCount; j ++) {
			current = myValues[j];
			myValues[j] = curr;
			curr = current;
		}
    }
    
    public boolean isEmpty() {
		if (myCount == 0) {
			return true;
		}
		return false;
    }
    
    public int size() {
    	return myCount;
    }
    
    public T elementAt(int pos) {
    	if (pos > myCount || pos < 0) {
    		System.err.println("This is not a valid position.");
    		System.exit(1);
    	}
    	return this.myValues[pos];
    }
    
    public String toString() {
    	String ret = "";
    	for (int i = 0; i < myCount; i++) {
    		if (i == myCount - 1) {
    			ret += this.myValues[i];
    		} else {
    			ret += this.myValues[i] + " ";
    		}
    	}
    	return ret;
    }
    
    public void remove(int pos) {
		if (pos < 0 || pos >= myCount) {
			return;
		}
		for (int i = 0; i < myCount; i++) {
			if (i == pos) {
				for (int k = i; k+1 < myCount; k++) {
					myValues[k] = myValues[k+1];
				}
			}
		}
		myValues[myCount - 1] = null;
		myCount--;
    }
    
    public boolean contains(T k) {
    	for (int i = 0; i < myCount; i ++) {
    		if (myValues[i] == k) {
    			return true;
    		}
    	}
    	return false;
    }
}
