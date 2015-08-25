public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	private int iteratorIndex = 0;
	
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		contains = new boolean [maxElement];
		for (int i=0; i<maxElement; i++){
			contains[i]=false;
		}
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		if (k>=0 && k < contains.length){
			contains[k]=true;
		}
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
		if (k>=0 && k < contains.length){
			contains[k]=false;
		}
	
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		if (contains[k]==false){
			return false;
		}
		return true;
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		for (int i=0; i< contains.length; i++){
			if (contains[i]==true){
				return false;
			}
		}
		return true;
	}
	
	public void initIterator(){
		iteratorIndex = 0;
	}
	
	public boolean hasNext(){
		for (int i=iteratorIndex; i< contains.length; i++){
			if (contains[i]==true){
				return true;
			}
		}
		return false;
	}
	
	public int next(){
		for (int i=iteratorIndex; i< contains.length; i++){
			iteratorIndex++;
			if (contains[i]==true){
				return i;
			}
		}
		System.out.println("no next number, return 0");
		return 0;
	}

}
