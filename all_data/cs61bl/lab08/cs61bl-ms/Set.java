public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement.

	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	private int nextIndexToReturn; //
	private int index;
	private int myCount; //

	// Initialize a set of ints from 0 to maxElement-1.
	public Set(int maxElement) {
		contains = new boolean[maxElement];
	}

	public void initIterator() {
		index = 0;
		nextIndexToReturn = 0;
	}

	public boolean hasNext(){
		return (index < myCount);
	}

	public int next() {
		int valToReturn = -1;
		while (hasNext()) {
			if (contains[nextIndexToReturn]) {
				valToReturn = nextIndexToReturn;
				index++;
				nextIndexToReturn++;
				break;
			} else {
				nextIndexToReturn++;
			}
		}
		return valToReturn;
	}

	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert(int k) {
		if (0 <= k && k < contains.length) {
			contains[k] = true;
			myCount++;
		}
	}

	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove(int k) {
		if (0 <= k && k < contains.length) {
			contains[k] = false;
		}
	}

	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member(int k) {
		if (0 <= k && k < contains.length && contains[k] == true) {
			return true;
		} else {
			return false;
		}
	}

	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		boolean temp = true;
		for (int i = 0; i < contains.length; i++)
			if (contains[i] == true) {
				temp = false;
				break;
			}
		return temp;
	}

}
