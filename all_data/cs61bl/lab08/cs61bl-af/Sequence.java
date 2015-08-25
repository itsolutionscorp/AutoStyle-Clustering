import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;


public class Sequence<T> implements Iterable<T> {
		
	    // instance variables
		private T mytype;
	    public T[] myValues;   // put in the value in array list
	    int myCount;                // number of array cells used by sequence

	    // constructor
	    // capacity: actual size of the array or the (temporary) maximum
	    // number of elements it can hold
	    public Sequence (int capacity) {
	    	myValues = (T[])new Object[capacity];
	        // YOUR CODE HERE
	    }

	    // Add the argument to the sequence by placing it in the first
	    // unused spot in the array and incrementing the count.
	    // Assume that the sequence isn't full.
	    public void add(T toBeAdded) {
	    	if (myCount == myValues.length) {
	    		System.err.println("The sequence is full");
	    		System.exit(1);
	    	}
	    	myValues[myCount]=toBeAdded;
	    	myCount++;
	    	//insert(toBeAdded, myCount);
	    }
	  

	    public boolean contains (T k) {
	    	for (int i = 0; i < myCount; i++) {
	    		if (myValues[i].equals(k)) {
	    			return true; 
	    		}
	    	}
	    	return false; 
	    }
	    
		public void insert  (T newInt, int pos) { 
		myCount++;
		
		for (int i = myCount-1; i > pos; i--){
			myValues[i]=myValues[i-1];
		}
			//System.out.print(pos);
			myValues[pos] = newInt;	
		}

		// YOUR CODE HERE}
				//System.out.println(a);
			// YOUR CODE HERE}
		
	    // other methods go here
	    public boolean isEmpty() { 
	    	if (myCount == 0) {
	    		return true; 
	    	}
	    return false; 
	    }
	    public int size() {
	    	return myCount;
	    }
	    public T elementAt(int pos) {//it seems I need to add a return integer at the end of the if b/c I get the x mark on the left 
	    	//saying so. But this is odd since I was given the system.exit(1) to exit the method. Ask Amit
	    	if ((pos < 0) || (pos > (myValues.length - 1))) { 
	    		System.err.print("We can not retrive an element because there is no element at the index  ");
	    	//	System.exit(1);
	    		return null; 
	    	} else {
	    	return myValues[pos]; 
	    	}
	    }
	    public String toString() {
	    	String sentence = "";
	    	for (int i = 0; i < myCount; i++) {
	    		if (i == (myCount - 1)) {
	    			sentence += myValues[i];
	    		} else { 
	    			sentence += myValues[i] + " ";
	    		}
	    	}
	    	return sentence; 
	    }
	    public T remove(int pos) { 
	    	if ((pos < 0) || (pos >= myCount)) {
	    		System.err.print("There is nothing to remove because there is no element at the index  ");
	        	//	System.exit(1); //not working, need to ask Amit
	        		return null;
	    	} else {
	    	T specificElement = myValues[pos];
	    	int i = 0; 
	    	while (i < (myCount - 1)) { 
	    		if (i < pos) { 
	    			myValues[i] = myValues[i];
	    			
	    		} else {
	    			myValues[i] =myValues[i + 1];
	    		}
	    		i++;
	    }
	    	myCount--; 

	    	return specificElement; 
	    }
	    }

		@Override
		public Iterator<T> iterator() {
			// TODO Auto-generated method stub
			
			return new SequenceIterator(this);
		}

		@Override
		public void forEach(Consumer<? super T> action) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Spliterator<T> spliterator() {
			// TODO Auto-generated method stub
			return null;
		}
		
		public class SequenceIterator implements Iterator<T>{
			private int index;
			private Sequence<T> newsequence;
			private int Size;
			public SequenceIterator(Sequence<T> a){
				index = 0;
				Size = a.size();
				newsequence = a;
				
			}
			
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				if(index == Size){
				return false;
				}else{
					return true;
				}
			}

			@Override
			public T next() {
				// TODO Auto-generated method stub
				
				index = index+1;
				return newsequence.elementAt(index-1);
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void forEachRemaining(Consumer action) {
				// TODO Auto-generated method stub
				
			}
			
		}
	}


