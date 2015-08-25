import java.awt.Point;
import java.util.Iterator;


public class Sequence<T> implements Iterable<T> {
	private T[] myValues;
	private int myCount;
	
	  public Sequence(int capacity) {
	    	myValues = (T[]) new Object[capacity];
	    }

	    public void add(T toBeAdded) {
	  	    	if (myCount == myValues.length) {
	    		System.err.println("Array is full.");
	    		System.exit(1);
	    	}
	    	myValues[myCount] = toBeAdded;
	    	myCount++;

	    }
	    
	    public void insert(T toInsert, int insertPos) {
	    	this.add(toInsert);
	        for (int k = myValues.length - 1; k > insertPos; k--) {
	            myValues[k] = myValues[k-1];
	        }
	        myValues[insertPos] = toInsert;
	    }
	    
	    public boolean isEmpty(){
	    	if (myCount == 0) {
	    		return true;
	    	} else {
	    		return false;
	    	}
	    }
	    
	    public int size(){
	    	return myCount;    	
	    }
	    
	    public T elementAt(int pos) {
	    	if (pos > myCount - 1 || pos < 0){
	    		System.err.println("Index out of bounds.");
	    		System.exit(1);
	    	}
	    	return myValues[pos];
	    }
	    
	    public String toString() {
	    	if (myCount == 0) {
	    		System.err.println("Array is empty.");
	    		System.exit(1);
	    	}
	    	String s = new String();
	    	for (int i = 0; i < myCount; i++) {
	    		if (i == myCount - 1) {
	    			s += myValues[i];
	    			return s;
	    		}
	    		s += myValues[i] + " ";	
	    	}
	    	System.out.print(s);
	    	return s;
	    }
	    
	    public T remove(int intPos) {
	    	if (intPos < 0 || intPos >= myValues.length) {
	    		System.err.println("Can't remove from that position.");
	    		System.exit(1);
	    	}
	    	T removed = myValues[intPos];
	    	for (int i = intPos; i < myValues.length - 1; i++) {
	    		myValues[i] = myValues[i+1];
	    	}
	    	myValues[myValues.length - 1] = null;
	    	myCount --;
	    	return removed;    	
	    }
	    
	    public boolean contains(T k) {
	    	boolean found = false;
	    	for (int i = 0; i < myValues.length; i++) {
	    		if (myValues[i] == k) {
	    			found = true;
	        		break;
	    		}	
	    	}
	    	return found;
	    }
		

	    public Iterator<T> iterator(){
	    	 Iterator<T> iter = new nestedIterator<T>();
	    	 return iter;
	    }
	    
	    
	    
	    private class nestedIterator<T> implements Iterator<T> {
	    	int nextIndex = 0;
	    	
	    	public boolean hasNext() {
	    		if (isEmpty() == false){
	        		return nextIndex < myValues.length;
	        	}
	        	else {
	        		return false;
	        	}
	    	}
	    	public T next(){
	    		T rtn = (T) myValues[nextIndex];
	    	    nextIndex++;
	    	    return rtn;
	    	}
	  
	   }
}


