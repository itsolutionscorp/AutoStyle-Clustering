public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement.

	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	int currentPos;
	int lastValue;
	int nextValue;

	public void initIterator() {
		lastValue = -1;
		currentPos = 0;
		nextValue = 0;
	}

	public boolean hasNext() {
		nextValue = lastValue + 1;
		if (nextValue >= contains.length) {
			return false;
		}
		while (nextValue + 1 < contains.length && contains[nextValue] == false) {
			nextValue++;	
		}	
		if (contains[nextValue] == true) {
			return true;
		}
		return false;
	}

	public int next() {
		while (contains[currentPos] != true) {
			currentPos++;
		}	
		lastValue = currentPos;
		currentPos++;
		return lastValue;
	}

	// Initialize a set of ints from 0 to maxElement-1.
	public Set(int maxElement) {
		contains = new boolean[maxElement];
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
		for (int i = 0; i < contains.length; i++) {
			if (contains[i] == true) {
				return false;
			}
		}
		return true;
	}

}