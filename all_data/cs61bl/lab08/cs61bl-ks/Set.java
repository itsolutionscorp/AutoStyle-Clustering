public class Set {
	
	private int index;
	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	
	//Keeps track of the biggest value allowed
	private int max;
	
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		this.max = maxElement - 1;
		this.contains = new boolean[maxElement];
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		if (k < 0 || k > max) {
			return;
		}
		this.contains[k] = true;
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
		if (k < 0 || k > max) {
			return;
		}
		this.contains[k] = false;
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		if (k < 0 || k > max) {
			return false;
		}
		if (this.contains[k]) {
			return true;
		}
		return false;
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		for (int i = 0; i < max; i++) {
			if (this.contains[i] == true) {
				return false;
			}
		}
		return true;
	}
	
	public void initIterator() {
		index = 0;
	}
	public boolean hasNext() {
		if (index > max) {
			return false;
		}
		for (int i = index; i <= max; i++) {
			if (contains[i] == true) {
				return true;
			}
		}
		return false;
	}
	public int next() {
		for (int i = index; i <= max; i++) {
			if (contains[i] == true) {
				index = i + 1;
				return i;
			}
		}
		System.exit(0);
		return 0;
	}
	
	
	
	

}
