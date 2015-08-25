public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement.

	// contains[k] is true if k is in the set, false if it isn't
	int index;
	private boolean[] contains;

	// Initialize a set of ints from 0 to maxElement-1.
	public Set(int maxElement) {
		contains = new boolean[maxElement];
	}

	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert(int k) {
		contains[k] = true;
	}

	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove(int k) {
		contains[k] = false;
	}

	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member(int k) {
		return contains[k];
	}

	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		for (int ind = 0; ind < contains.length; ind++) {
			if (contains[ind] == true) {
				return false;
			}
		}
		return true;
	}
	
	public void initInterator() {
		index = 0;
	}
	
	public boolean hasnext() {
		for (int i = index; i < contains.length; i++) {
			if (contains[i]) {
				return true;
			}
		}
		return false;
	}
	
	public int next() {
		for (int i = index; index < contains.length; i++) {
			if (contains[i]) {
				index = i+1;
				return i;
			}
		}
		return -1;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set s = new Set(4);
		s.insert(0);
		s.insert(1);
		s.hasnext();
		System.out.println(s.next());
		s.hasnext();
		System.out.println(s.next());
		s.hasnext();

	}
}
