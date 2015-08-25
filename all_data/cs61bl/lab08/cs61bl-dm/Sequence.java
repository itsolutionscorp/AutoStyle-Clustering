import java.util.*;
public class Sequence<T> implements Iterable<T>{
	protected T[] myValues;
	private int myCount;
	
	public Iterator<T> iterator(){
		return new SeqIterator();
	}
	
	public Sequence (int capacity){
		myValues = (T[]) new Object[capacity];
	}
	
	public void add(T toBeAdded) {
        if (myCount == myValues.length){
        	System.err.println("Sequence is full");
        	System.exit(1);
        }
    	myValues[myCount] = toBeAdded;
        myCount++;
    }
	
	public void insert(T toInsert, int insertPos) {
        for (int k = myCount; k > insertPos; k--) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }
	
    public boolean isEmpty(){
    	if(myCount == 0){
    		return true;
    	}
    	return false;
    }
    
    public int size(){
    	return myCount;
    }
    
    public T elementAt(int pos){
    	if (pos >= myCount || pos < 0){
    		System.err.println("Not a valid position");
        	System.exit(1);
    	}
    	return myValues[pos];
    }
    
    public String toString(){
    	String string = "";
    	for (int i = 0; i < myCount; i++){
    		if (i == myCount - 1){
    			string = string + myValues[i];
    		}
    		else{
    			string = string + myValues[i] + " ";
    		}
    	}
    	return string;
    }
    
    public T remove(int pos){
    	if(pos<0 || pos >= myCount){
    		System.err.println("Not a valid position");
        	System.exit(1);
		}
    	T removed = myValues[pos];
    	myCount -= 1;
    	for (int i = pos; i < myCount; i++){
    		myValues[i] = myValues[i+1];
    	}
    	return removed;
    }
    
    public boolean contains(T x){
    	for(int i = 0; i < myCount; i++){
    		if(myValues[i]==x){
    			return true;
    		}
    	}
    	return false;
    }
    
    
    private class SeqIterator implements Iterator<T>{
    	private int index = 0;
    	public boolean hasNext(){
    		return index < size();
    	}
    	
    	public T next(){
    		T next = myValues[index];
    		index ++;
    		return next;
    	}
    	
    	public void remove(){
    		throw new UnsupportedOperationException("cannot remove");
    	}
    }

}


