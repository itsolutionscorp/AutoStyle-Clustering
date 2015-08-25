public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement.

	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	int nextIndexToReturn;

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
			if (contains[i]) {
				return false;
			}
		}
		return true;
	}

	public void initIterator() {
		nextIndexToReturn = 0;
	}

	public int next() {
//		if (nextIndexToReturn < contains.length) {
//			for (int i = nextIndexToReturn; i < contains.length; i++) {
//				if (contains[i] == true)
//					return i;
//			}
//			System.err.println("We have run out of numbers to return.");
//			return -1;
//		} else
//			System.err.println("We have run out of numbers to return.");
//		return -1;
//	}	
		if (nextIndexToReturn < contains.length) {
        int currentidx = nextIndexToReturn;
        boolean currentval = contains[nextIndexToReturn];
        nextIndexToReturn++;
        if (currentval) {
        return currentidx;
        }
        else {
                return next();
        }}else{
        	System.err.println("cannot find any value");
        	return -1;
        }
               
}

	public boolean hasNext() {
		if (nextIndexToReturn < contains.length) {
			for (int i = nextIndexToReturn; i < contains.length; i++) {
				if (contains[i] == true)
					return true;
			}
			return false;
		} else
			return false;
	}

	/* setter functions for test purpose */
	public void setContains(boolean[] contains) {
		this.contains = contains;
	}

	public boolean[] getContains() {
		return contains;
	}
}
