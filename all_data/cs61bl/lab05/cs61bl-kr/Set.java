public class Set {
	
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	
	public Set (int maxElement) {
	this.contains = new boolean[maxElement];
	
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
//		if (k <= 0 || k > contains.length+1) {
//			return;
//		}
		
//		else {
			this.contains[k] = true;
//z		}
	}
		/*for (y = 0; y < set.length ; y++ ){
		if (set[y] == k) {
		return;
		}
	}*/
		/*for (x = 1; x < set.length; x++) {
				if (set[x] > k && set[x-1] < k) {
					for(int b = x; b < set.length-1; b++) {
						set[b+1] = set[b];
					}
						set[x] = k;
					}
				}
			}*/
			

	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
//		if (k <= 0 || k > this.contains.length+1) {
//			return;
//		}
//		else {
			this.contains [k] = false;
//		}
	
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		return this.contains[k];
	
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		for (int k = 0; k < this.contains.length; k++) {
			if (this.contains[k] == true) {
				return false;
			}
		}
		return true;
		
		}
		

}
