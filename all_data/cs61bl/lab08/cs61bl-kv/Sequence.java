import java.util.Iterator;

public class Sequence<T> implements Iterable<T>{

    // instance variables
    protected T[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
        // YOUR CODE HERE
    	this.myValues = (T[]) new Object[capacity];
    	this.myCount = 0;
    }

    // Tells you the capacity of current sequence
    public int cap() {
    	return this.myValues.length;
    }
    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
        // YOUR CODE HERE
    	if (this.myCount >= this.myValues.length) {
    		System.err.println("IntSequence is full. Go away.");
    		System.exit(1);
    	}
    	this.myValues[this.myCount] = toBeAdded;
    	this.myCount++;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(T toInsert, int insertPos) {
        if (insertPos < 0 || insertPos > this.myCount) {
        	System.err.println("Out of Bounds");
    		System.exit(1);
        }
    	for (int k = this.myCount; k > insertPos; k--) {
            this.myValues[k] = this.myValues[k-1];
        }
        this.myValues[insertPos] = toInsert;
        this.myCount++;
    }

    // other methods go here
    
    public boolean isEmpty() {
    	return this.myCount == 0;
    }
    
    public int size() {
    	return this.myCount;
    }
    
    public T elementAt(int pos) {
    	if (pos < 0 || pos >= this.myCount) {
    		System.err.println("OutOfBounds");
    		System.exit(1);
    	}
    	return this.myValues[pos];
    }
    
    public String toString() {
    	if (this.isEmpty()) {
    		return "Empty sequence.";
    	}
    	String seqstring = this.myValues[0].toString();
    	//System.out.println(this.myValues[0]);
    	for (int k = 1; k < this.myCount; k++){
    		seqstring = seqstring + " " + this.myValues[k].toString();
    	}
    	return seqstring;
    }
    
	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public void remove (int pos) {
		if (pos < 0 || pos >= this.myCount) {
			System.err.println("out of bounds.");
			System.exit(1);
		}
		for (int k = pos; k < this.myValues.length - 1; k++) {
			this.myValues[k] = this.myValues[k+1];
		}
		this.myValues[this.myCount - 1] = null;
		this.myCount -= 1;
		}
	
	public boolean contains (T k) {
		for (int m = 0; m < this.myCount; m++) {
			if (this.myValues[m].equals(k)) {
				return true;
			}
		}
		return false;
	}
	private class SeqIterable implements Iterator<T>{
		
		private int index;
		private Sequence<T> s;
		
		public SeqIterable(Sequence<T> s) {
			this.index = -1;
			this.s = s;
		}

		@Override
		public boolean hasNext() {
			return this.index + 1 < this.s.size();
		}

		@Override
		public T next() {
			this.index++;
			return (T) this.s.elementAt(this.index);
			
		}

		@Override
		public void remove() {
			
		}

		}
		
	
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new SeqIterable(this);
	}
}
	
