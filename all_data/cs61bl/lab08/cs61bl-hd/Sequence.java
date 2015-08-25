import java.util.Iterator;

public class Sequence <T> implements Iterable<T>{
	

    // instance variables
    protected T[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
        this.myValues = (T[]) new Object[capacity];
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
		if (this.myCount==this.myValues.length){
			System.err.println("No more space in array");
			System.exit(1);
		}
    	this.myValues[this.myCount] = toBeAdded;
        this.myCount++;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
//    public void insert(int toInsert, int insertPos) {
//        for (int k = insertPos + 1; k < myCount; k++) {
//            myValues[k] = myValues[k-1];
//        }
//        myValues[insertPos] = toInsert;
//        myCount++;
//    }
	public void insert (T toInsert, int insertPos) {
		if (insertPos < 0 || insertPos > this.size()) {
			System.err.println(insertPos + " is outside of bounds");
			System.exit(1);
			return;
		}
		int k = this.myValues.length-1;
		for (;k!=insertPos;k--){
			this.myValues[k] = myValues[k-1]; 
 		}
		this.myValues[insertPos] = toInsert;
		if (myCount < this.myValues.length){
			myCount++;
		}
	}
	
	public boolean isEmpty() {
		if (this.myCount==0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public int size() {
		return this.myCount;
	}
	
	public T elementAt(int pos){
		if (pos>this.myValues.length){
			System.err.println(pos + " is outside of bounds");
			System.exit(1);
		}
		return this.myValues[pos];
	}
	
    public String toString() {
    	String outString = "";
    	if (this.myCount == 0){
    		return outString;
    	}
    	for (int k=0; k!=(this.myCount-1);k++){
    		outString = outString + String.valueOf(this.myValues[k]) + " ";
    	}
    	return outString = outString + String.valueOf(this.myValues[this.myCount-1]);
    }
    
	public T remove (int pos) {
		if (pos < 0 || pos >= this.size()) {
			System.err.println(pos + " is outside of bounds");
			System.exit(1);
		}
		T returnVal = this.myValues[pos];
		for (;pos!=this.size()-1;pos++){
			this.myValues[pos] = this.elementAt(pos+1); 
		}
			this.myValues[this.size()-1]=null;
			this.myCount--;
			return returnVal;
	}
	
	public boolean contains (T val) {
		for (int k=0;k!=this.size();k++){
			if(this.myValues[k].equals(val)){
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new seqIterator();
	}
	
	private class seqIterator implements Iterator<T>{
		int index;
		
		public seqIterator() {
			index = 0;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			
			return index < myCount;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			int n = index;
			index++;
			return (T) elementAt(n);
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
	}


}

