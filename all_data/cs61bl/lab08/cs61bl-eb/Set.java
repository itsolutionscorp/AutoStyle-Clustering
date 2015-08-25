public class Set {
	
	
	/********************************Iterator********************************/
	
	
	private int index; 
	
	
	public void initIterator() {
		index = 0;  // have to reset index to 0 
	}
	
	/* this method shouldn't change any state (i.e. index) */ 
	public boolean hasNext() {
		for (int i = index; i < contains.length; i++) {
			if (contains[i] == true) {
				return true; // we do have next
			}
		}
		return false; 
	}
	
	public int next() {
		for (int i = index; i < contains.length; i++) {
			if (contains[i] == true) {
				index = i + 1; 
				return i; // we do have next
			}
		}
		/*
		 * Q: What if someone calls next() when hasNext() returns false?
		 * A: This violates the contract of your code and you can crash or 
		 *    do whatever you want. We won't be testing this.
		 */
		System.err.println("There is no more element in this set");
		System.exit(-1);
		return -1; // this line will never get executed, does no matter 
	}
	
	
	/********************************Iterator********************************/

	
	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains; // declaration of boolean array, "contains" is just
	// memory location; before assignment it stores null
	// this pointer is an instance variable which can be used later methods 
	
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		contains = new boolean[maxElement]; // instantiation (new) and assignment (=)
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		contains[k] = true; // you have inserted "k" into the set 
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
		for (int i = 0; i < contains.length; ++i) {
			if (contains[i]) {
				return false; // not empty if you find one "true" 
			} 
		}
		return true; 
	}

}
