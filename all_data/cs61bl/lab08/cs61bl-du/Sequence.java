import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.Iterable;

public class Sequence <T> implements Iterable<T> {
	private T[] myItems;
	private int myCount;
	
	private class myIterator implements Iterator<T> {
		private int iteratorIndex = 0;
		private boolean done = false;
		
		public boolean hasNext() {
			if (done) {
				return false;
			} if (iteratorIndex == myItems.length - 1) {
		        done = true;
		    }
		    return true;
		}
		
		public T next() {
		    T rtn = myItems[iteratorIndex];
		    iteratorIndex++;
		    return rtn;
		}
		
		public void remove() {
			throw new NoSuchElementException("lalalala");
		}
	}
	
	public Iterator<T> iterator(){
		return new myIterator();
	}
	
	public void putItem(T item) {
        if (myCount == myItems.length) {
        	System.err.println("Sequence full");
        	System.exit(1);
        }
        myItems[myCount] = item;
        myCount += 1;
	}
	
    public void insert(T toInsert, int insertPos) {
    	if (insertPos < 0 || insertPos >= myCount) {
			System.out.println("invalid index");
    		return;
		}
		for (int i = myCount; i > insertPos; i--) {
			myItems[i] = myItems[i-1];
		}
		myItems[insertPos] = toInsert;
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
    	if (pos < 0 || pos > myCount - 1) {
    		System.err.println("invalid index");
    		System.exit(1);
    	}
    	return myItems[pos];
    }
    
    public String toString() {
    	String output = new String(myItems[0] + "");
    	for (int i=1; i<myCount; i++){
    		output += new String(" " + myItems[i]);
    	}
    	return output;
    }
    
    public T remove(int pos) {
    	if (pos < 0 || pos >= myCount) {
    		System.err.println("invalid index");
			System.exit(1);
		}
    	T removed = myItems[pos];
    	for (int i = pos; i < myCount; i++) {
    		myItems[i] = myItems[i+1];
    	}
    	myCount -= 1;
    	return removed;
    }
    
    public boolean contains(int k) {
    	for (int i = 0; i < myCount; i++) {
    		if (myItems[i].equals(k)) {
    			return true;
    		}
    	}
    	return false;
    }
    
}

