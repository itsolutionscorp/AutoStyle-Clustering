public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	int index;
	
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		contains = new boolean[maxElement];
	
	}
	public void initIterator() {
		index = 0;
	}
	public boolean hasNext() {
		for(int i = index; i < contains.length; i ++) {
			if (contains[i]) {
				return true;
			}
		}
		return false;	
	}
	
	public int next() {
		for(int i = index; i < contains.length; i ++) {
			if (contains[i]) {
				index = i + 1;
				return i;
			}
		}
		return -1;
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		if (k < 0 || k > contains.length){
			return;
		}	
		if (!contains[k]) {
			contains[k] = true;
		}
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
		if (k < 0 || k > contains.length) {
			return;	
		}
		if (contains[k]) {
			contains[k] = false;
		}
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		if (k < 0 || k > contains.length) {
			return false;
		}	
		return contains[k];
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		for(int i = 0; i < contains.length; i++) {
			if(contains[i]){
				return false;
			}
		}
		return true;	
	}
	
	public static void main (String[] arg) {
		Set s = new Set(10);
		s.insert(1);
		s.insert(5);
		s.insert(9);

		s.initIterator();
		System.out.println(s.hasNext());
		if(s.hasNext()) {
			System.out.println(s.next());
		}
		System.out.println(s.hasNext());
		if(s.hasNext()) {
			System.out.println(s.next());
		}
		System.out.println(s.hasNext());
		if(s.hasNext()) {
			System.out.println(s.next());
		}
		System.out.println(s.hasNext());
	}

}
