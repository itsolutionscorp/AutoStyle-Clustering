import java.util.Iterator;


public class Sequence<T> implements Iterable<T> {

    // instance variables
    protected T[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
        this.myValues = (T[]) new Object[capacity];
        this.myCount = 0;
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
    	if(myCount >= this.myValues.length){
    		System.err.println("The array is full.");
    		System.exit(1);
    	}
        this.myValues[myCount] = toBeAdded;
        this.myCount++;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(T toInsert, int insertPos) {
    	myCount++;
    	for (int i = myCount-1;i>insertPos;i--){
			myValues[i]=myValues[i-1];
		}
        myValues[insertPos] = toInsert;
    }

    public boolean isEmpty(){
    	return (this.myCount==0);
    }
    
    public int size(){
    	return myCount;
    }
    
    public Object elementAt(int pos){
    	if(pos>myCount-1){
    		System.err.println("Index not found");
    		System.exit(1);
    	}
    	return this.myValues[pos];
    }
    
    public String toString(){
    	String result = "";
    	if(myCount==0)
    		return result;
    	for(int i = 0;i<myCount-1;i++){
    		result +=myValues[i]+" ";
    	}
    	result+=myValues[myCount-1];
    	return result;
    }
    
    public Object remove (int pos) {
    	Object result = this.elementAt(pos);
		for (int i = pos;i<myCount-1;i++){
			myValues[i]=myValues[i+1];
		}
		myCount--;
		return result;
    }
    
    public boolean contains(T k){
    	for (int i=0;i<=myCount-1;i++){
    		if (myValues[i]==k)
    			return true;
    	}
    	return false;
    }
    
    public Iterator iterator() {
    	return new SequenceIter<T>();
    }
    
    private class SequenceIter<T> implements Iterator<T>{
    	int nextIndexToReturn = 0;
    	
    	public boolean hasNext() {
    		if (nextIndexToReturn < myCount)
    			return true;
    		return false;
    	}
    	public T next() {
    		T result = (T) myValues[nextIndexToReturn];
    		nextIndexToReturn++;
    		return result;
    	}
    }
}
