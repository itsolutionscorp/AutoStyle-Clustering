public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	private int index;
	
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
	  this.contains = new boolean[maxElement];
	}
	
	public void initIterator() {
		index = -1;
	}
	public boolean hasNext(){
		if (index >= contains.length){
			return false;
		}
		return true;
	}
	
	public int next(){
		int rtn;
		try {
		while (contains[index] == false){
			index++;
		}
		} catch (ArrayIndexOutOfBoundsException e){
			System.err.println(e);
		}
		rtn = index;
		index++;
		return rtn;
	}
	
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		contains[k]=true;
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
		contains[k]=false;
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		if (contains[k]){
			return true;
		} else {
			return false;
		}
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		for (int c = 0; c<contains.length; c++){
			if (contains[c]){
				return false;
			}
		}
		return true;
	}

}
