public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	int members = 0;
	int count;
	int index = 0;
	
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		contains = new boolean[maxElement];
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		if (!contains[k]) {
			contains[k] = true;
			members++;
		}
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
		if (contains[k]) {
			contains[k] = false;
			members--;
		}
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		return contains[k];
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		return members == 0;
	}
	
	public void initIterator() {
		count = 0;
	}
	
	public boolean hasNext() {
		return count < members;
	}
	
	public int next() {
		while (contains[index] != true) {
			index++;
		}
		count++;
		return index;
	}

}