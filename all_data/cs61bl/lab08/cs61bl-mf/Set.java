public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	int myCount = 0;
	
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		contains = new boolean[maxElement];
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
		for(boolean item : contains) {
			if (item == true) {
				return false;
			}
			}
		return true;
		}
	
	public void initIterator(){
		 myCount = 0;
	}
	
	public boolean hasNext() {
		for (int index = myCount; index < contains.length; index++) {
			if (contains[index]) {
				return true;
			}
		} return false;
	}
	
	public int next() {
		if (!hasNext()) {
			System.err.println("The iterator does not have a next value.");
			System.exit(1);
		}
		int rtn = 0;
		while (myCount < contains.length) {
			if (contains[myCount]) {
				rtn = myCount;
				myCount++;
				break;
			} else {
				myCount++;
			}
			} return rtn;
	} 
}

