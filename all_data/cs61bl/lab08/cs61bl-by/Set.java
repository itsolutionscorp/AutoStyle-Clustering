public class Set {

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
		if (contains[k]){
			return true;
		} else {
			return false;
		}
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		int k = contains.length -1;
		for (int i=0;i<=k; i++){
			if (member(i)){
				return false;
			}
		}
		return true;
	}
	
	int nextValueToReturn; 
	public void initIterator(){
		//nvtr needs to start at something that makes sense, or the end
		nextValueToReturn = 0;
		while (!(member(nextValueToReturn))){
			nextValueToReturn++;
		}
	}
	
	public boolean hasNext(){
		if (isEmpty()){
			return false;
		} else if (nextValueToReturn >= contains.length){
			return false;
		} else if (member(nextValueToReturn)){
			return true;
		}
		return false;
	}
	
	public int next(){
		int value = nextValueToReturn;
		//figure out nextValueToReturn
		//if (nextValueToReturn >= contains.length){	
		nextValueToReturn++;
		while ((nextValueToReturn < contains.length)&& !(member(nextValueToReturn))){
			nextValueToReturn++;
		}	
		return value;
	}
	
	public static void main(String[] args){
		Set a = new Set(10);
		a.insert(3);
		a.insert(7);
		a.insert(8);
		a.initIterator();
		while (a.hasNext()){
			System.out.println(a.next());
		}
		a.initIterator();
		while (a.hasNext()){
			System.out.println(a.next());
		}

	}

}
