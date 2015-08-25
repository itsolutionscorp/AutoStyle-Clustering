import java.util.*;

public class Sequence<T> implements Iterable<T> {

	/**
	 * @param args
	 */
	 // instance variables
    protected T[] myValues;   // sequence elements
    private int myCount;
    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
    	myValues = (T[]) new Object[capacity];
    	myCount=0;
    }
    
    public void add(T addedElement){
    	myValues[myCount] = addedElement;
    	myCount ++;
    }

    public void insert(T element, int pos){
    	for (int k = myCount; k >= pos+1; k--) {
          myValues[k] = myValues[k-1];
  		System.out.println(myValues[k]);
      }
      myValues[pos] = element;
      myCount++;	
		}	
    

public void remove(int pos) {
	int p=pos+1;
	for(int i=p;i<myValues.length;i++){
		myValues[i-1] = myValues[i];
	}
	myCount--;
	myValues[myValues.length - 1] = null;
}

public int size(){
	return myCount;
}

public T elementAt(int pos){
	if(pos<0 || pos>myValues.length-1){
		System.err.println("Out of bounds!!!!!!");
		System.exit(1);
	}
	return myValues[pos];
}

public boolean contains(T k) {
	for(int i = 0; i < myCount; i++) {
		if (elementAt(i).equals(k)) {
			return true;
		}
	} return false;
}

public Iterator<T> iterator() {
	return new SequenceIterator<T>();
}

	private class SequenceIterator<T> implements Iterator<T> {
		int index = 0;
		public boolean hasNext(){
			return (index < myCount);
		}
		public T next() {
			int current = index;
			index++;
			return (T) elementAt(current);
		}
	}
}



