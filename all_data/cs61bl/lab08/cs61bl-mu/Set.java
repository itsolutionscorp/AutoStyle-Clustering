public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 

	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	
	
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		contains = new boolean[maxElement];
		for(int i = 0; i < maxElement-1; ++i){
			contains[i] = false;
			
		}
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		if(k >= 0 || k < contains.length){
			contains[k] = true;		
		}
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
		if(k >= 0 || k < contains.length){
			contains[k] = false;		
		}
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		if(k > 0 || k < contains.length){
				if(contains[k] == true){
					return true;
			    }else{
			    	return false;
			    }
	}
		return false;
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		for(int i = 0; i < contains.length-1; ++i){
			if(contains[i] == false){
				continue;
			}else{
				return false;
			}
		}
		return true;
	}
	 int nextIndexToReturn;
	    
	     // number of values in the sequence
	public void initIterator(){
		 nextIndexToReturn = 0;
	}
	public boolean hasNext(){
		
        int i =nextIndexToReturn;
        while(i < contains.length){
        	if(contains[i] == true){
        		return true;
        	}
        	i++;
        }
        return false;
	}
	public int next(){
		while(true){
		boolean valToReturn = contains[nextIndexToReturn];
		if(valToReturn == true){
			int i = nextIndexToReturn;
			nextIndexToReturn++;
			 return i;	
		}
        nextIndexToReturn++;
      
        }
		
	}

}
