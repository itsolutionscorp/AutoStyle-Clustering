public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	private int nextIndex;
	
	public void initIterator() {
		nextIndex = 0;
	}
	
	public boolean hasNext() {
		for (int i = nextIndex; i < contains.length; i ++) {
			if (contains[i]) {
				return true;
			}
		}
		return false;
	}
	
	public int next() {
		if (hasNext()) {
		for (int i = nextIndex; i < contains.length; i ++) {
			if (contains[i]) {
				nextIndex = i + 1;
				return i;
			}
		}
		}
		nextIndex = 0;
		return next();
	}
	
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		contains = new boolean[maxElement];
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		if (0 <= k && k < contains.length) {
			contains[k] = true;
		}
		return;
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
		if (0 <= k && k < contains.length) {
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
		for (boolean elem: contains) {
			if (elem) {
				return false;
			}
		}
		return true;
	}
	
	public String toString() {
		String s = "";
		for (int i = 0; i < contains.length; i ++) {
			if (contains[i]) {
				s += " " + i;
			}
		}
		return s;
	}
	

public static void main (String[] args) {
	/*TESTS:
	Set s = new Set(8);
	s.insert(0);
	s.insert(4);
	s.insert(5);
	s.insert(7);
	System.out.println("My set is: " + s.toString());
	System.out.println();
	s.initIterator();
	System.out.println(s.next());
	System.out.println(s.next());
	System.out.println(s.next());
	System.out.println(s.next());
	System.out.println(s.next());
	System.out.println(s.next());
	System.out.println(s.next());
	System.out.println(s.next());*/
	
}

}
