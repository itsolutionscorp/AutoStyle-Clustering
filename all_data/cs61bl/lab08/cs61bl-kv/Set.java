public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	private int index;
	private boolean startIter;
	
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		 this.contains = new boolean[maxElement];
		 this.startIter = false;
	}
	
	public void initIterator() {
		this.index = -1;
		this.startIter = true;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public boolean hasNext() {
		if ((this.index + 1) < this.contains.length) {
		for (int k = this.index + 1; k < this.contains.length; k++) {
			if (this.contains[k]) {
				return true;
			}
		}
		}
		return false;
	}
	
	public int next(){
		if (!this.startIter || !this.hasNext()){
			System.out.println("You suck. Please go away.");
			System.exit(1);
		} else {
			for (int k = this.index + 1; k + 1 < this.contains.length; k++) {
				if (this.contains[k]) {
					this.index = k;
					return k;
				}
			}
		}
		
		System.exit(1);
		return index;
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		if (k < 0 || k > this.contains.length) {
			return;
		}
		this.contains[k] = true;
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
		if (k < 0 || k > this.contains.length) {
			return;
		}
		this.contains[k] = false;
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		if (k < 0 || k > this.contains.length) {
			return false;
		}
		return this.contains[k];
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		if (this.contains == null) {
			return true;
		}
		for (int k = 0; k < this.contains.length; k++) {
			if (this.contains[k]) {
				return false;
			}
		}
		return true;
	}

}
