public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	private int nextIndex = 0;
	private int finalIndex;
	private int index;
	
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		this.contains = new boolean[maxElement];

	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		this.contains[k] = true;
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
		this.contains[k] = false;
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		return this.contains[k];
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		for (int i = 0;i < this.contains.length;i++) {
			if (this.contains[i] == true){
				return false;
			}
		}
		return true;
	}
	public void initIterator() {
		index = 0;
		nextIndex = 0;
		finalIndex = 0;
		for (int i=0;i<contains.length;i++) {
			if (contains[i]) {
				finalIndex++;
			}
		}
	}
	
	public boolean hasNext() {
		return index<finalIndex;
	}
	public int next() {
		while (true) 
		{
			if (contains[nextIndex]) 
			{
				int result = nextIndex;
				nextIndex++;
				index++;
				return result;
			}
			nextIndex++;
		}
		
	}
}
