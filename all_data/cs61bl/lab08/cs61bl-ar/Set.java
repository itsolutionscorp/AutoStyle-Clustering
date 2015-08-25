public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	private int iteratorindex=0;
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
	contains = new boolean [maxElement];
	for ( int count = 0 ; count <= maxElement - 1 ; count ++){
		contains [count] = false ;
	}
	}
	public void initIterator(){
		iteratorindex=0;
	}
	public boolean hasNext(){
		if(iteratorindex==contains.length-1){
			return false;
		}
		return true;
	}
	public boolean next(){
		boolean var=contains[iteratorindex];
		iteratorindex++;
		return var;
	}
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		contains [k] = true;
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
	   if ( contains[k] != false )
	   {
	       contains[k] = false ;
	   }
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
	   if ( contains [k] == true){
		   return true;
	   }
	   return false;
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		for ( int count = 0 ; count <= contains.length -1 ; count ++){
			if ( contains [ count ] == true ){
				return false;
			}
		}
		return true;
	}

}