public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	int nextIndex;
	
	public void initIterator() {
		nextIndex = 0;
	}
	
	public boolean hasNext() {
		int temp = nextIndex; 
		while(temp < contains.length) {
			if(contains[temp]) {
				return true; 
			}
			temp++;
		}
		return false;
	}
	
	public int next() {
		int result; 
		while(!contains[nextIndex]) {
//			System.out.println(nextIndex);
			nextIndex++;
		}
		result = nextIndex;
		nextIndex++;
		return result;
	}
	
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		contains = new boolean[maxElement];
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		if (!contains[k]) {
			contains[k] = true; 
		}
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
		if (contains[k]) {
			contains[k] = false; 
		}
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		if(contains[k]) {
			return true;
		} 
		else 
			return false; 
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		for (int i = 0; i < contains.length; i++) { 
			if(!contains[i]) {
				continue;
			}
			else 
			 return false;
		}
		return true;
	}

}
