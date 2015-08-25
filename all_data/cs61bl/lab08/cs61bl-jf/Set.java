public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
		
		
	
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		contains = new boolean[maxElement];
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		if (k < 0 || k >= contains.length) {
			System.err.println("Error: Invalid Insert number");
			System.exit(1);
		}
		contains[k]	= true;
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
		if (k < 0 || k >= contains.length) {
			System.err.println("Error: Invalid Remove number");
			System.exit(1);
		}
		contains[k] =false;
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		if (k < 0 || k >= contains.length) {
			System.err.println("Error: Invalid Member request");
			System.exit(1);
		}
		return contains[k];
	
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		for(int i = 0; i < contains.length ; i++){
			if(contains[i]){
				return false;
			}	
		}
		return true;
	
	}
	
	//Iterator variables
	private int nextElementToReturn = -1;
	private boolean done = false;
	
	public void initIterator() {
		done = false;
		for (int i=0; i<contains.length;i++) {
			if (contains[i]) {
				nextElementToReturn = i;
				return;
			}
		}
		done = true;
	}
	public boolean hasNext() {
		return !done;
	}
	public int next() {
		int elementToReturn = nextElementToReturn;
		for (int i=nextElementToReturn+1; i<contains.length;i++) {
			if (contains[i]) {
				nextElementToReturn = i;
				return elementToReturn;
			}
		}
		done = true;
		return elementToReturn;
	}

}
