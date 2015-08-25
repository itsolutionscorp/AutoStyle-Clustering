import java.util.*;
import java.util.function.Consumer;

public class Sequence <K> implements Iterable<K> {
	protected K[] myValues;
	int myCount;

	public Sequence(int capacity) {
		myValues = (K[]) new Object[capacity];
		myCount = 0;
	}

	public void add(K toBeAdded) {
        if (myCount - 1 == myValues.length) {
            System.err.println("Sequence is full.");
            System.exit(1);
        }
        myValues[myCount] = toBeAdded;
        myCount += 1;
	}

    public void remove(int pos) {
        if (pos < 0 || pos >= myCount) {
            System.err.println("Negative position or doesn't exist.");
            System.exit(1);
        }
        for (int k = 0; k < myCount; k += 1) {
            if (k >= pos) {
                myValues[k] = myValues[k + 1];
            }
        }
        myCount -= 1;
    }

    public void insert(K toInsert, int insertPos) {
        for (int k = myCount; k > insertPos; k -= 1) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }

    // Returns true when the sequence is empty, else false.
    public boolean isEmpty() {
        if (myCount == 0) {
            return true;
        }
        return false;
    }

    // Returns the number of values in this sequence
    public int size() {
        return myCount;
    }

    // Returns the value at the given position in the sequence
    public K elementAt(int pos) {
        if (pos > myValues.length) {
            System.err.println("Index out of bounds.");
            System.exit(1);
        }
        return myValues[pos];
    }

    public boolean contains(K k) {
        for (int n = 0; n < myCount; n += 1) {
            if (myValues[n] == k) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder elems = new StringBuilder();
        for (int k = 0; k < myValues.length - 1; k += 1) {
            elems.append(myValues[k] + " ");
        }
        elems.append(myValues[myValues.length - 1]);

        return elems.toString();
    }

    private class SeqIter implements Iterator<K> {
        /** Stores the current key. */
        int index;
    	
        public SeqIter() {
        	index = 0;
        }

        /** Return true if there are more elements left to return. */
        public boolean hasNext() {
            if (myValues[index + 1] != null) {
                return true;  
            }
            return false;
        }

        /** Returns the next element (where E is the formal type parameter of the iterator). */
        public K next() {
            K elem;
            elem = (K)myValues[index];
            index++;
            return elem;
        }

        /* Remove the most recently returned element. */
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

	@Override
	public Iterator<K> iterator() {
		// TODO Auto-generated method stub
		SeqIter seqIter = new SeqIter();
		return seqIter;
	}
}