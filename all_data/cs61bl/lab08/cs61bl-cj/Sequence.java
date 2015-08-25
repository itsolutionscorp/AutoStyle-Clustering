import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;



public class Sequence <T> implements Iterable<T>{
	
	protected T[] myValues;
	int myCount; // maybe
	
	public Sequence(int capacity){		
		myValues = (T[]) new Object[capacity];
	    myCount = 0;		
	}
	
	public void add(T toBeAdded){
		myValues[myCount] = toBeAdded;
    	myCount++; 
	}
	
	public boolean isEmpty(){
    	return myCount == 0;
    }
	
	public int size(){
    	return myCount;
    }
	
	public T elementAt(int pos){
	    return myValues[pos];
	}
	
	 @Override 
	public String toString(){
	   if (myCount == 0) return "";
	  
	   String temp = new String(String.valueOf(myValues[0]));
	   for (int i = 1; i < myCount; i++){
		   temp = temp+" " + myValues[i];
	   }	   
	   return temp;
	 }
	
	 public void remove(int pos){
	   	  if (pos < 0 || pos > myCount)
		  {
		    System.err.println("Out of Bound");
		    System.exit(1);
		  }
		   
		   for (int i = pos; i < myCount-1; i++){
			   myValues[i] = myValues[i+1];
			}
		   /*if (myValues[myCount-1] instanceof String)
			   myValues[myCount-1] = (T) "";
		   else {
			   myValues[myCount-1] = 0;
		   }*/
		   myCount--;		   
	   }
	 
	 public boolean contains(T k){
		   for (int i = 0; i<myCount;i++){
			   if (myValues[i].equals(k)) return true;
		   }
		   return false;
	   }
	 
	 public void insert(T toInsert, int insertPos) {
	    	
	    	myValues[myCount+1] = myValues[myCount]; // shift the last element to the last+1
	    	for (int i = myCount-1; i>insertPos;i--){
	    		myValues[i] = myValues[i-1];			
			}
	    	myValues[insertPos] = toInsert;        
	        myCount++;
	    }
	
	 private class SeqIterator implements Iterator<T> {
		 private int count;
		 
		 public SeqIterator(){
			 count = 0;
		 }
		 
		@Override
		public boolean hasNext() {			
			return count < myCount;
		}

		@Override
		public T next() {
			T temp = myValues[count];
			count++;
			return temp;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();			
		}

	 }
		 
	 
	 
	public static void main(String[] args){
		Sequence <String> a = new Sequence<String>(5);
		a.add("Hello");
		a.add("Hello2");
		a.add("Hello3");
		a.remove(2);		
		//System.out.println(a);
		//System.out.println(a.contains("Hello"));
		
		Iterator<String> tepm =  a.iterator();
		System.out.print(tepm.next());
		System.out.print(tepm.next());
		
		Sequence <Integer> a1 = new Sequence<Integer>(5);
		a1.add(6);
		a1.add(8);
		//System.out.println(a1);
		
		
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new SeqIterator();
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

	
}
