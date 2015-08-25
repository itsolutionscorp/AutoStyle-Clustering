import java.util.Iterator;

public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	int nextIndex;
	
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		contains = new boolean[maxElement];
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		if ((k >= 0) && (k < contains.length)){
			contains[k] = true;
		}
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
		if (k >= 0 && k < contains.length){
			contains[k] = false;
		}
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		boolean check = true;
		if (contains[k] == true){
			return check;
		}
		else{
			check = false;
			return check;
		}
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		boolean check = true;
		for (int x = 0; x < contains.length; x++){
			if (contains[x] == true){
				check = false;
				break;
			}
		}
		return check;
	}
	
	public void initIterator(){
		nextIndex = 0;
	}
	public boolean hasNext(){
		return nextIndex < contains.length;
	}
	public int next(){
		if(hasNext()){
			if(!member(nextIndex)){
				nextIndex++;
				next();
			}
			else{
				nextIndex++;
				return nextIndex-1;
			}
			
		}
		return 356476987;
	}
	
	public static void main(String []args){
		Set s = new Set(5);
		s.insert(2);
		s.insert(0);
		s.initIterator();
		int m = s.next();
		m = s.next();
		m = s.next();
		System.out.println(m);
	}

	
}
