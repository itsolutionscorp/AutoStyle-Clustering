import java.util.Iterator;

public class Sequence<T> implements Iterable<T>{
	
	protected T[] myValues;
	int myCount, nextIndex;

	public Sequence(int capacity){
		myCount = 0;
		myValues = (T[]) new Object[capacity];
	}
	
	public boolean isEmpty(){
    	if(myCount == 0)
    		return true;
    	else
    		return false;
    }
    
    public int size(){
    	return myCount;
    }
    
    public T elementAt(int pos){
    	if(pos < 0 || pos > myCount){
    		System.err.println("Index out of bounds");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    
    public void add(T toBeAdded) {
    	if(myCount > myValues.length){
    		System.err.println("Sequence is full");
    		System.exit(1);
    	}
    	myValues[myCount] = toBeAdded;
    	myCount++;
    }
    
    public void insert(T toInsert, int insertPos) {
    	if (insertPos < 0 || insertPos > myCount) {
			return;
		}
		for(int i = myCount; i > insertPos; i--){
			myValues[i] = myValues[i-1];
		}
		myValues[insertPos] = toInsert;
		myCount++;
    }
    
    public void remove(int pos){
    	if (pos < 0 || pos >= myCount) {
			return;
		}
    	for(int i = pos; i < myCount - 1; i++){
    		myValues[i] = myValues[i+1];
    	}
    	myValues[myCount - 1] = null;
    	myCount--;
    }
	
    public boolean contains(T k){
    	for(int i = 0; i < myCount; i++){
    		if(myValues[i].equals(k))
    			return true;
    	}
    	return false;
    }
    
    public String toString(){
    	String values = "";
    	for(int i = 0; i < myCount; i++){
    		values += myValues[i].toString() + " ";
    	}
    	return values.trim();
    }
    
    public Iterator<T> iterator() {
    	nextIndex = 0;
		return new SequenceIterator<T>();
	}
    
    private class SequenceIterator<T> implements Iterator<T>{

		@Override
		public boolean hasNext() {
			return nextIndex < myCount;
		}

		@Override
		public T next() {
			T nextVal = (T) myValues[nextIndex];
			nextIndex++;
			return nextVal;
		}
		
    	
    }
    
    
    
}
