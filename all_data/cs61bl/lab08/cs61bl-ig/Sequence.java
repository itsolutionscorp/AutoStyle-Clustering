import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Sequence<T> implements Iterable<T> {

	// instance variables
	private T[] myValues; // sequence elements
	int myCount; // number of array cells used by sequence

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
		// YOUR CODE HERE
		myValues[myCount] = toBeAdded;
		myCount++;

	}

	// Insert toInsert into the sequence at position insertPos,
	// shifting the later elements in the sequence over to make room
	// for the new element.
	// Assumptions: The array isn't full, i.e. myCount < myValues.length
	// Also, insertPos is between 0 and myCount, inclusive.
	public void insert(T toInsert, int insertPos) {
		for (int k = myCount; k > insertPos; k--) {
			myValues[k] = myValues[k - 1];
		}
		myValues[insertPos] = toInsert;
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
		if (pos < 0 || pos >= myCount) {
			System.err.println("position does not exist");
			System.exit(1);
		}
		return myValues[pos];
	}
	public Iterator iterator(){
		return new SequenceIterator();
	}

	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < myCount; i++) {
			if (i == myCount - 1) {
				result += myValues[i];
			} else {
				result += myValues[i] + " ";
			}
		}
		return result;
	}

	public T remove(int pos) {

		T removed = myValues[pos];
		for (int i = pos; i < myCount - 1; i++) {
			myValues[i] = myValues[i + 1];
		}
		myCount--;
		return removed;
	}

	public boolean contains(T k) {
		for (int i = 0; i < myCount; i++) {
			if (myValues[i].equals(k)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Sequence<Integer> s = new Sequence<Integer>(5);
		s.add(1);
		s.add(2);
		s.add(3);
		s.add(4);
		System.out.println(s.toString());
		System.out.println(s.contains(4));
		s.remove(2);
		System.out.println(s.toString());
		
		Iterator<Integer> iter = s.iterator();
		while(iter.hasNext()) {
			Integer x = iter.next();
			System.out.println(x);
		}
		s.insert(4, 0);
		System.out.println(s.toString());

	}

	private class SequenceIterator implements Iterator<T> {
		
		int index;
		
		public SequenceIterator() {
			index=0;
		}
		
		public boolean hasNext() {
			if (index<myCount){
				return true;
			}
			return false;
		}
		
		public T next() {
			index++;
			return /*(T)*/ myValues[index-1];
		}
		
		public void remove() {     }
		
	}
}
