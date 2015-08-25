import java.awt.Point;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;


public class Sequence <T> implements Iterator <T>{

	private Object[] myValues;
	private int myCount;
	public Iterator<T> iter;
	
	public <T> Sequence(int capacity){
		myValues = new Object[capacity];
		myCount = 0;
	}
	public void putValue (T value) {
		myValues[myCount] = (T) value;
		myCount++;
	}
	public Object getValue(int index){
		return myValues[index];
	}
	@SuppressWarnings("unchecked")
	public void initIterator(){
		iter = new Iterator<T>();
	}
	public boolean hasNext(){
		return iter.hasNext();
	}
	@SuppressWarnings("unchecked")
	public T next(){
		return (T) iter.next();
	}
	
	@SuppressWarnings("hiding")
	private class Iterator<T> {
		private int IteratorIndex;
		private Iterator(){
			IteratorIndex = -1;
		}
		public boolean hasNext(){
			return IteratorIndex + 1 < myCount;
		}
		public Object next(){
			IteratorIndex++;
			return myValues[IteratorIndex];
		}
		
	}



}
