import java.util.Iterator;

public class Sequence<E> {
	
	private class SequenceIterator implements Iterator<E> {
		private int index = 0;

		public SequenceIterator() {

		}

		@Override
		public boolean hasNext() {
			if (index < myCount) {
				return true;
			}
			return false;
		}

		@Override
		public E next() {
			if (index < myCount) {
				E next = myValues[index];
				index++;
				return next;
			}
			return null;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

		}
	}
	
	
	private E[] myValues;
	private int myCount;
	private SequenceIterator i = new SequenceIterator();
	public Sequence(int capacity) {
		myValues = (E[]) new Object[capacity];
		myCount = 0;
	}

	public int size() {
		return myCount;
	}

	public void add(E element) {
		if (myCount < myValues.length) {
			myValues[myCount] = element;
			myCount++;
		} else {
			return;
		}
	}

	public E remove(int index) {
		if (index < 0 || index >= myCount) {
			return null;
		} else {
			myCount--;
			Object o = myValues[index];
			for (int j = index; j <= myCount; j++) {
				myValues[j] = myValues[j + 1];

			}

			return (E) o;

		}
	}

	public void insert(int index, E newValue) {
		if (index < 0 || index > myCount) {
			System.out.println("its out of myCount");
			// System.err.println();
			return;
		} else {
			E[] ref = (E[]) new Object[myValues.length];
			for (int j = 0; j < myValues.length; j++) {
				ref[j] = myValues[j];
			}
			myCount++;
			for (int k = 0; k < myCount; k++) {
				if (k < index) {
					myValues[k] = ref[k];
				} else if (k == index) {
					myValues[k] = newValue;
				} else if (k > index) {
					myValues[k] = ref[k - 1];
				}
			}
		}
	}

	public boolean isEmpty() {
		if (myCount == 0) {
			return true;
		}
		return false;

	}

	public E elementAt(int pos) {
		if (myValues[pos] == (E) new Integer(0)) {
			System.err.println("NO element at this position");
			System.exit(1);
		}
		return myValues[pos];
	}

	public String toString() {
		String string = new String();
		for (int k = 0; k < myCount; k++) {
			string = string + myValues[k];
			if (k != myCount - 1) {
				string += " ";
			}
		}
		return string;
	}

	public boolean contains(E e) {
		for (int k = 0; k < myCount; k++) {
			if (myValues[k].equals(e)) {
				return true;
			}

		}
		return false;
	}

	public static void main(String[] args) {
		Sequence<Integer> s1 = new Sequence<Integer>(5);
		Sequence<String> s2 = new Sequence<String>(5);

		s1.add(9);
		s1.add(9);


		s1.insert(0, 6);
		System.out.println(s1.toString());

		while(s1.i.hasNext()){
			System.out.println(s1.i.next());
		}
		
		//
		// s1.add(4);
		// s1.add(3);
		//
		// System.out.println(s1.toString());
		//
		// s1.remove(3);
		//
		// System.out.println(s1.toString());
		//
		// s2.add("a");
		// s2.add("c");
		//
		// System.out.println(s2.toString());
		//
		// s2.add("w");
		//
		// System.out.println(s2.toString());
		//
		// s2.remove(1);
		//
		// System.out.println(s2.toString());

		// s1.insert(3, new Integer(5));
		// s1.remove(0);
		// System.out.println(s1.toString());
		//
		// s2.insert(3, "a");
		// s2.remove(0);
		// System.out.println(s2.toString());
	}


}
