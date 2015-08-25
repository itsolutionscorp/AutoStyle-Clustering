import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;


public class Sequence<T> implements Iterable<T> {

    // instance variables
    protected T[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(){}
    public Sequence(int capacity) {
        // YOUR CODE HERE
    	myValues = (T[]) new Object [capacity];
    	myCount = 0;
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
        // YOUR CODE HERE
    	if (myCount < myValues.length){
    	 this.myValues[myCount] = toBeAdded;
    	 myCount += 1;}
    	else{
    		System.err.println("Not Enough Spot");
    		System.exit(1);
    	}
    	
    }
    public boolean isEmpty(){
    	if(myCount == 0){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    public int size(){
    	return myCount;
    }

    public T elementAt(int pos){
    	if (pos > myCount -1 || pos < 0){
    		System.err.println("Wrong Index");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(T toInsert, int insertPos) {
        for (int k = 0; k <= myCount-insertPos-1; k++) {
            myValues[myCount-k] = myValues[myCount-k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }
    public String toString() {
    	String result = new String();
    	for(int k = 0; k< myCount-1;k += 1){
    		result = result + myValues[k] +" ";
    	}
    	result = result + myValues[myCount-1];
	    return result;
	}
    public T remove(int removePos){
    	T result = myValues[removePos];
    	for(int k = removePos;k < myCount-1; k += 1){
    		myValues[k] = myValues[k+1];
    	}
    	myCount -= 1;
    	return result;
    }
   
    
	public Iterator<T> iterator(){
		return new SequenceIterator();
	}
	private class SequenceIterator implements Iterator<T>{
		private int index;
		public SequenceIterator(){
			index = 0;
		}
		public boolean hasNext(){
			return index < myCount;
		}
		public  T next(){
				T current =  myValues[index];
				index += 1;
				return current;			
			
		}
		public void remove(){
			throw new UnsupportedOperationException();
		}
	}

    // other methods go here
    public static void main(String[] args){
    	Sequence<Integer> test = new Sequence<Integer>(10);
    	test.add(2);
    	test.add(3);
    	test.add(4);
    	test.add(5);
    	test.add(6);
    	
    	Iterator<Integer> it = test.iterator();    		
    	while(it.hasNext()){
    		System.out.println(it.next());
    	}
    	
    	
 
    	
    }

    }


