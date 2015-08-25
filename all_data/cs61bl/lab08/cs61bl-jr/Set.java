public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		contains = new boolean [maxElement];
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
		boolean empty = true;
		for (int i = 0; i < contains.length; i++){
			if (contains[i]){
				empty = false;
			}
		}
		return empty;
	}
	
	private int index;
	
	public void initIterator() {
		index = 0;
	}
	
	public boolean hasNext() {
		for (int i = index; i < contains.length; i++){
			if (contains[i] == true){
				return true;
			}
		}
		return false;
	}
	
	public int next() {
		int number = 0;
		for (int i = index; i < contains.length; i++){
			if (contains[i] == true){
				number = i;
				index = i+1;
				break;
			}
		}
		return number;
	}
}
