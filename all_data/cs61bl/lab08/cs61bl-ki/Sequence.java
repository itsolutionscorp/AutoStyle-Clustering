import java.util.Iterator;


public class Sequence<T> implements Iterable<T> {

	protected Object[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    private class SequenceIterator <T> implements Iterator<T>{

    	private int index;
    	
    	public SequenceIterator(){
    		this.index = 0;
    	}
    	
		@Override
		public boolean hasNext() {
			if (index > myValues.length-1){
				return false;
			}
			return true;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			T value = (T) myValues[index];
			index++;
			return value;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
    	
    }
    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
    	this.myValues = new Object[capacity];
        this.myCount = 0;
    }

    public SequenceIterator<T> iterator() {
    	return new SequenceIterator<T>();
    }
    
    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
    	if (this.myCount < this.myValues.length){
    		this.myValues[this.myCount] = toBeAdded;
    		this.myCount++;    		
    	} else {
    		System.err.println("Maximum Capacity Reached");
    		System.exit(1);
    	}
    }
    
    public void remove(int pos){
		if (pos < 0 || pos >= this.myCount) {
			return;
		}
		for (int i=pos; i < this.myCount; i++){
			if (pos < this.myCount-1) {
				this.myValues[i] = this.myValues[i+1];
				pos++; //move position to continue shifting
			}
		}
		if (this.myCount>0)
			this.myCount--;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    @SuppressWarnings("unchecked")
    public void insert(T toInsert, int pos) {
    	if (pos < 0 || pos >= this.myCount || this.myCount == this.myValues.length) {
    		return;
   		}
    	this.myCount++;
   		for (int i = pos; i < this.myCount; i++ ){
   			T temp = (T) this.myValues[i];
   			this.myValues[i]=toInsert;
   			toInsert = temp;
   			pos++;
   		}
    }
    
    public boolean contains(T k){
    	for (int i = 0; i < this.myCount; i++){
    		if (this.myValues[i].equals(k)){
    			return true;
    		}
    	}
    	return false;
    }
    
    public boolean isEmpty(){
    	if (this.myCount==0)
    		return true;
    	return false;
    }

    public int size(){
    	return this.myCount;
    }
    
    @SuppressWarnings("unchecked")
    public T elementAt(int pos){
    	if (pos < 0 || pos >= this.myCount){
    		System.err.println("Position out of Bounds");
    		System.exit(1);
    	}
    	return (T) this.myValues[pos];
    }
    
    public String toString(){
    	String result = "";
    	for (int i=0; i<this.myCount; i++){
    		if (i==this.myCount-1){
    			result = result + this.myValues[i];
    		} else {
    			result = result + this.myValues[i] + " ";    			
    		}
    	}
    	return result;
    }

}
