import java.util.Iterator;

public class Sequence<T> implements Iterable<T> {

	private T[] myValues;

	private int myCount;

	public Sequence(int capacity) {

		myValues = (T[]) new Object[capacity];

		myCount = 0;

	}

	public void add(T toBeAdded) {

		if (myCount == myValues.length) {

			System.err.println("");

			System.exit(1);

		} else {

			myValues[myCount] = toBeAdded;

			myCount++;

		}

	}

	public void insert(T toInsert, int insertPos) {

		for (int k = myCount - 1; k >= insertPos; k--) {

			myValues[k + 1] = myValues[k];

		}

		myCount++;

		myValues[insertPos] = toInsert;

	}

	public T remove(int pos) {

		T i = myValues[pos];

		for (int k = pos; k < myCount - 1; k++) {

			myValues[k] = myValues[k + 1];

		}

		myValues[myCount] = null;

		myCount--;

		return i;

	}

	public String toString() {

		String s = new String();

		for (int k = 0; k < myCount; k++) {

			s += myValues[k] + " ";

		}

		return s;

	}

	public T get(int index) {

		return myValues[index];

	}


	public Iterator<T> iterator() {

		return new SeqIter();

	}

	private class SeqIter implements Iterator<T> {

		int nextIndex;

		public SeqIter() {

			nextIndex = 0;

		}

		public boolean hasNext() {

			return nextIndex < myCount;

		}

		public T next() {

			T valToReturn = (T) myValues[nextIndex];

			nextIndex += 1;

			return valToReturn;

		}

	}
	public static void main(String[] args) {

		Sequence<Integer> s = new Sequence<Integer>(5);

		s.add(5);

		s.add(3);

		s.add(5);

		s.insert(17, 1);

		s.remove(2);
		Iterator b = s.iterator(); 
		System.out.println(b.next());
		System.out.println(b.next());
		System.out.println(b.hasNext());
		System.out.println(b.next());
		System.out.println(b.hasNext());
		

		

	}
	
	

}