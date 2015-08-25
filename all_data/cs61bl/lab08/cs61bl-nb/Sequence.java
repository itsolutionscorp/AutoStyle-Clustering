import java.util.Iterator;
public class Sequence<T> implements Iterable<T>{

    // instance variables
    protected T [] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence
    
    
    private class  Iter<T> implements Iterator<T> {
    	private int index ;
    	public Iter(){
    		 index = 0;
    	}
    	
    	public boolean hasNext(){
    		if(index < myCount) {
    			return true;
    		} else {
    			return false;
    		}
    	}
    	
    	public T next(){
    		T temp = (T)myValues[index];
    		index++;
    		return temp;
    	}
    	
    }
    
    public Iterator<T> iterator(){
    	Iter<T> i = new Iter<T>();
    	return i;
    }
    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
        // YOUR CODE HERE
    	myValues = (T[]) new Object[capacity];
    	myCount = 0;
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
        // YOUR CODE HERE
    	if(myCount == myValues.length) {
    		System.err.println("No more open spots in this array.");
    		System.exit(1);
    	}
    	myValues[myCount] = toBeAdded;
    	myCount ++;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(T toInsert, int insertPos) {
        for (int k = myCount; k > insertPos; k--) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }
    
	// other methods go here
    public boolean isEmpty() {
    	if(myCount == 0) {
    		return true;
    	} else {
    		return false;
    	}	
    }
    
    public int size() {
    	return myCount;
    }
    
    public T elementAt(int pos){
    	if(pos < 0 || pos >= myCount) {
    		System.err.println("The index " + pos + " does not exist");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    
    public String toString() {
    	String s = myValues[0] + " ";
    	for(int i = 1; i < myCount-1; i++){
    		s = s + myValues[i]+" ";
    	}
    	s = s + myValues[myCount - 1];
    	return s;
    }
    
    public void remove (int pos) {
		if (pos < 0 || pos >= this.myCount) {
			return;
		}
		for (int i = pos; i < this.myCount - 1; i++) {
			this.myValues[i] = this.myValues[i + 1];
		}

		this.myCount--;
	}
    
    public boolean contains (T k){
    	for (int i = 0; i < myCount; i++){
    		if(myValues[i] == k){
    			return true;
    		}
    	}
    	return false;
    }
    
    

}

