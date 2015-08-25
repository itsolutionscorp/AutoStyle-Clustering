import java.util.Iterator;
public class Sequence<T> implements Iterable<T>{

	
	public T[] myValues;   // sequence elements
	    int myCount;  
	    
	    public Iterator iterator(){
	    	return new iterat<T>();
	    }
	    
	    
	    private class iterat<T> implements Iterator<T>{
	    	 private int nextIndexToReturn;
	    	   
	    	   

	    	    public void initIterator() {
	    	        nextIndexToReturn = 0;
	    	    }

	    	    public T next() {
	    	        T valToReturn =(T)myValues[nextIndexToReturn];
	    	        nextIndexToReturn++;
	    	        return valToReturn;
	    	    }

	    	    public boolean hasNext() {
	    	        return nextIndexToReturn < myCount;
	    	    }
	    	    public void remove() {
	    	    	throw new UnsupportedOperationException("");
	    	    }
	    }
	    
	    public Sequence(int capacity) {
	        // YOUR CODE HERE
	    	myValues = (T[])new Object[capacity];
	    	myCount = 0;
	    }
	    
	    
	    public void add(T toBeAdded) {
	        // YOUR CODE HERE
	    	if(myCount < myValues.length){
		    	myValues[myCount] = toBeAdded;
		    	myCount++;
	    	}else{
	    		System.err.println("Array is full");
	    		System.exit(1);
	    	}
	    }

	    // Insert toInsert into the sequence at position insertPos,
	    // shifting the later elements in the sequence over to make room
	    // for the new element.
	    // Assumptions: The array isn't full, i.e. myCount < myValues.length
	    // Also, insertPos is between 0 and myCount, inclusive.
	    public void insert(T newInt, int pos) {
	    	if (pos < 0 || pos >= myValues.length) {
	    		System.err.println("Element does not exist at index");
	    		System.exit(1);
			}else {
			for(int i  = myValues.length - 1; pos < i; i--){
				myValues[i] = myValues[i-1];
			}
			myValues[pos] = newInt;
			}
	        myCount++;
	    }

	    // other methods go here

	    public boolean isEmpty(){  	
		if(myCount == 0){
			return true;
		}else{
			return false;
		}
	    }
	    
	    public int size(){
	    	return myCount;
	    }
	    

	    public T elementAt(int pos){
	    	if(pos >= myCount){
	    		System.err.println("Element does not exist at index");
	    		System.exit(1);
	    	}
	    	return myValues[pos];
	    }
	    
	    public String toString(){
	    String result= "";
	    	for(int i = 0;i < myCount; ++i){
	    		result = result +myValues[i];
	    		if(i !=myCount-1){
	    			result = result +" ";
	    		}
	    	}
	    	return result;
	    }
	    
	    public T remove (int pos) {
	    	T k = myValues[pos];
	    	if (pos < 0 || pos >= myValues.length) {
	    		System.err.println("Element does not exist at index");
	    		System.exit(1);
			
			}else{
				
				for(int i = 1; (i+pos) <= myValues.length-1; i++){
					myValues[pos+i-1]= myValues[pos+i];
				}
					myValues[myValues.length-1] = null;
			}
	    	this.myCount--;
	    	return k;
			// YOUR CODE HERE
		}
	    
	    public boolean contains (T k) {
	    	Iterator i = iterator();
	    	((iterat<T>) i).initIterator();
	    	while(i.hasNext()){
	    		if(i.next() == k){
	    			return true;
	    		}
	    	}
	    	return false;
	    }

	    

	    
	    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sequence<Integer> s1 = new Sequence<Integer>(2);
		s1.add(31);
		s1.add(35);
		Integer temp = s1.elementAt(0);
		Integer temp1 = s1.elementAt(1);
		System.out.println(temp);
		System.out.println(temp1);
		
		
		Sequence<String> s2 = new Sequence<String>(2);
		s2.add("HI one");
		s2.add("HI two");
		s2.toString();
		String temp2 = s2.elementAt(0);
		String temp3 = s2.elementAt(1);
		System.out.println(temp2);
		System.out.println(temp3);
	}

}
