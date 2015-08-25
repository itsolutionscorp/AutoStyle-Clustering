import java.util.Iterator;

public class Sequence <T> implements Iterable<T>{

    // instance variables
    protected T[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence
    private class lst implements Iterator<T>{
    	int i;
    	
    	public void initIterator() {
    		i = 0;
    	}

    	public T next() {
    		T x = myValues[i];
    		i ++;
    		return x;
    	}
    	
    	public boolean hasNext() {
    		return i < myCount;
    	}
    }
    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    
    
    public Sequence(int capacity) {
        myValues  = (T[]) new Object[capacity];
        myCount = 0; 
        //for (int i=0; i< myValues.length; i+= 1) {
        //	SeqArray[i] = myValues[i]; 
        //	myCount += 1; 
        //}
    }

    public Iterator iterator() {
    	return new lst();
    }
    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
        if (myCount == myValues.length) {
            System.err.println("Error: Attempted add in index out of bounds"); 
            System.exit(1); 
        }
    	myValues[myCount] = toBeAdded; 
    	myCount += 1;    
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(T toInsert, int insertPos) {
        if (myCount == myValues.length) {
            System.err.println("Error: Attempted insert in index out of bounds"); 
            System.exit(1); 
        }
        for (int k = myCount; k > insertPos; k--) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount += 1; 
    }

    public boolean contains (T k) {
        for (int i = 0; i <= myCount; i+=1) {
            if (myValues[i] == k) {
                return true; 
            }
        }
        return false;   
    }

    public void remove (int delPos) {
        for (int i = delPos; i < myValues.length -1; i += 1) {
            myValues[i] = myValues[i+1]; 
        }
        myCount -= 1; 
    }

    public boolean isEmpty() {
    	if ( myCount == 0) {
    		return true; 
    	}
    	else {
    		return false; 
    	}
    }
    
    public int size() {
    	return myCount; 
    }
    
    public T elementAt (int pos) {
    	if (pos < myCount) {
    		return myValues[pos]; 
    	}
    	else {
    		System.err.println("Error: Index out of bounds ");
    		System.exit(1);
    		return null;
    	}
    }
    
    public String toString () {
     if (myCount == 0) {
    	 return "";
     }
     String elemString = "" + myValues[0]; 
    	for (int i = 1; i < myCount; i += 1) {
    		elemString += " " + myValues[i];
    	}
    	return elemString; 
    }
    
    public static void main(String[] args) {
    	Sequence a = new Sequence(10);
    	a.add("I");
    	a.add("like");
    	a.add("to");
    	a.add("eat");
    	a.add("cheese");
    	Iterator b = a.iterator();
    	while (b.hasNext()) {
    		System.out.println(b.next());
    	}
    }
}

