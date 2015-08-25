import java.util.Iterator;


public class Sequence<T> implements Iterable<T> {
	
	private T[] myValues;
	private int myCount;
	private int nextIndex = 0;
	
	public Iterator<T> iterator() {
		return new SequenceIterator<T>();
	}
	
	public Sequence (int capacity) {
		myValues = (T[]) new Object[capacity];
	}

	public class SequenceIterator<T> implements Iterator<T> {
		public boolean hasNext() {
			return (nextIndex < myCount);
	    }

	    public T next() {
	        if (nextIndex < myCount) {
	        	nextIndex ++ ;
	        	return (T) myValues[nextIndex - 1];
	        }
	        else {
	        	nextIndex = 0;
	        	return next();
	        }
	    }

	    public void remove() {
	        throw new UnsupportedOperationException ("remove not supported");
	    }
	}
	
	
	public static void main (String[] args) {

		/*TESTS:
		Sequence<String> stringseq1 = new Sequence<String>(10);
		stringseq1.add("one");
		stringseq1.add("two");
		stringseq1.add("three");
		System.out.println(stringseq1.toString());
		for (String s : stringseq1) {
			System.out.println(s);
		}
		
		Sequence<String> stringseq = new Sequence<String>(10);
		if (stringseq.isEmpty()) {
			System.out.println("yup, it's empty right now");
		}
		stringseq.add("item #1!");
		stringseq.add("item #2!");
		stringseq.add("item #3!");
		System.out.println(stringseq.toString());
		if (stringseq.contains("item #3!")) {
			System.out.println("contains method is working for stringseq");
		}
		stringseq.remove(2);
		System.out.println(stringseq.toString());
		stringseq.insert("I stuck something in here!", 1);
		System.out.println(stringseq.toString());
		System.out.println("size: " + stringseq.size());
		System.out.println("element at 2 is " + stringseq.elementAt(2));
		
		Sequence<Integer> intseq = new Sequence<Integer>(10);
		if (intseq.isEmpty()) {
			System.out.println("yup, it's empty right now");
		}
		intseq.add(1);
		intseq.add(2);
		intseq.add(3);
		System.out.println(intseq.toString());
		if (intseq.contains(3)) {
			System.out.println("contains method is working for intseq");
		}
		intseq.remove(2);
		System.out.println(intseq.toString());
		intseq.insert(79, 1);
		System.out.println(intseq.toString());
		System.out.println("size: " + intseq.size());
		System.out.println("element at 2 is " + intseq.elementAt(2));*/
	}
	
	public boolean contains(T k) {
    	for (int i = 0; i < myCount; i ++) {
    		if (myValues[i] == k) {
    			return true;
    		}
    	}
    	return false;
    }
    
    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
    	if (myCount < myValues.length) { 
    		myValues[myCount] = toBeAdded;
    		myCount ++;
    	}
    	else {
    		System.err.println("sequence full");
    		System.exit(1);
    	}
    }

    public T remove(int toRemove) {
    	T removed = myValues[toRemove];
    	for (int i = 0; i <= myCount; i++) {
    		if (i < toRemove) {
    			continue;
    		}
    		else {
    			myValues[i] = myValues[i+1];
    		}    		
    	}
    	myCount --;
    	return removed;
    }   
    
    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(T toInsert, int insertPos) {
        /*for (int k = insertPos + 1; k <= myCount; k++) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
        */    	
    	
        if (insertPos < 0 || insertPos > myCount) {
			return;
		}
        
		T temp = myValues[insertPos];
		myValues[insertPos] = toInsert;
		T temp2;
		
		for (int i = 0; i <= myCount; i ++) {
			if (i <= insertPos) {
				continue;
			}
			else {
				temp2 = myValues[i];
				myValues[i] = temp;
				temp = temp2;
			}
		}
		myCount++;        
    }

    public boolean isEmpty() {
    	return (myCount == 0);
    }
    
    public int size() {
    	return myCount;
    }
    
    public T elementAt (int pos) {
    	if (pos >= myCount) {
    		System.err.println("no element in requested position");
    		System.exit(1);
    		return null; 
    	}
    	else {    		    		
    		return myValues[pos];
    	}
    }
    
    public String toString() {
    	String s = new String("");
    	if (isEmpty()) {
    		return "";
    	}
    	for (int i = 0; i < myCount - 1; i ++) {
    		s = s + myValues[i] + " ";
    	}
    	s = s + myValues[myCount-1];
    	return s;
    }

}
