import java.util.Iterator;

public class Sequence <T> implements Iterable <T>{
	// instance variables
	protected T [] myValues;
	private int myCount;
	private int index;

	// constructor
	public Sequence(int capacity) {
		myValues = (T[]) new Object [capacity]; // merp...
	}

	private class MyIter implements Iterator <T>{
		
		public MyIter(){
			index = 0;
		}

		public boolean hasNext(){
			return index < myCount;
		}

		public T next(){
			index++;
			return elementAt(index);
		}
	}

	public Iterator <T> iterator(){
		MyIter i = new MyIter();
		return i;
	}

	public void add(T toBeAdded) {
		if(myCount == myValues.length){
			System.err.println("Index Error");
			System.exit(1);
		}
		myValues[myCount] = toBeAdded;
		myCount ++;
	}

	public void insert(T toInsert, int insertPos) {
		if (insertPos < 0 || insertPos >myCount) {
			return;
		}
		for (int k = myCount; k > insertPos; k--) {
			myValues[k] = myValues[k-1];
		}
		myValues[insertPos] = toInsert;
		myCount++;
	}

	// other methods go here
	public boolean isEmpty(){
		return myCount==0;
	}

	public int size(){
		return myCount;
	}

	public T elementAt(int pos){
		if(pos > myCount){
			System.err.println("Index Error");
			System.exit(1);
		}
		return myValues[pos];
	}

	public String toString(){
		String vals = "";
		for(int i = 0; i < myCount; i++){
			vals = vals + myValues[i];
			if(i != myCount-1){
				vals+=" ";
			}
		}
		return vals;
	}

	public void remove(int pos) {
		if (pos < 0 || pos >= myCount) {
			return;
		}
		for(int i = pos; i < myCount-1; i++){
			myValues[i] = myValues[i+1];
		}
		myCount--;
	}

	public boolean contains(T k){
		for(int i = 0; i < myCount; i++){
			if(elementAt(i) == k){
				return true;
			}
		}
		return false;
	}	
}