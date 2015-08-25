import java.util.Iterator;
import java.util.NoSuchElementException;

public class Sequence <Berkeley> implements Iterable<Berkeley>{

    // instance variables
    protected Berkeley[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence (size, actually)
    
    
	/********************************Iterator********************************/
    
    
    // citation: https://github.com/Berkeley-CS61B/lectureCode/blob/master/lec15/live1/Iterator/AList.java
    // Thank you, Josh Hug.
    
    /* this method just returns a (summer) iterator;
     *  SummerIterator is a subtype of an iterator */
    public Iterator<Berkeley> iterator() {
        return new SummerIterator();
    }

    private class SummerIterator implements Iterator<Berkeley> {
        private int i = 0;

        public Berkeley next() {
            if (hasNext()) {
                Berkeley result = myValues[i];
                // Note: Nested classes can access the instance variables of the class they're nested in.
                i++;
                return result;
            }
            throw new NoSuchElementException();
        }

        public boolean hasNext() {
            return (i < myCount);
        }

        /* "If you're using Java 8, feel free to ignore the remove method" */
//        public void remove() {
//            throw new UnsupportedOperationException();
//        }
    }
    
    
	/********************************Iterator********************************/
    
    
    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) { // takes in argument which = max 
        myValues = (Berkeley[]) new Object[capacity]; // instantiation of new array and assignment to pointer "myValues"
        // Note: this is a weird syntax!
        //       "You can't create an array with a generic dynamic type. 
        //       Only its static type can be generic. 
        //       So myValues should have a generic static type, but a dynamic type of Object[]. 
        //       This is just an odd special case with arrays; don't worry too much about it."
        myCount = 0; 
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(Berkeley toBeAdded) {
    	if (myCount < myValues.length) { 
    		myValues[myCount] = toBeAdded; 
    		++myCount; 
    		return;
    	}
    	System.err.println("Invalid argument: no more space left in array."); 
    	System.exit(1); 
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(Berkeley toInsert, int insertPos) {
		if (insertPos >= 0 && insertPos <= myCount) { // can insert
			for (int i = myCount; i > insertPos; --i) {
				myValues[i] = myValues[i - 1];
			}
			myValues[insertPos] = toInsert;
			++myCount; // don't forget to update instance variable!
			return;
		}
    	System.err.println("Invalid argument: no more space left in array, or insertPos out of range."); 
    	System.exit(1); 
    }

    // other methods go here
    
    // returns true when this sequence is empty and returns false otherwise
    public boolean isEmpty() {
    	if (myCount == 0) {
    		return true;
    	}
    	return false; 
    }
    
    // returns the number of values in this sequence 
    public int size() {
    	return myCount; 
    }
    
    // returns the value at the given position in the sequence
    // Note: If someone asks for the elementAt an index that does not exist,
    // you should call System.err.println and include a description of the error 
    // and call System.exit(1) to exit the method. 
    // The same is true for any case where a method is called with incorrect input.
    public Berkeley elementAt(int pos) {
    	if (pos >= 0 && pos <= myCount - 1) { // length is 6, but index of last element is 5 
    		return myValues[pos]; 
    	}
    	System.err.println("Invalid argument: pos out of range. Index does not exist."); 
    	System.exit(1); 
    	return myValues[0];
    	// Note: must return a type Berkeley, but it will never executes this line!
    	//       Or the error: "This method must return a result of type Berkeley"
    }
    
	// given the position of the sequence element to remove as an argument,
    // removes the specified element and returns it
    public Berkeley remove (int pos) {
		if (pos >= 0 && pos < myCount) { // can remove within this range 
			Berkeley victim = myValues[pos];
			for (int i = pos; i < myCount - 1; ++i) {
				myValues[i] = myValues[i + 1];
			}
			myValues[myCount - 1] = null;
			--myCount;
			return victim;
		}
    	System.err.println("Invalid argument: pos out of range. Cannot remove the element"); 
    	System.exit(1); 
    	return myValues[0];
	}
    
    // contains returns true if k is one of the elements of this sequence,
    // and returns false otherwise
    public boolean contains(Berkeley k) {
    	for (int i = 0; i < myCount; ++i) {
    		if (myValues[i].equals(k)) {
    			return true;
    		}
    	}
    	return false;
    }
    
	// return a String that contains the elements of the sequence separated by blanks. 
    // Please make sure that there is just one space between each element,
    // and no trailing spaces on either side of the string.
	@Override
	public String toString() {
		String result = new String();
		for (int i = 0; i < myCount - 1; ++i) {
			result = result.concat(myValues[i] + " ");
		}
		result = result.concat(myValues[myCount -1] + ""); // last element (without space) 
		return result; 
	} 
	

    
}

