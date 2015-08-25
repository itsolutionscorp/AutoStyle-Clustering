public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	private int index;
	
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		contains = new boolean[maxElement];
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
		boolean empty = true;
		for (int count = 0; count < contains.length; count++) {
			if (contains[count]) {
				empty = false;
				break;
			}
		}
		return empty;
	}
	
	public void initIterator() {
		index = 0;
	}

	public boolean hasNext() {
		return (index < contains.length); 
	}
	
	public int next() {
		int toReturn = index;
		if (this.hasNext()) {
			index++;
		} 
		return toReturn;
	}
	
	public static void main(String[] args) {
		Set s = new Set(5);
		s.initIterator();
		System.out.println(s.hasNext());
		System.out.println(s.next());
		System.out.println(s.hasNext());
		System.out.println(s.next());
		System.out.println(s.hasNext());
		System.out.println(s.next());
		System.out.println(s.hasNext());
		System.out.println(s.next());
		System.out.println(s.hasNext());
		System.out.println(s.next());
		System.out.println(s.hasNext()); // should return false
	}
}