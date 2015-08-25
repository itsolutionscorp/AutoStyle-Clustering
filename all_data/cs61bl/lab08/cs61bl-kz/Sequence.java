import java.util.ArrayList;
import java.util.Iterator;

public class Sequence<T> implements Iterable<T> {
	
	protected T[] myValues;
	int myCount;
	
	public Sequence(int capacity) {
		myValues = (T[]) new Object[capacity]; 
	}
	
	//Looked at codereview.stackexchange.com/questions/48109 in order to understand
	//syntax of Iterators and Iterables.
	public Iterator<T> iterator(){
		return new SequenceIterator();
	}
	
	private class SequenceIterator implements Iterator<T> {
		private int index;
		public SequenceIterator() {
			this.index = -1;
		}
		public boolean hasNext() {
			return (this.index + 1) < myCount;
		}
		public T next() {
			index++;
			return myValues[index];
		}
	}
	
	public boolean isEmpty() {
    	//return true when sequence is empty, false otherwise
    	if (this.myCount == 0){
    		return true;
    	} else 
    		return false;
    }
    
    public int size() {
    	//number of values in the sequence
    	return this.myCount;
    }
    
    public T elementAt(int pos) {
    	if (pos > myCount -1) {
    		System.err.println("Outside scope of array");
    		System.exit(1);
//    		return null; //used for testing purposes
    	}
		return myValues[pos];
    }
    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
        // YOUR CODE HERE
    	if (myCount == myValues.length) {
    		System.err.println("No more availabe spots in array cells");
    		//System.exit(1);
    	}
    	myValues[myCount] = toBeAdded;
    	myCount++;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert1(T toInsert, int insertPos) {
    	//This is the buggy version. We had to change the name in order
    	//to be syntactically correct.
        for (int k = insertPos + 1; k <= myCount; k++) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }
    // other methods go here
    public String toString() {
    	String myString = "";
    	for (int k = 0; k < myCount - 1; k ++) {
    		myString = myString + myValues[k] + " "; 
    	}
    	myString = myString + myValues[myCount - 1];
    	return myString;
    }
    
    public void remove(int pos) {
    	if (pos < 0 || pos >= myValues.length - 2) {
    		return;
    	} else
    		myCount--;
    		for (int i = 0; i < myValues.length - 1; i++)
    			if (i < pos) {
    				continue;
    			} else 
    				myValues[i]= myValues[i+1];			
    }	

    
    public void insert (T toInsert, int pos) {
        //This is the non-buggy version of insert.
    	if (pos < 0 || pos >= myValues.length) {
    		return;
    	} else 
    		if (myCount < myValues.length)
    			{myCount++;}
    		{	
    			int j = myValues.length - 1;
    			for (; j > 0; j--){
    			if (j > pos){
    				this.myValues[j] = this.myValues[j-1];
    			} else if (j == pos){
    				myValues[j] = toInsert;
    			}}
	    		if(pos == 0 && j==0)
				{myValues[0] = toInsert;}
    		}
    	}

    
    public boolean contains(T k) {
    	for (int i = 0; i < myCount; i++) {
    		if (myValues[i] != k) {
    			continue;
    		} else
    			return true;
    	} return false;
    }

	}


