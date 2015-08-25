public class Set {
	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	boolean[] maxArray;
	private int index;
	
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		maxArray = new boolean[maxElement];
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		maxArray[k] = true;
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
		maxArray[k] = false;
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		return maxArray[k];
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		int i = 0;
		while (i < maxArray.length) {
			if (maxArray[i]) {
				return false;
			} else
				i++;
		}
		return true;
	}
	
	public void initIterator() {
		index = -1;
	}
	
	public boolean next() {
		index++;
		return maxArray[index];
	}
	
	public boolean hasNext() {
		return (index + 1) < maxArray.length;
	}
}