public class Set {
	private boolean[] mySet;
	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		mySet = new boolean[maxElement];
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		mySet[k] = true;
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
		mySet[k] = false;
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		if (mySet[k] == true){
			return true;
		} else {
			return false;
		}
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		boolean empty = true;
		for (int i = 0; i < mySet.length - 1; i++) {
			if (mySet[i] == true) {
				empty = false;
				break;
			}
		}
		return empty;
	}

}
