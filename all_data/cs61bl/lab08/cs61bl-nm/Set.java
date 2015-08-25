public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	
	private int nextIndex;
	private int curIndex;
	
	
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		contains = new boolean[maxElement];
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		if (k<0 || k>=contains.length) {
			return;
		}
		contains[k] = true;
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
		if (k<0 || k>=contains.length) {
			return;
		}
		contains[k] = false;
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		if (k<0 || k>=contains.length) {
			return false;
		}
		return contains[k];
		
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		for (int i=0; i<contains.length; i++) {
			if (contains[i]) {
				return false;
			} else {
				continue;
			}
		}
		return true;
	}
	
	public void initIterator(){
		nextIndex = 0;
		curIndex =0;
	}
	public boolean hasNext(){
		for(int i = nextIndex; i < contains.length; i++){
			if(contains[i] && i != curIndex)
				return true;
		}
		return false;
	}
	public int next(){
		curIndex = nextIndex;
		for (int i=nextIndex+1; i<contains.length; i++) {
			
			if (contains[i]) {
				nextIndex = i;
				break;
			}
			
		}
		
		return curIndex;
	}
	
	public static void main(String args[]) {
		Set s = new Set(10);
		s.insert(0);
		s.insert(3);
		s.insert(5);
		s.insert(7);
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
	}
}
