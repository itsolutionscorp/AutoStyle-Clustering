public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	private int max;
	public int index; // ounter for iterator
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		max = maxElement;
		contains = new boolean[maxElement];
		for (int i=0; i<maxElement; i++) {
			contains[i] = false;
		}
	}
	
	//iterator methods
	public void initIterator() {
		this.index = -1;
	}
	
	public boolean next() {
	    this.index++;
	    return this.contains[this.index];
	}
	
	public boolean hasNext() {
	    return index < this.max-1 ;
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		if (0 <= k && k < this.max) {
			contains[k] = true;
		} 
		return;
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
		if (0 <= k && k < this.max) {
			contains[k] = false; 
		}
		return;
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		if (0 <= k && k < this.max) {
			return contains[k]; }
		else return false;
		
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		for(int k=0;k < contains.length;k++) {
			if (contains[k]) return false;
		}
		return true ;
		
	}

}
