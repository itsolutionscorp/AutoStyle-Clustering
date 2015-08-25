import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;


public class Sequence<T extends Object> implements Iterable<T>{

	// instance variables
    protected Object[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
    	myValues = new Object[capacity];
    	myCount = 0;
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
        // YOUR CODE HERE
    	if (myCount == myValues.length) {
    		Sequence<T> blah = new Sequence<T>(myCount * 2);
    		for (int i = 0; i < myCount; i++) {
    			blah.myValues[i] = myValues[i];
    			blah.myCount++;
    		}
    		blah.myValues[blah.myCount] = toBeAdded;
    		blah.myCount++;
    		myValues = blah.myValues;
    		myCount = blah.myCount;
    	}
    	else {
    		myValues[myCount] = toBeAdded;
        	myCount ++;
    	}
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(T toInsert,int insertPos){
    	if (myCount == myValues.length) {
    		Sequence<T> blah = new Sequence<T>(myCount * 2);
    		for (int i = 0; i < myCount; i++) {
    			blah.myValues[i] = myValues[i];
    			blah.myCount++;
    		}
    		for(int q = blah.myCount + 1; q > insertPos; q--){
        		blah.myValues[q] = blah.myValues[q-1];
        		}
    		blah.myValues[insertPos]=toInsert;
        	blah.myCount++;
        	myValues = blah.myValues;
        	myCount = blah.myCount;
    	}
		else {
			for(int q = myCount + 1; q > insertPos; q--){
	    		myValues[q] = myValues[q-1];
	    		}
	    	myValues[insertPos]=toInsert;
	    	myCount++;
	    	}
    	}

    // other methods go here
    public boolean isEmpty() {
    	if (myCount == 0) return true;
    	else return false;
    }
    public int size() {return myCount;}
    
    public T elementAt(int pos) {
    	if (pos>=myCount) {
    		System.err.println("Index out of range.");
    		System.exit(1);
    	}
    	return (T)myValues[pos];
    }
    public String toString(){
    	String res="";
    	for(int q = 0;q<myCount;q++){
    		res+=myValues[q].toString()+" ";
    	}
    	return res.substring(0, res.length()-1);
    }

	public T remove(int pos){ 
		Object q = myValues[pos];
		for(int x=pos; x < myCount; x++){
			myValues[x] = myValues[x+1];
		}
		myCount--;
		if (myCount <= (myValues.length / 2)) {
			Sequence<T> blah = new Sequence<T>(myValues.length / 2);
    		for (int i = 0; i < myCount; i++) {
    			blah.myValues[i] = myValues[i];
    			blah.myCount++;
    		}
    		myValues = blah.myValues;
    		myCount = blah.myCount;
		}	
		return (T)q;
	}
	
	public boolean contains(T val) {
		for (int i = 0; i < myCount; i++) {
			if (myValues[i] == val) {
				return true;
			}
		}
		return false;
	}
	
	//Methods from Iterable
	
	public Iterator<T> iterator() {
		return new SequenceIterator<T>();
	}

	public void forEach(Consumer<? super T> action) {
		// TODO Auto-generated method stub
		
	}

	public Spliterator<T> spliterator() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * And now we have the <private interior class of +25 confuse everyone>
	 * @author cs61bl-ah
	 *
	 * @param <T> is any subclass of Object
	 */
	private class SequenceIterator<T> implements Iterator<T>{
		int index=0;
		public boolean hasNext() {
			if(index>=myCount)
				return false;
			else
				return true;
		}

		public T next() {
			// TODO Auto-generated method stub
			T res = (T)myValues[index];
			index++;
			return res;
		}

		public void remove() {
			//Stuff and things. Yay Java 8.
		}

		public void forEachRemaining(Consumer<? super T> action) {
			// TODO Auto-generated method stub
			
		}
		
	}

	

}