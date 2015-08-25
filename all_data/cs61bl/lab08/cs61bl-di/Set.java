import java.util.NoSuchElementException;
public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement.

	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	private int iterIndex;
	private int myCount;
	private int[] newSet;
	int rtn;

	// Initialize a set of ints from 0 to maxElement-1.
	public Set(int maxElement) {
		newSet = new int[maxElement];
		contains = new boolean[maxElement];
		myCount = maxElement;
	}

	public void initIterator() {
	}

	public boolean hasNext() {
		for (int i = iterIndex; i < myCount; i++) {
			if (contains[i] == true) {
				return true;
			}
		}
		return false;
	}

	public int next() throws NoSuchElementException {
		for (int i = iterIndex; i < myCount; i++) {
			iterIndex++;
			if (contains[i]) {
				rtn = newSet[i];
				break;
				
			}
		}
		return rtn;
	}

	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert(int k) {
		if (k >= 0 && k < contains.length) {
			contains[k] = true;
			newSet[k] = k;
		}
	}

	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove(int k) {
		if (k >= 0 && k < contains.length) {
			contains[k] = false;
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
		for (int i = 0; i < contains.length; i++) {
			if (contains[i]) {
				return false;
			}
		}
		return true;

	}

}
