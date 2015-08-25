import java.util.Iterator;

public class Sequence <T> implements Iterable<T> {
	private T[] values;
	private int count;
	
	public Sequence (int capacity) {
		values = (T[]) new Object[capacity];
	}
	
	public boolean isEmpty() {
		return count == 0;
	}
	
	public int size() {
		return count;
	}
	
	public T elementAt(int pos) {
		if (pos > count) {
			System.err.println("Sequence index out of bounds");
			System.exit(1);
		}
		return values[pos];
	}
	
	public void add(T item) {
		values[count] = item;
		count++;
	}
	
	public T remove(int i) {
		count--;
		return values[i];
	}
	
	public String toString() {
		String seq = "";
		if (count == 0) return seq;
		for (int i = 0; i < count - 1; i++) {
			seq += values[i] + " ";
		}
		seq += "" + values[count - 1];
		return seq;
	}
	
	public void insert(T toInsert, int insertPos) {
		if (insertPos < 0 || insertPos >= count) return;
		int i = count;
		while (i > insertPos) {
			values[i] = values[i-1];
			i--;
		}
		values[insertPos] = toInsert;
		count++;
	}
	
	public boolean contains(T item) {
		for (int i = 0; i < count; i++) {
			if (values[i] == item) return true;
		}
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new SetIterator();
	}
	
	private class SetIterator implements Iterator<T> {

		int index = -1;
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return (index < count - 1);
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			index += 1;
			return values[index];
		}

	}
	
}

