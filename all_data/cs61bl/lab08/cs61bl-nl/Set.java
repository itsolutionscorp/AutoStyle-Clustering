public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private int index;
	private boolean[] contains;
	private int length;
	
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		length = maxElement;
		contains = new boolean[maxElement];
	}
	
	public void initIterator(){
		index = 0;
	}
	
	public int next() {
		int current = index;
		if (contains[index] == true) {
			index++;
			return current;
		} else {
			while (contains[index] == false){
				index++;
			}
			current = index;
			index++;
			return current;
		}
	}
	
	public boolean hasNext() {
		int numberOfElementsLeft = 0;
		for (int i = index; i<length; i++){
			if (contains[i] == true){
				numberOfElementsLeft++;
			}
		}
		return (numberOfElementsLeft != 0);
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		if (0 > k || length <= k) System.out.println("Out of bounds");
		contains[k] = true;
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
		if (0 > k || length <= k) System.out.println("Out of bounds");
		contains[k] = false;
	
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		if (0 > k || length <= k) System.out.println("Out of bounds");
		if (contains[k]) {
			return true;
		} else {
			return false;
		}
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		for (int k = 0; k < length; k++) {
			if (contains[k]) {
				return false;
			}
		}
		return true;
	}

}
