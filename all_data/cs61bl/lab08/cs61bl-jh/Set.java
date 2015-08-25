package lab08;

public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
		// for some initially specified maxElement. 
		int index;
		// contains[k] is true if k is in the set, false if it isn't
		private boolean[] contains;
		
		// Initialize a set of ints from 0 to maxElement-1.
		public Set (int maxElement) {
			contains=new boolean[maxElement];
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
			if(contains[k]==true)
				return(true);
			else
				return(false);
		}
		
		// Return true if this set is empty, false otherwise.
		public boolean isEmpty() {
			int n=0;
			for(int k=0;k<contains.length;k++){
				if(contains[k]==true){
					n=1;
					break;
				}
			}
			if(n==0)
				return(true);
			else
				return(false);
		}
		public void initIterator() {
		    index = -1;
		}

		public boolean next() {
		    index++;
		    return contains[index];
		}

		public boolean hasNext() {
		    return (index + 1) < contains.length;
		}
}
