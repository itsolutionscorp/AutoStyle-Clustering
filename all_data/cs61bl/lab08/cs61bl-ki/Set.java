public class Set {

	private boolean[] contains;
	int nextIndex;
	
	public Set (int maxElement) {
		this.contains = new boolean[maxElement];
	}
	
	public void initIterator(){
		nextIndex = 0;
	}
	
    public int next() {
    	while (!contains[nextIndex] && nextIndex < contains.length){
    		nextIndex++;
    	}
    	int rtn = nextIndex;  		
    	nextIndex++;
    	while (nextIndex < contains.length && !contains[nextIndex]){
    		nextIndex++;
    	}
    	return rtn;
    }

    public boolean hasNext() {
    	return nextIndex < contains.length ;
    }
    
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		this.contains[k]=true;
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
		this.contains[k]=false;
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		System.out.println(this.contains[k]);
		return this.contains[k];
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		for(int i=0;i<this.contains.length;i++){
			if (this.contains[i]){
				return false;
			}
		}
		return true;
	}

}

