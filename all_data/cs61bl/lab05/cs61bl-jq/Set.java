public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		if (maxElement >0)
			contains = new boolean[maxElement];
		else contains = new boolean[0]; 
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		if ((0 <= k)  && (k < contains.length))
			contains[k] = true;
		else System.out.println("fail");
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
		if ((0 <= k)  && (k < contains.length))
			contains[k] = false;
		else System.out.println("fail");
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		if ((0 <= k)  && (k < contains.length))
			if (contains[k]==true){
				return true;
			}
			else return false;
		else return false;

	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		for (int k=0;k<contains.length;k++){
			if(contains[k]==true){
				return false;
			}
		}
		return true;
	}}
