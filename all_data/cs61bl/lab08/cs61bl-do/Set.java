public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	private int nextIndex;
	
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		contains = new boolean[maxElement];
	}
	
	public void initIterator(){
		nextIndex = 0;
	}
	
	public boolean hasNext(){
		for(int i = nextIndex; i < contains.length; i++){
			if(contains[i])
				return true;
		}
		return false;
	}
	
	public int next(){
		for(int i = nextIndex; i < contains.length; i++){
			if(contains[i]){
				nextIndex = i + 1;
				return i;
			}
		}
		System.err.println("No values found");
		return 0;
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		if(k >= 0 && k < contains.length){
			contains[k] = true;
		}
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
		if(k >= 0 && k < contains.length){
			contains[k] = false;
		}
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		if(k >= 0 && k < contains.length)
			return contains[k];
		else{
			return false;
		}
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		for (int i = 0; i < contains.length; i++)
			if(contains[i])
				return false;
		return true;
	}

}