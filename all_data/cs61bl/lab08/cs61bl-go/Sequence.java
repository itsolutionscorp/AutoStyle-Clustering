import java.util.Iterator;


public class Sequence<T> implements Iterable<T>{
	protected T[] myValues; 
	int myCount;    
	
	
	 // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
    	myValues = (T[]) new Object[capacity];
    	myCount = 0;
    }
    
    public T getItem(int i) {
        return myValues[i];
    }
    
    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
        if (size() == myValues.length){
        	System.err.println("No more open spots in the array.");
        	System.exit(1);
        }
    	myValues[size()] = toBeAdded;
        myCount++;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(T toInsert, int insertPos) {
    	if (myCount == myValues.length){
    		System.err.println("No more open spots in the array.");
    		System.exit(1);
    	}
    	if ((insertPos < 0) || (insertPos > myCount)){
    		System.err.println("Index out of bounds.");
    		System.exit(1);
    	}
        for (int k = myCount; k >= insertPos + 1; k--) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }

    // other methods go here

    public boolean isEmpty(){
    	if (myCount == 0){
    		return true;
    	}
    	return false;
    }

    public int size(){
    	return myCount;
    }
    
    public T elementAt(int pos){
    	if (pos > size()){
    		System.err.println("Index " + pos + " does not exist.");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    
    public String toString(){
    	String elements = new String();
    	if (myValues.length == 0){
    		System.err.println("The array is empty.");
    		System.exit(1);
    	}
    	if (myCount >= 1){
    		elements = String.valueOf(myValues[0]);
    	}
    	for (int x = 1; x < myCount; x++){
    		elements += " " + String.valueOf(myValues[x]);
    	}
    	return elements;
    }
    
    public T remove (int pos) {
    	T toReturn = myValues[pos];
		if (pos < 0 || pos >= myValues.length) {
			return null;
		}
		T[] copyOfValues = (T[]) new Object [myValues.length];
		System.arraycopy(myValues, 0, copyOfValues, 0, myValues.length);
		for (int p = 0; p < myValues.length; p++){
			if (p == myValues.length - 1){
				myValues[p] = null;
			}
			else if (p >= pos){
				myValues[p] = copyOfValues[p+1];	
			}
		}
		myCount--;
		return toReturn;
	}
    
    public boolean contains(T k){
    	boolean includes = false;
    	for (int x = 0; x < myCount; x++){
    		if (myValues[x] == k){
    			includes = true;
    			break;
    		}
    	}
    	return includes;
    }
    
    public static void main(String [] args){
    	Sequence integers = new Sequence(7);
    	integers.add(1);
    	integers.add(3);
    	integers.add(6);
    	integers.add(7);
    	integers.add(9);
    	integers.insert(5, 2);
    	System.out.println(integers);
    	System.out.println(integers.elementAt(3));
    	
    	Sequence strings = new Sequence(8);
    	strings.add("Hello");
    	strings.add("Hi");
    	strings.add("Goodbye");
    	strings.add("wow");
    	strings.add("61bl");
    	System.out.println(strings);
    	System.out.println("removed " + strings.remove(1));
    	System.out.println(strings);
    	System.out.println("strings contains wow is " + strings.contains("wow"));
    	
    	Iterator iter = strings.iterator();
		System.out.println(iter.next());
		System.out.println(iter.next());
		System.out.println(iter.next());
		System.out.println(iter.next());
		System.out.println(iter.next());
    }
    
    public Iterator<T> iterator() {
		return new sequenceIterator<T>();
	}
    
    private class sequenceIterator<T> implements Iterator<T>{
    	private int myStart;

		public boolean hasNext() {
			return myStart < myCount;
		}

		public T next() {
			T temp = null;
			if (hasNext()){
				temp = (T) myValues[myStart];
				myStart++;
			}
			return temp;
		}

	}
}
