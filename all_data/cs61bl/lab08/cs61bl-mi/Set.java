public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	
	int nextIndextoReturn;
	int lastIndexReturned = 0;
	boolean nextElem = true;
	
	public void initIterator() {
		//nextIndextoReturn = 0;
		int i =0;
		boolean b = false;
		while (b == false) {
			b = contains[i];
			if (b == false) {
				i++;
			}
		}
		nextIndextoReturn = i;
	}
	
	public boolean hasNext() {
		if (nextIndextoReturn == contains.length - 1 && contains[nextIndextoReturn] == false) {
			return false;
		}
		else {
			for (int i = nextIndextoReturn + 1; i < contains.length; i++) {
				if (contains[i] == true) {
					return true;
				}
			}
			return nextElem;
		}
		
	}
	
	public int next() {
		//int valToReturn = nextIndextoReturn;
		lastIndexReturned = nextIndextoReturn;
		int i = nextIndextoReturn + 1;
		boolean b = false;
		while (b == false && i < contains.length) {
			if (contains[i] == true) {
				b = true;
				nextIndextoReturn = i;
			}
			i++;
		}
		if (nextIndextoReturn == lastIndexReturned) {
			nextElem = false;
		}

		return lastIndexReturned;
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
		for (int i = 0; i < contains.length; i++) {
			if (contains[i] == true) {
				return false;
			}
		}
		return true;
		
		
	}
	
//	public static void main (String[] args) {
//		Set s = new Set(10);
//		for (int i = 0; i < 10; i+= 2) {
//			s.insert(i);
//		}
//		s.insert(9);
//		s.initIterator();
//		System.out.println(s.hasNext());
//		System.out.println(s.next());
//		System.out.println(s.hasNext());
//		System.out.println(s.next());
//		System.out.println(s.hasNext());
//		System.out.println(s.next());
//		System.out.println(s.hasNext());
//		System.out.println(s.next());
//		System.out.println(s.hasNext());
//		System.out.println(s.next());
//		System.out.println(s.hasNext());
//		System.out.println(s.next());
//		System.out.println(s.hasNext());
//		System.out.println(s.next());
//		System.out.println(s.hasNext());
//		System.out.println(s.next());
//		System.out.println(s.hasNext());
//		System.out.println(s.next());
//		System.out.println(s.hasNext());
//		System.out.println(s.next());
//		System.out.println(s.hasNext());
//		System.out.println(s.next());
//		System.out.println(s.hasNext());
//		System.out.println(s.next());
//		System.out.println(s.hasNext());
//	}

}
