
public class Set {
	
	
	// contains[k] is true if k is in the set, false if it isn't
	private int position;
	private boolean[] contains;
	
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		contains = new boolean[maxElement];
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
		return contains[k];
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		for(int i=0;i<contains.length;i++){
			if(contains[i])
				return false;
		}
		return true;
	}
	
	public void initIterator(){
		position = 0;
	}
	public boolean hasNext(){
		int count = 0;
		for(int i = position;i<contains.length;i++){
			if(contains[i])
				count++;
		}
		return count > 0;
	}

	public int next(){
		while(!contains[position]){
			position++;
		}
		int retVal = position;
		position++;
		return retVal;
	}
}
