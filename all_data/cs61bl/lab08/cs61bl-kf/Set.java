public class Set {
	
	
	private int index;
	private int length;
	private int number;

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	
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
		for (int i = 0; i < contains.length; i++) {
			if (contains[i] == true) {
				return false;
			}						
		}
		return true;
	}
	
	public void initIterator() {
		index = -1;
		for (int i = 0; i < contains.length; i++) {
			if (contains[i]) {
				length++;
			}
		}
	}
	
	public int next() {
		index ++;
		number ++;
		while (!contains[index]) {
			index ++;
		}
		return index;
	}
	
	public boolean hasNext() {
		return number < length;
	}
	
//	public static void main (String[] args) {
//		Set test = new Set(10);
//		test.insert(3);
//		test.insert(4);
//		test.insert(9);
//		test.initIterator();
//
//		System.out.println(test.hasNext());
//		System.out.println(test.next());
//
//		System.out.println(test.hasNext());
//		System.out.println(test.next());
//		System.out.println(test.hasNext());
//		System.out.println(test.next());
//		System.out.println(test.hasNext());
//	}

}
