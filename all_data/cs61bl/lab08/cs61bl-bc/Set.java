
public class Set {
	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	int nextIndexToReturn;
	int next;
	
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		this.contains = new boolean[maxElement];
	
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		this.contains[k]= true;
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
		this.contains[k] = false;
	
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		
		return this.contains[k];
	
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		int length = contains.length;
		for (int i=0 ; i < length ; i++){
			if (contains[i] == true){
				return false;
			}
		}
		return true;
		
	}
	public void initIterator(){
		nextIndexToReturn = 0;
		
	}
	public boolean hasNext(){
		if( nextIndexToReturn <contains.length-1){
				return true;
		}
		return false;
		
	}
	public int next(){
		int counter = nextIndexToReturn;
		for (int i=counter+1 ; i <contains.length ; i++){
			if (contains[i]){
				//this.next = counter;
				nextIndexToReturn = i;
				break;
			}
			nextIndexToReturn = contains.length+1;	
		}
		return nextIndexToReturn;
		
	}
	
	public static void main(String[] a){
		Set n = new Set(6);
		n.insert(1);
		n.insert(2);
		n.initIterator();
		System.out.println(n.hasNext()); //true
		System.out.println(n.hasNext()); //true
		System.out.println(n.next()); //0
		System.out.println(n.next()); //2
		System.out.println(n.hasNext());

		
		
	}

}
