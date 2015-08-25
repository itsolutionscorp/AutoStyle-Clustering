public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	private int index = -1;
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	
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
        for (int i = 0; i < contains.length; i ++) {
            if (contains[i]) {return false;}
        }
        return true;
	}
	
	public void initIterator() {
		index = -1;
	}
	
	public boolean hasNext () {
		boolean found = false;
		for (int i = index+1; i < contains.length; i ++) {
			if (contains[index]) {
				found = true;
				break;
			}
		}
	    return found;
	}
	
	public int next() {
		index ++;
		boolean found = false;
		for (; index < contains.length; index ++) {
			if (contains[index]) {
				found = true;
				break;
			}
		}
		if (found) {
			return index;
		}
		else {
			throw new RuntimeException("No more elements.");
		}
	}

}
