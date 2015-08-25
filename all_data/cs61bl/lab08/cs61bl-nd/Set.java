public class Set{

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	private int index;
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
	contains = new boolean[maxElement];
	}
	public void initIterator(){
		index = 0;
	}
	public boolean hasNext(){
		return ((index + 1) < contains.length);
	}
	public int next(){
		int curr = index; 
		index += 1;
		return curr; 
	}
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		for (int i = 0; i <= k; i++){
			if (i == k){
				contains[i]= true;
				break;
			}
		}
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
	contains[k] = false;
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		if(contains[k]== true){
			return true;
	}
		return false;
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		for (int i = 0; i < contains.length; i++){
			if (contains[i] == true){
				return false;
			}
			else if ((i == contains.length -1) && (contains[i] != true)){
				return true;
			}
		}
		return true;
	}

}