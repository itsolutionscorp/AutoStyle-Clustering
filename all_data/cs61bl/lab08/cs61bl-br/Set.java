public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement.

	// contains[k] is true if k is in the set, false if it isn't
	 boolean[] contains;
	private int nextIndexToReturn;
	private boolean atEnd;

	// Initialize a set of ints from 0 to maxElement-1.
	public Set(int maxElement) {
		contains = new boolean[maxElement];
	}

	public void initITerator() {
		for (int k = 0; k < contains.length; k++) {
			if (contains[k]) {
				nextIndexToReturn = k;
				break;
			}
		}
		atEnd = false;
	}

	public boolean hasNext() {
		if (atEnd) {
			return false;
		} else {
			for (int k = nextIndexToReturn; k < contains.length; k++) {
				if (contains[k]) {
					return true;
				}
			}
			return false;
		}
	}

	public int next() {
		int valToReturn = nextIndexToReturn;
		for (int k = nextIndexToReturn + 1; k <= contains.length; k++) {
			if (k == contains.length) {
				atEnd = true;
			} else if (contains[k]) {
				nextIndexToReturn = k;
				break;
			}
		}
		return valToReturn;
	}

	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert(int k) {
		contains[k] = true;
	}

	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove(int k) {
		contains[k] = false;
	}

	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member(int k) {
		return contains[k];
	}

	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		boolean setEmpty = true;
		for (int k = 0; k < contains.length; k++) {
			if (contains[k] == true) {
				setEmpty = false;
				break;
			}
		}
		return setEmpty;
	}

}
