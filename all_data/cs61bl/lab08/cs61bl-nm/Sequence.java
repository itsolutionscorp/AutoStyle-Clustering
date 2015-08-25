import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;


public class Sequence<T> implements Iterable<T> {
	T[] myValues;
	int myCount;
	
	public Sequence(int capacity) {
		myValues = (T[]) new Object[capacity];
		
	}
	
    public void add(T toBeAdded) {
    	if (this.myCount == this.myValues.length) {
    		System.err.println("Maximum capacity for set has been reached");
    		System.exit(1);
    		return;
    	}
        this.myValues[myCount] = toBeAdded;
        this.myCount++;
    }

    public void insert(T toInsert, int insertPos) {
    	if (insertPos < 0 || insertPos >= this.myCount) {
    		System.err.println("Index Out of Bound");
    		System.exit(1);
    	}
        for (int k = this.myValues.length-1; k > insertPos; k--) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        if (this.myCount != this.myValues.length) {
        	myCount++;
        }
    }

    // other methods go here
    
    public boolean isEmpty() {
    	return this.myCount == 0;
    }
    public int size() {
    	return this.myCount;
    }
    
    public T elementAt(int pos) {
    	if (pos<0 || pos >= this.myCount) {
    		System.err.println("Index Out of Bound");
    		System.exit(1);
    	}
    	return this.myValues[pos];
    }
    
    public void remove(int pos) {
    	if (pos<0 || pos >= this.myCount) {
    		System.err.println("Index Out of Bound");
    		System.exit(1);
    		return;
    	}
    	for (int i=pos;i<this.myCount-1;i++) {
    		this.myValues[i] = this.myValues[i+1];
    	}
    	this.myCount--;
    }
    
    public boolean contains(T k) {
    	for (int i=0; i<this.myCount; i++) {
    		if (this.myValues[i] == k) {
    			return true;
    		} else {
    			continue;
    		}
    	}
    	return false;
    }
    
    public String toString() {
    	String result = "";
    	for (int i=0;i<this.myCount-1;i++) {
    		result += this.myValues[i] + " ";
    	}
    	result += this.myValues[this.myCount-1] + "";
    	return result;
    }
    
    public static void main(String args[]) {
    	Sequence<String> seq1 = new Sequence<String>(6);
    	seq1.add("First item");
    	seq1.add("Second Item");
    	System.out.println(seq1.contains("First item"));
    	System.out.println(seq1.toString());
    	
    	Iterator<String> seq1Iter = seq1.iterator();
    	System.out.println(seq1Iter.hasNext());	//true
    	System.out.println(seq1Iter.next());	//First item
    	System.out.println(seq1Iter.hasNext());	//true
    	System.out.println(seq1Iter.next());	//Second item
    	System.out.println(seq1Iter.hasNext());	//false
    	
    	Sequence<Integer> seq2 = new Sequence<Integer>(5);
    	seq2.add(5);
    	seq2.add(10);
    	System.out.println(seq2.contains(11));
    	
    	Iterator<Integer> seq2Iter = seq2.iterator();
    	System.out.println(seq2Iter.hasNext());	//true
    	System.out.println(seq2Iter.next());	//5
    	System.out.println(seq2Iter.hasNext());	//true
    	System.out.println(seq2Iter.next());	//10
    	System.out.println(seq2Iter.hasNext());	//false
    	
    }

	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new myIterator<T>();
	}

	public void forEach(Consumer<? super T> action) {
		// TODO Auto-generated method stub
		
	}

	public Spliterator<T> spliterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	class myIterator<E> implements Iterator<E> {
		private int nextIndex;
		
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return nextIndex != myCount;
		}

		public E next() {
			// TODO Auto-generated method stub
			int curIndex = nextIndex;
			nextIndex++;
			return (E) myValues[curIndex];
			
		}

		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}

		public void forEachRemaining(Consumer action) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
