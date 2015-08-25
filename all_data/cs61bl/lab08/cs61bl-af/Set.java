import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;



public class Set implements Iterable<Integer> {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		contains = new boolean[(maxElement)];
		
		for (int i = 0; i <(maxElement-1);i++){
			contains[i]= false;
		}
		for (int i = 0; i < contains.length; i++) {
			System.out.print(contains[i]);
		}
		
	}//finish Set
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		contains[k]=true;
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
		contains[k]=false;
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		return contains[k];
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		int i = 0;
		while(i < contains.length){
			if(contains[i] ==false){
				i++;
			}
			else if(contains[i]==true){
				//System.out.println("not empty");
				return false;
				
			}						
		}
		return true;
	}

	
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		
		return new SetIterator(this);
	}

	@Override
	public void forEach(Consumer<? super Integer> action) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Spliterator<Integer> spliterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public class SetIterator implements Iterator<Integer>{
		private int index;
		private Set newset;
		private int Size;
		public SetIterator(Set a){
			index = 0;
			Size = a.contains.length;
			newset = a;
			
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
		public Integer next() {
			
			index = index+1;
			
			return index;
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


