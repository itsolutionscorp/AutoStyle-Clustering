public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement.

	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	private int index;
	private int count;
	private int num;

	// Initialize a set of ints from 0 to maxElement-1.
	public Set(int maxElement) {
		contains = new boolean[maxElement];
		count = 0;
	}

	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert(int k) {
		if (!contains[k]) {
			contains[k] = true;
			count++;
		}
	}

	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove(int k) {
		if (contains[k]) {
			contains[k] = false;
			count--;
		}
	}

	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member(int k) {
		if (contains[k]) {
			return true;
		} else {
			return false;
		}
	}

	// Return true if this set is empty, false otherwise.

	public boolean isEmpty() {
		if (contains == null) {
			return true;
		}
		boolean condition = false;
		for (int i = 0; i < contains.length; i++) {
			if (contains[i] == true) {
				condition = true;
				break;
			}
		}
		return !condition;
	}
	
	public void initIterator() {
		index = 0;
		num = 0;
	}
	
	public boolean hasNext() {
		return index < count;
	}
	
	public int next() {
		for (int i = num; i < contains.length; i++) {
			if (contains[i]) {
				num = i + 1;
				index++;
				return i;
			}
		}
		return -1;
	}
}