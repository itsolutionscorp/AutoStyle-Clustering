import java.util.Iterator;


public class Sequence <E> implements Iterable <E> {
	// instance variables
    protected E[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence
    
    private class SequenceIterator <E> implements Iterator <E> {
    	public int iteratorIndex;
    	
    	public SequenceIterator() {
    		iteratorIndex = 0;
    	}
    	
    	public boolean hasNext() {
    		if (iteratorIndex < myCount) {
    			return true;
    		} else {
    			return false;
    		}
    	}
    	
    	public E next() {
    		Object nextValue = myValues[iteratorIndex];
    		iteratorIndex++;
    		return (E) nextValue;
    	}
    	
    	public void remove() throws UnsupportedOperationException {
    		throw new UnsupportedOperationException();
    	}

    }
    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
        // YOUR CODE HERE
    	myValues = (E[]) new Object[capacity];
    	myCount = 0;
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(E toBeAdded) {
        // YOUR CODE HERE
    	if (myCount < myValues.length) {
	    	myValues[myCount] = toBeAdded;
	    	myCount++;
    	} else {
    		System.err.println("NO MORE ROOM");
    		System.exit(1);
    	}
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(E toInsert, int insertPos) {
        for (int k = this.size() - 1; k >= insertPos; k--) {
            myValues[k+1] = myValues[k];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }

    // other methods go here
    
    public int size() {
    	return myCount;
    }
    
    public boolean isEmpty() {
    	return myCount == 0;
    }
    
    public E elementAt(int pos) {
    	if(pos >= myCount || pos < 0) {
    		System.err.println("INDEX DOES NOT EXIST");
    		System.exit(1);
    		return null;
    	} else {
    		return myValues[pos];
    	}
    }
    
    public String toString() {
    	String result = "";
    	if(this.size() > 0) {
    		for(int i = 0; i < this.size(); i++) {
    			if (i == 0) {
    				result = result + this.elementAt(i);
    			} else {
    				result = result + " " + this.elementAt(i);
    			}
    		}
    	} else {
    		return result;
    	}
    	return result;
    }
    
    public E remove(int pos) {
    	E toBeRemoved = this.elementAt(pos);
    	for(int i = pos; i < this.size() - 1; i++) {
    		myValues[i] = myValues[i + 1];
    	}
    	myCount--;
    	return toBeRemoved;
    }
    
    public SequenceIterator<E> iterator() {
    	return new SequenceIterator<E>();
    }
    
//    public static void main(String[] args) {
//    	Sequence<String> seqString = new Sequence<String>(3);
//    	seqString.add("How");
//    	seqString.add("Are");
//    	seqString.add("You");
//    	Iterator<String> myIter = seqString.iterator();
//    	while (myIter.hasNext()) {
//    		System.out.println(myIter.next());
//    	}
//    }
}
