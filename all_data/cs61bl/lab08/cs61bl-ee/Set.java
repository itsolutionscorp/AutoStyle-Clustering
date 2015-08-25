
public class Set {
	// Represent a set of nonnegative ints from 0 to maxElement-1
		// for some initially specified maxElement. 
		
		// contains[k] is true if k is in the set, false if it isn't
		private boolean[] contains;
		private int index;
		
		// Initialize a set of ints from 0 to maxElement-1.
		public Set (int maxElement) {
			contains = new boolean[maxElement];
			for(int k = 0; k<maxElement;k+=1){
				contains[k] =false;
			}
		
		}
		
		// precondition: 0 <= k < maxElement.
		// postcondition: k is in this set.
		public void insert (int k) {
			if(k<contains.length && k >=0){
				contains[k] = true;
			}
		}
		
		// precondition: 0 <= k < maxElement.
		// postcondition: k is not in this set.
		public void remove (int k) {
			if(k<contains.length && k >=0){
				contains[k] = false;
			}
		
		}
		
		// precondition: 0 <= k < maxElement
		// Return true if k is in this set, false otherwise.
		public boolean member (int k) {
			return contains[k];
		}
		
		// Return true if this set is empty, false otherwise.
		public boolean isEmpty() {
			boolean result = true;
			for(int k = 0; k<contains.length;k+=1){
				if(contains[k] !=false){
					result = false;
					break;
				}
			}
			return result;
			
			
		}
		public void initiIterator(){
			index = 0;
			
		}
		public int next(){
			while(contains[index] == false){
				index += 1;
			}
			int result = index;
			index += 1;
			
			return  result;
			
		}
		public boolean hasNext(){
			for(int k = index; k< contains.length; k+=1){
					if(contains[k] == true){
						return true;
					}
				}
			return false;
			
		}

}
