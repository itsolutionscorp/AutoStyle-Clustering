
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;


public class Sequence<T> implements Iterable<T> {
    
	T[] myValues;
	int myCount;
    public Sequence(int capacity) {
    	myValues = (T[])new Object[capacity];
    }
	public Iterator<T> iterator() {
		
		return new SequenceIter();
	}
	private class SequenceIter implements Iterator<T>{
        int count=0;
        T current=myValues[0];
		public boolean hasNext() {
			
			if (count >= myCount) {
				return false;
			}
			return true;
			
		}

		public T next() {
			
			T result = current;
			count++;
			current = myValues[count];
			return result;
		}

		public void remove() {
			
			throw new UnsupportedOperationException();
		}

		public void forEachRemaining(Consumer<? super T> action) {
			
			
		}
		
	}
	
	public void forEach(Consumer<? super T> action) {
		
		
	}
	public Spliterator<T> spliterator() {
		
		return null;
	}
	
	    // Add the argument to the sequence by placing it in the first
	    // unused spot in the array and incrementing the count.
	    // Assume that the sequence isn't full.
	    public void add(T toBeAdded) {
	        // YOUR CODE HERE
	    	if (myCount == myValues.length) {
	    		raiseerror("Error: sequence is full!");
	    	}
	    	myValues[myCount]= toBeAdded;
	    	myCount++;
	    }

	    // Insert toInsert into the sequence at position insertPos,
	    // shifting the later elements in the sequence over to make room
	    // for the new element.
	    // Assumptions: The array isn't full, i.e. myCount < myValues.length
	    // Also, insertPos is between 0 and myCount, inclusive.
	    public void insert(T toInsert, int insertPos) {
	    	if (myCount == myValues.length) {
	    		raiseerror("Error: sequence is full!");
	    	}
	    	for (int k = myCount; k >=insertPos; k--) {
	            myValues[k] = myValues[k-1];
	        }
	        myValues[insertPos] = toInsert;
	        myCount++;
	    }

	    // other methods go here
	    public boolean isEmpty() {
	    	return myCount==0;
	    }
	    public int size() {
	    	return myCount;
	    }
	    public T elementAt(int pos) {
	    	if (pos>myCount ) {
	    		raiseerror("Error: position does not exist!");
	    	}
	    	return myValues[pos];
	    }
	    private void raiseerror(String error) {
	    	System.out.println(error);
	    	System.exit(1);
	    }
	    public String toString() {
	    	String result="";
	    	for (int i = 0; i< myCount-1; i++) {
	    		result = result+myValues[i]+" ";
	    	}
	    	result = result+myValues[myCount-1];
	    	return result;
	    }
	    public void remove(int pos) {
	    	if (pos>=myCount) {
	    		raiseerror("Error: position does not exist!");
	    	}
	    	for (int i = pos; i<myCount; i++) {
	    		myValues[i]=myValues[i+1];
	    	}
	    	myCount--;
	    }

}