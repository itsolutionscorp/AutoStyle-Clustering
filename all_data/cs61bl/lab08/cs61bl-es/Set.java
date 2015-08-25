import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	private int nextIndexToReturn;
	
	
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
			if (contains[i]) {
				return false;
			}
		}
		return true;
	}
	
	public void initIterator() {
		nextIndexToReturn = 0;
	}
	
	public boolean hasNext() {
		for (int i = nextIndexToReturn; i < contains.length; i++) {
			if (member(i)) {
				return true;
			}
		}
		return false;
	}
	
	public int next() {
		if (member(nextIndexToReturn)) {
			int rtnValue = nextIndexToReturn;
			nextIndexToReturn++;
			return rtnValue;
		}
		else {
			for (int i = nextIndexToReturn + 1; i < contains.length; i++) {
				if (member(i)) {
					int rtnValue = i;
					nextIndexToReturn = i + 1;
					return rtnValue;
				}
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		Set s = new Set(10);
		s.insert(0);
		s.insert(1);
		s.insert(4);
		s.insert(6);
		s.insert(7);
		s.insert(9);
		
		s.initIterator();
		// should print "0 1 4 6 7 9"
		while (s.hasNext()) {
			System.out.print(s.next() + " ");
		}
		System.out.println();
		
		s.initIterator();
		// should print "0 1 4 6 7 9"
		for (int i = 0; i < 6; i++) {
			System.out.print(s.next() + " ");
		}
	}
}
