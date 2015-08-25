public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	// Initialize a set of ints from 0 to maxElement-1.
	private int iterIndex = -1;
	public Set (int maxElement) {
		contains= new boolean[maxElement];
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		if (k<0 || k>=contains.length) {
			return;
		}
		contains[k]=true;
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
		if (k<0 || k>=contains.length) {
			return;
		}
		contains[k]=false;
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		if (k<0 || k>=contains.length) {
			return false;
		}
		return contains[k];
	}
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		for (int k=0; k<contains.length; k++) {
			if (contains[k]) {
				return false;
			}
		}
		return true;
	}
	public void initIterator() {
		iterIndex++;
	}
	public boolean hasNext() {
		if (iterIndex == -1) {
			throw new IllegalStateException("not initiated yet");
		}
		for (int k = iterIndex; k<contains.length; k++) {
			if (contains[k]) {
				return true;
			}
		}
		return false;
	}
	public int next() {
		if (iterIndex == -1) {
			throw new IllegalStateException("not initiated yet");
		}
		if (hasNext()) {
			while (!contains[iterIndex]) {
				iterIndex++;
			}
			int toReturn = iterIndex;
			iterIndex++;
			return toReturn;
		}
		else {
			throw new IllegalStateException("no next element");
		}
	}
}
