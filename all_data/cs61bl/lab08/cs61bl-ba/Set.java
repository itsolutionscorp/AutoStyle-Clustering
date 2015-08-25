public class Set {

	private int maxElement = 0;
	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement.

	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	private int myPos;

	// Initialize a set of ints from 0 to maxElement-1.
	public Set(int maxElement) {
		this.maxElement = maxElement;
		contains = new boolean[maxElement];
		for (int i = 0; i < maxElement; i++)
			contains[i] = false;
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

	public void initIterator() {
		myPos = 0;
	}

	public boolean hasNext() {
		for (int i = myPos; i < maxElement; i++)
			if (contains[i])
				return true;
		return false;
	}

	public int next() {
		for (int i = myPos; i <= maxElement ; i++) {
			try {
				if (contains[i]) {
					myPos = i + 1;
					return i;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 666;
	}
}
