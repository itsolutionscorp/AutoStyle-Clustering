
public class Set {
	// contains[k] is true if k is in the set, false if it isn't
		private boolean[] contains;
		private int iteratorIndex;
		private int maxElement;
		
		// Initialize a set of ints from 0 to maxElement-1.
		public Set (int maxElement) {
			contains = new boolean[maxElement];
			this.maxElement = maxElement;
		}
		
		// precondition: 0 <= k < maxElement.
		// postcondition: k is in this set.
		public void insert (int k) {
			contains[k] = true;
		}
		
		// precondition: 0 <= k < maxElement.
		// postcondition: k is not in this set.
		public void remove (int k) {
			contains[k] = false;
		}
		
		// precondition: 0 <= k < maxElement
		// Return true if k is in this set, false otherwise.
		public boolean member (int k) {
			return contains[k];
		}
		
		// Return true if this set is empty, false otherwise.
		public boolean isEmpty() {
			for(int i = 0; i < contains.length; i++) {
				if (contains[i]) {
					return false;
				}
			}
			return true;
		}
		
		public void initIterator() {
			iteratorIndex = 0;
		}
		
		public int next() {
			while (contains[iteratorIndex] == false) {
				iteratorIndex++;
			}
			int nextValue = iteratorIndex;
			iteratorIndex++;
			return nextValue;
		}
		
		public boolean hasNext() {
			if (iteratorIndex == maxElement) {
				return false;
			} else if (this.isEmpty()) {
				return false;
			} else {
				for(int i = iteratorIndex; i < maxElement; i++) {
					if (contains[i]) {
						return true;
					}
				}
				return false;
			}
		}
}
