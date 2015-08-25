import java.util.Iterator;

public class Sequence<T> implements Iterable{

    // instance variables
    protected T[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
        this.myCount = 0;
        this.myValues = (T[]) new Object[capacity]; 
    }
    
    

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) { 
        if(myCount < myValues.length) {
        	myValues[myCount] = toBeAdded;
        	myCount++;
        } else {
        	System.err.println("Error: The sequence is already full");
        	System.exit(1);
        }
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(T toInsert, int insertPos) {
    	if (insertPos < 0 || insertPos > myCount) {
    		System.err.println("Error: insert to an non existant position");
    		System.exit(1);
    	}
        for (int k = myValues.length - 1; k > insertPos; k--) {
        	myValues[k] = myValues[k - 1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
     }

    // other methods go here
    
    public void remove(int pos) {
    	if (pos < 0 || pos >= myCount) {
    		System.err.println("Error: remove an non existant position");
    		System.exit(1);
    	}
    	for (int i=pos;i<myCount-1; i++) {
    		myValues[i] = myValues[i+1];
    	}
    	myCount--;
    }

    public boolean isEmpty() {
    	if (myCount == 0){
    		return true;
    	}
    	return false;
    }
    
    public int size(){
    	return myCount;
    }
    
    public T elementAt(int pos){
    	if (pos < 0 || pos >= myCount) {
    		System.err.println("Error: Getting an non existant position");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    
    public boolean contains(T element) {
    	for(int i=0;i<myCount; i++) {
    		if(myValues[i] == element) 
    			return true;
    	}
    	return false;
    }
    
    public String toString(){
    	String result = "";
    	for(int i = 0; i < myCount; i++){
    		result += myValues[i];
    		if (i != myCount-1) 
    			result += " ";
    	}
    	return result;
    }
    
    private class MyIterator implements Iterator<T> {

    	int index = 0;
    	
    	public MyIterator() {
    		initIterator();
    	}
    	
		@Override
		public boolean hasNext() {
			if(index < myCount){
				return true;
			}
			return false;
		}

		@Override
		public T next() {
			index++;
			return myValues[index-1];
		}
    	
    	public void initIterator(){
    		index = 0;
    		
    	}
    	
    }

	@Override
	public Iterator<T> iterator() {
		return new MyIterator();
	}
}

