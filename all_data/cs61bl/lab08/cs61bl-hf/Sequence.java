import java.util.Iterator;



public class Sequence<T> {
	 
private int mycount ;
private T[] myValues;
 
@SuppressWarnings("unchecked")
public Sequence(int capacity) {   
     myValues= (T[]) new Object[capacity];
}
 

	// instance variables
	 // sequence elements

	// int myCount = 0; // number of array cells used by sequence

	// constructor
	// capacity: actual size of the array or the (temporary) maximum
	// number of elements it can hold

	public boolean isEmpty() {
		if (mycount == 0) {
			return true;
		} else {
			return false;
		}
	}

	public int size() {
		return mycount;
	}

	public T elementAt(int pos) {
		if (pos < 0 || pos >= mycount) {
			throw new IllegalArgumentException("Postion is Out of Boundary!!!");
		} else {
			return myValues[pos];
		}
	}

	// Add the argument to the sequence by placing it in the first
	// unused spot in the array and incrementing the count.
	// Assume that the sequence isn't full.
	public void add(T toBeAdded) {
		// YOUR CODE HERE
		// myCount ++;
		mycount ++;
		myValues[mycount- 1] = toBeAdded;
	}
	// Insert toInsert into the sequence at position insertPos,
	// shifting the later elements in the sequence over to make room
	// for the new element.
	// Assumptions: The array isn't full, i.e. myCount < myValues.length
	// Also, insertPos is between 0 and myCount, inclusive.
	public void insert(T toInsert, int pos) { // pos is insert pos
		
		if (pos < 0 || pos >= myValues.length) {
			throw new IllegalArgumentException("Postion is Out of Boundary!!!");
		}
		// YOUR CODE HERE
		@SuppressWarnings("unchecked")
		
		T[]  newArray = (T[]) new Object[myValues.length];
		for (int i = 0; i < pos; i++){
			newArray[i] =myValues[i];
			
		}
		newArray[pos] = toInsert;
		
		for (int i = pos + 1; i < myValues.length; i++){
			newArray[i] = myValues[i - 1]; 
		}
		for(int i = 0; i < myValues.length; i++){
			myValues[i] = newArray[i];
		}
		if (mycount<myValues.length){
			mycount++;
		}
	}
	// other methods go here
	public String toString() {
		String rValue = ""; 
		for (int i = 0; i < mycount; i++){

			if (i == mycount - 1){
				rValue = rValue + myValues[i]; 
			}else{
				rValue = rValue + myValues[i] + " ";
			}   		
		}
		return rValue;
	}
	public void remove(int pos) {

		if (pos < 0 || pos >= mycount) {
			System.err.println("postion number is out of boundary");
			
		}
		
		// YOUR CODE HERE
		if (pos == mycount - 1) {
			myValues[mycount - 1]=null; 
			mycount --; 
		}
		else {
			
			for (int i = pos; i < mycount-1; i ++){
				myValues[i] = myValues[i + 1];
			}
				mycount -- ; 	
		}			
	}
	public boolean contains(T input) {
		
		boolean ans = false;
		for (int i=0; i<mycount;i++ ) {
			if (myValues[i].equals(input)) {
				 ans= true;
				 break;
			} else {
				
			}
		}
			return ans;
	}
	
	public Iterator<T> iterator(){
		return new SEQIterator<T>();
		
	}
	
	public class SEQIterator<E> implements Iterator<E> {
		int count;
		
		public  SEQIterator(){
			count= 0;
		}
		public boolean hasNext() {
			if(count<mycount){
				return true;
			}
			return false;
		}
		public E next() {
			@SuppressWarnings("unchecked")
			E ret= (E) myValues[count];
			count++;
			return ret;	
		}
	}
	public static void main(String[] string) {
		
		Sequence<String> s = new Sequence<String>(5);
		s.add("hi,");
		s.add("my");
		s.add("name");
		s.add("is");
		Iterator<String> Iter = s.iterator();
		while(Iter.hasNext()){
			System.out.println(Iter.next());
		}
		Sequence<Integer> s1 = new Sequence<Integer>(6);
		s1.add(1);
		s1.add(2);
		s1.add(3);
		s1.add(4);
		Iterator<Integer> Iter1 = s1.iterator();
		while(Iter1.hasNext()){
			System.out.println(Iter1.next());
		}
		Sequence<Integer> s11 = new Sequence<Integer>(6);
		s11.add(1);
		s11.add(2);
		s11.add(3);
		s11.add(4);
		for(Iterator<Integer> Iter11 = s11.iterator(); Iter11.hasNext();){
			     
			System.out.println(Iter1.next());
		}
		}
	}

