import java.util.Iterator;


public class Sequence<T> implements Iterable<T>{
	
	protected int myCount;
	protected T[] myValues;
	
	public Iterator<T> iterator() {
		return new SequenceIterator();
	}
	
	public class SequenceIterator implements Iterator<T> {
		private int index;
		
		public SequenceIterator() {
			index = -1;
		}
		
		public boolean hasNext() {
			return (index + 1) < myCount;
		}
		
		public T next() {
			index ++;
			return myValues[index];
		}

		@Override
		public void remove() {
			//
		}
	}
	
	public static void insert ( Object[] values, int pos, Object newThing) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		Object[] copy = values.clone();
		for (int i = 0; i < values.length; i++) {
			if (i == pos) {
				values[i] = newThing;
			} else if (i > pos) {
				values[i] = copy[i - 1];
			}
		}
	}
	
	public static void remove (Object[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for (int i = 0; i < values.length; i++) {
			if (i >= pos && i != values.length - 1) {
				values[i] = values[i + 1];
				continue;
			} else if (i == values.length - 1) {
				values[i] = 0;
			}
		}
	}
	
    public Sequence(int capacity) {
        myCount = capacity;
        myValues = (T[]) new Object[50];
    }
    
    public void add(T toBeAdded) {
        if (myCount >= myValues.length) {
        		System.err.println("no more space to add");
        		System.exit(1);
        } else {
        		myValues[myCount] = toBeAdded;
        		myCount += 1;
        }
    		
    }
    public void insert(T toInsert, int insertPos) {
		if (insertPos > myCount) {
			System.err.println("position out of bounds");
			System.exit(1);
		} else {
			insert(myValues, insertPos, toInsert);
			myCount++;
		}
}

	public boolean isEmpty() {
		for (int i = 0; i < myCount; i++) {
			if (myValues[i] != null) {
				return false;
			}
		}
		return true;
	}
	
	public int size() {
		return myCount;
	}
	
	public T elementAt(int pos) {
		if (pos >= myCount) {
			System.err.println("position out of bounds");
			System.exit(1);
			return null;
		} else {
			return myValues[pos];
		}
	}
	
	public String toString() {
			String start = "";
			if (myCount > 0) {
				start = start + myValues[0];
				for (int i = 1; i < myCount; i++) {
					start = start + " " + myValues[i];
				}
			}
			return start;
	}
	
	public void remove(int pos) {
		if (pos >= myCount) {
			System.err.println("position out of bounds");
			System.exit(1);
		} else {
			remove(myValues, pos);
			myCount--;
		}
	}
	
	public boolean contains(T k) {
		for (int i = 0; i < myValues.length; i++) {
			if (myValues[i] != null && myValues[i].equals(k)) {
				return true;
			}
		}
		return false;
	}
}
