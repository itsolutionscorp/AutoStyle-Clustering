import java.util.Iterator;

public class Sequence <T> implements Iterable<T>{
	
	private Object[] myValues;
	private int myCount;
	

	@Override
	public Iterator<T> iterator() {
		return new SequenceIterator();
	}
	
	private class SequenceIterator implements Iterator<T> {
		private int index;

		public SequenceIterator() {
			this.index = -1;
		}

		public T next() {
			index++;
			return (T) myValues[index];
		}
		
		public boolean hasNext() {
			if (myCount == 0)
				return false;
			return index < myCount - 1;
		}

		@Override
		public void remove() {
			
			
		}
	}
	
	public Sequence (int capacity) {
		this.myValues = new Object[capacity];
		this.myCount = 0;
	}
	
	/**
	 * Returns whether or not sequence is empty
	 * @return true if sequence is empty, false if otherwise
	 */
	public boolean isEmpty() {
    	return (myValues == null || myCount == 0);	}
	
	/**
	 * Returns the size of sequence
	 * @return size of sequence
	 */
	public int size() {
		return myCount;
	}
	
	/**
	 * Returns element at the position pos in sequence
	 * @param pos position to return in sequence
	 * @return the pos element in sequence
	 */
	public T elementAt (int pos) {
    	if (pos < 0 || pos >= size()) {
    		System.out.println("Out of range");
    		System.exit(1);
    	}
    	return (T) myValues[pos];
	}
	
	/**
	 * Add the argument to the sequence by placing it in the first
	 * unused spot in the array and incrementing the count.
	 * Assume that the sequence isn't full.
	 * @param toBeAdded position to return element
	 * @return null
	 */
	public void add (T toBeAdded) {
    	if (myCount >= myValues.length) {
    		System.out.println("Out of range");
    		System.exit(1);
    	}
    	myValues[myCount] = toBeAdded;
        myCount++;
	}
	
	/**
	 * Insert toInsert into the sequence at position insertPos,
	 * shifting the later elements in the sequence over to make room
	 * for the new element.
	 * Assumptions: the array isn't full, i.e. myCount < myValues.length.
	 * 		Also, insertPos is between 0 and myCount, inclusive.
	 * @param toInsert to be inserted into the Sequence
	 * @param insertPos position to insert into Sequence
	 */
	public void insert (T toInsert, int insertPos) {
        for (int k = myCount; k > insertPos; k--) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }
	
	/**
	 * Remove the element at pos
	 * @param pos element to be removed
	 */
    public void remove(int pos) {
		for (int i = 0; i< myCount -1 ; i++) {	
			if (i < pos) {
				continue;
			} else {
				myValues[i] = myValues[i+1];			
			}
		}
		myValues[myCount - 1] = 0;
		myCount--;
    }

    /**
     * Return whether or not sequence contains 
     * @param k
     * @return
     */
    public boolean contains(Object k) {
    	for (int i = 0; i < myCount; i++) {
    		if (myValues[i].equals(k)) {
    			return true;
    		}
    	}
    	return false;
    }
	
	public String toString() {
    	String elements = "";
    	for (int i = 0; i < myCount - 1; i++) {
    		elements += myValues[i] + " ";
    	}
    	elements += myValues[myCount - 1];
    	return elements;
	}
}
