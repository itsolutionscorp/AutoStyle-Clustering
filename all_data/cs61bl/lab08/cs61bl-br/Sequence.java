import java.util.Iterator;

public class Sequence <T> implements Iterable<T> {


	private class SequenceIterator <T> implements Iterator<T> {

		private int Index = 0; 

		@Override
		public boolean hasNext() {
			if (Index < myCount) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public T next() {
			if (hasNext()) {
				T returnValue = (T) myValues[Index];
				Index++;
				return returnValue;
			} else { 
				System.out.println("You've reached the end of the list");
				return null;
			}
		}
	}


		// instance variables
		protected T[] myValues; // sequence elements
		int myCount; // number of array cells used by sequence

		// constructor
		// capacity: actual size of the array or the (temporary) maximum
		// number of elements it can hold
		public Sequence(int capacity) {
			myCount = 0;
			myValues = (T[]) new Object[capacity];
		}

		// returns true when this sequence is empty and returns false otherwise
		public boolean isEmpty() {
			if (myCount == 0) {
				return true;
			} else {
				return false;
			}
		}

		// returns the number of values in this sequence
		public int size() {
			return myCount;
		}

		// returns the value at the given position in the sequence
		public T elementAt(int pos) {
			if (pos < myCount) {
				return myValues[pos];
			} else {
				System.err.println("You have asked for the value of an element at a position which is outside of the sequence");
				System.exit(1);
				return myValues[pos];
			}
		}

		// Add the argument to the sequence by placing it in the first
		// unused spot in the array and incrementing the count.
		// Assume that the sequence isn't full.
		public void add(T toBeAdded) {
			if (myValues.length == myCount) {
				System.err.println("There is no more room in the sequence!");
				System.exit(1);
			} else {
				myValues[myCount] = toBeAdded;
				myCount++;
			}
		}

		// toString will return a String that contains the elements of the sequence
		// separated by blanks

		public String toString() {
			String returnString = new String();
			for (int k = 0; k < myCount; k++) {
				if (k == myCount - 1) {
					returnString = returnString + myValues[k];
				} else {
					returnString = returnString + myValues[k] + " ";
				}
			}
			return returnString;
		}

		// Insert toInsert into the sequence at position pos,
		// shifting the later elements in the sequence over to make room
		// for the new element.
		// Assumptions: The array isn't full, i.e. myCount < myValues.length
		// Also, pos is between 0 and myCount, inclusive.
		public void insert(T toInsert, int pos) {
			if (pos < 0 || pos > myCount) {
				return;
			}
			if (pos == myCount ) {
				myCount++;
				myValues[pos] = toInsert;
				return;
			}
			T currentValue = null;
			for (int k = 0; k < myCount; k++) {
				if (k == pos) {
					currentValue = myValues[k];
					myValues[k] = toInsert;
					break;
				}
			}
			myCount++;
			for (int k = pos; k < myCount; k++) {
				if (k > pos) {
					T nextValue = myValues[k];
					myValues[k] = currentValue;
					currentValue = nextValue;
				}
			}
		}

		// Delete the value at the given position in the argument array,
		// shifting all the subsequent elements down and shortening the
		// sequence by 1.
		public T remove(int pos) {
			if (pos < 0 || pos >= myCount) {
				return null;
			}
			T removedPiece = myValues[pos];
			for (int k = 0; k < myCount; k++) {
				if (k >= pos) {
					if (k == myCount - 1) {
						myCount--;
					} else {
						myValues[k] = myValues[k + 1];
					}
				}
			}
			return removedPiece;
		}

		public boolean contains(T k) {
			boolean doesContain = false;
			for (int j = 0; j < myCount; j++) {
				if (myValues[j] == k) {
					doesContain = true;
				}
			}
			return doesContain;
		}

		@Override
		public Iterator<T> iterator() {
			SequenceIterator<T> myIterator = new SequenceIterator<T>();
			return myIterator;
		}
	}
