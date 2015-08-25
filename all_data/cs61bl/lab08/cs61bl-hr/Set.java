public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	private int iteratorIndex;
	private boolean done;
	
	
	public void initIterator() {
		iteratorIndex = 0;
		done = false;
	}
	
	public boolean hasNext() {
		if (done) {
			return false;
		} else if (iteratorIndex == contains.length - 1) {
			done = true;
		}
		return true;
	}
	
	public boolean next() {
		boolean val = contains[iteratorIndex];
		this.iteratorIndex++;
		return val;
	}
	
	
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		contains = new boolean[maxElement];
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		if (k >= 0 && k < contains.length) {
			contains[k] = true;
		}
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
		if (k >= 0 && k < contains.length){
			contains[k] = false;
		}
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		if (k >= 0 && k < contains.length){
			return contains[k];
		}
		else {
			return false;
		}
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		initIterator();
		while (this.hasNext()) {
			boolean value = this.next();
			if (value) {
				return false;
			}
		}
		return true;
	}

}
