
public class Set {
	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
		
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	private int nextElement = 0;
		
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		contains = new boolean[maxElement];
	}
	
	public void initIterator() {
		nextElement = 0;
	}
	
	public boolean hasNext() {
		for (int counter = nextElement + 1; counter < contains.length; counter++) {
			if (contains[counter] == true) {
				return true;
			}
		}
		return false;
	}
	
	public int next() {
		if (this.hasNext()) {
			while (contains[nextElement] == false) {
				nextElement++;
			}
			int thisElement = nextElement;
			nextElement++;
			return thisElement;
		} else {
			System.err.println("No more values");
			return -1;
		}
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
		if (contains[k]) {
			return true;
		} else {
			return false;
		}
	}
		
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		boolean check = true;
		for (boolean item : contains) {
			if (item) {
				check = false;
				break;
			} else {
				continue;
			}
		}
		return check;
	}
}
