public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	private int value;
	
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		this.contains = new boolean[maxElement];
	
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		contains[k] = true;
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
		contains[k] = false;
	
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		return contains[k];
	
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		for (int i = 0; i<contains.length; i++) {
			if (contains[i]) {
				return false;
			}
		}
		return true;
	}

	public void initIterator() {
		value = 0;
		while (value < contains.length && member(value) == false) {
			value++;
		}
	}
	public boolean hasNext() {
		return (value > contains.length);
	}
	public int next() {
		int next = value;
		value++;
		while (value < contains.length && member(value) == false) {
			value++;
		}
		return next;
	}
}
