import java.util.Iterator;


public class Sequence <T> implements Iterable<T> {

	
	
	protected T[] myValues;
	private int myCount;
	
	public Sequence (int capacity) {
		myValues =   (T[]) new Object[capacity];
	}
	
	public void add(T obj) {
		if (myCount >= myValues.length) {
			System.err.println("No more space in array");
			System.exit(1);
		}
		myValues[myCount] = obj;
		myCount++;
	}
	
	public void insert(T toInsert, int insertPos) {
		if (insertPos > myCount || myCount >= myValues.length) {
    		System.err.println("Position is out of range");
        	System.exit(1);
    	}
		T temp1 = myValues[insertPos];
    	T temp2;
        for (int k = insertPos + 1; k <= myCount; k++) {
        	temp2 = myValues[k];
            myValues[k] = temp1;
            temp1 = temp2;
        }
        myValues[insertPos] = toInsert;
        myCount++;
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
		if (pos > myCount) {
    		System.err.println("Position is out of range");
        	System.exit(1);
        	return null; // only to compile so thinks always will return int
        
      	} else {
      		return myValues[pos];
      	}
	}
	
	public String toString() {
    	String output = "";
    	for (int k = 0; k < myCount; k++) {
    		output = output + " " + myValues[k];
    	}    	
    	return output.substring(1);
    }
	
	public void remove(int removePos) {
    	if (removePos > myCount) {
    		System.err.println("Position is out of range");
        	System.exit(1);
    	}
    	else {
    		for (int i = removePos; i < myCount; i++) {
    			if (i == myCount - 1) {
    				myValues[i] = null;
    				continue;
    			}
			myValues[i] = myValues[i+1];
    		}
    		myCount --;
    	}
    }
	
	public boolean contains (T obj) {
		for (int i = 0; i <= myCount; i++) {
			if (myValues[i] == obj) {
				return true;
			}
		} return false;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new SequenceIterator();
	}
	
	private class SequenceIterator  implements Iterator<T> {
		
		int index = -1;
		
		public void initSequenceIterator() {
			index = -1;
		}
		public T next() {
			index ++;
			return myValues[index];
		}
		
		public boolean hasNext() {
			return index < myCount - 1;
		}
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException("unsupported");
			
		}
		
		
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sequence<String> tester = new Sequence<String>(10);
		tester.add("hello");
		tester.add("go");
		tester.add("bears");
		
		//Iterator testerIter = tester.iterator();
		
		for (String elem : tester) {
			System.out.println(elem);
		}

	}
}
