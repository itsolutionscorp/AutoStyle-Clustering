public class Set {
/**
 * Represent a set of nonnegative ints from 0 to maxElement-1
 * for some initially specified maxElement.
 * 
 */
	private boolean[] contains;
	private int index;
	
	/**
	 * Initialize a set of ints from 0 to maxElement - 1.
	 * @param maxElement
	 */
	public Set (int maxElement) {
		this.contains = new boolean[maxElement];
	}
	
	/**
	 * Makes Set an iterator.
	 */
	public void initIterator() {
	    index = 0;
	}

	public int next() {
		while (!contains[index])
		    index++;
		index++;
		return index - 1;
	}

	public boolean hasNext() {
	    int test = index;
	    while (test < contains.length) {
	    	if (contains[test])
	    		return true;
	    	test++;
	    }
		return false;
	}
	
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		if (k >= 0 && k < this.contains.length) {
			this.contains[k] = true;
		}
	}
	
	/**
	 * Removes element from index k
	 * precondition: 0 <= k < maxElement
	 * postcondition: k is not in set.
	 * @param k index to remove
	 */
	public void remove (int k) {
		if (k >= 0 && k < this.contains.length) {
			this.contains[k] = false;
		}
	}
	
	/**
	 * Return true if k is in this set, false otherwise
	 * precondition: 0 <= k < maxElement
	 * @param k test if k exists within set
	 * @return true if k is in set, false otherwise
	 */
	public boolean member (int k) {
		if (k >= 0 && k < this.contains.length) {
			return this.contains[k];
		} 
	    return false;
	}
	
	/**
	 * Return true if set is empty
	 * @return true if set is empty, false otherwise
	 */
	public boolean isEmpty() {
		if (this.contains == null) {
			return true;
		}
		
		for (int i = 0; i < this.contains.length; i++) {
			if (this.contains[i] == true) {
				return false;
			}
		}
		return true;
	}

}
