import java.util.*;
public class Sequence <T> implements Iterable<T>{

	// instance variables
	protected T[] myValues;   // sequence elements
	int myCount;                // number of array cells used by sequence
	

	// constructor
	// capacity: actual size of the array or the (temporary) maximum
	// number of elements it can hold
	public Sequence(int capacity) {
		// YOUR CODE HERE
		
		myValues = (T[]) new Object[capacity];
		myCount = 0;
		
	}

	// Add the argument to the sequence by placing it in the first
	// unused spot in the array and incrementing the count.
	// Assume that the sequence isn't full.
	public void add(T toBeAdded) {
		// YOUR CODE HERE
		if (myCount == myValues.length) {
			System.err.println("no more space");
			System.exit(1);
		}
		
		
	}

	// Insert toInsert into the sequence at position insertPos,
	// shifting the later elements in the sequence over to make room
	// for the new element.
	// Assumptions: The array isn't full, i.e. myCount < myValues.length
	// Also, insertPos is between 0 and myCount, inclusive.
	public void insert(T toInsert, int insertPos) {
		for (int k = insertPos + 1; k <= myCount; k++) {
			myValues[k] = myValues[k-1];
		}
		myValues[insertPos] = toInsert;
		myCount++;
	}

	// other methods go here
	public boolean isEmpty(){
		return (myCount==0);
	}
	public int size(){
		return myCount;
	}
	public T elementAt(int pos) {
		if (pos > myCount-1 ){
			System.err.println("Out of bound");
			System.exit(1);
		}
		return myValues[pos];
	}
	public String toString(){
		String n = "";
		for (int k = 0; k < myCount; k++) {
			n = n + " " + myValues[k];
		}
		return n;
	}

	public void remove (int pos) {
		if (pos < 0 || pos >= myCount) {
			return;
		}
		// YOUR CODE HERE
		
		for (int k = pos; k < myCount-1; k++){
			myValues[k] = myValues [k + 1];
		}
		myValues[myCount - 1] = null;
		myCount --;
	}
	
	public boolean contains (T n) {
		for (int pos = 0; pos < myCount; pos++) {
			if (myValues[pos] == n) {
				return true;
			}
		}
		return false;
	}
	public Iterator iterator() {
		
		return new seqIterator(); 
	}
	private class seqIterator implements Iterator{
		int nextIndexToReturn = 0;
		public boolean hasNext(){
			return nextIndexToReturn < myCount;
		}
		public T next(){
			return myValues[nextIndexToReturn];
		}
	}
	
}

