
import java.util.*;
public class Sequence<T> implements Iterable<T> {

    // instance variables
    protected T[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence
 
    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public myIterator iterator(){
    	return new myIterator();
    }
    class myIterator<T> implements Iterator<T>{
    	private int index;
    	public myIterator(){
    		index = 0;
    	}
    	public boolean hasNext(){
    		return ((index + 1) < myValues.length);
    	}
    	public T next(){
    		int curr = index; 
    		index += 1;
    		return (T) myValues[index]; 
    	
    }
   } 
     

    public Sequence(int capacity) {
        myValues = (T[]) new Object[capacity];
        
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
        if(myCount < myValues.length){
        	myCount++;
        	this.insert(toBeAdded, myCount);
        }
        else{
        	System.err.println("No more space :(");
        	System.exit(1);
        }
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(T toBeAdded, int insertPos) {
        for (int k = insertPos + 1; k <= myCount; k++) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toBeAdded;
        myCount++;
    }

    // other methods go here
    public boolean isEmpty(){
    	if (myCount == 0){
    		return true;
    	}
    	return false;
    }
    public int size(){
    	return myCount;
    }
    public T elementAt(int pos){
    	if(pos <= (myCount -1) ){
    		return myValues[pos];
    	}
    	else{
    		System.err.println("Index does not exist");
    		System.exit(1);
    		return null;
    	}
    }
    
    public String toString(){
    	String result = new String();
    	for(int i = 0; i < myCount; i++){
    		result = result + " " + Integer.toString((Integer) myValues[i]);
    	}
    	return result;
    }

}