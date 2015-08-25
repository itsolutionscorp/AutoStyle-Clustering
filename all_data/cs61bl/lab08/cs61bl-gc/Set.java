public class Set {


	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	int nextIndexToReturn;
	int max;
	
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		contains = new boolean [maxElement];
		max = maxElement;
	}
	
	public void initIterator() {
		nextIndexToReturn = 0;
	}
	
	public boolean hasNext() {
		if (nextIndexToReturn > max - 1)
			return false;
		for (int i = nextIndexToReturn; i < max; i++) {
			if (contains[i])
				return true;
		}
		return false;
	}
	
	public int next() {
		boolean searching = true;
		while (searching) {
			if (contains[nextIndexToReturn])
				searching = false;
			nextIndexToReturn++;
		}
		return nextIndexToReturn - 1;
		
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		if (k<0||k>=this.contains.length)
			return;
		this.contains[k] = true;
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
		if (k<0||k>=this.contains.length)
			return;
		this.contains[k] = false;
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		if (k<0||k>=this.contains.length)
			return false;
		return this.contains[k];
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		for (int i = 0;i<this.contains.length;i++){
			if(this.contains[i]==true)
				return false;
		}
		return true;
	}

}
