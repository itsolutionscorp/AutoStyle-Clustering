public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	private int currentIndex;
	
		
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		this.contains = new boolean[maxElement];
		
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		if ((0 <= k) && (k < this.contains.length)){
			this.contains[k] = true;
		}
		else {
			System.out.println("outside of bounds");
			
		}
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
		if ((0 <= k) && (k < this.contains.length)){
			this.contains[k] = false;
		}
		else {
			System.out.println("outside of bounds");
		}
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		if ((0 <= k) && (k < this.contains.length)){
			if (this.contains[k] == true) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			System.out.println("outside of bounds");
			return false;
			
		}
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		for (int k=0;k <= this.contains.length-1; k++){
			if (this.contains[k] == true) {
				return false;
			}
		}
		return true;
	}
	
	public void initIterator() {
		currentIndex = 0;
	}
	
	public boolean hasNext() {
		for (int i = currentIndex ; i < contains.length ; i++) {
			if(contains[i]) return true;
		}
		return false;
	}
	public int next() {
		for (int i = currentIndex ; i < contains.length ; i++) {
			if(contains[i]) {
				currentIndex = i + 1;
				return i;
			}
		}
		
		return -1;
	}

}
