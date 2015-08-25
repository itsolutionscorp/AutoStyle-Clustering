public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 

	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	private int count;
	private int maxElement;
	private int numElement;
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		contains = new boolean [maxElement];
		for (int i = 0; i < maxElement; i++){
			contains[i]  = false;
		}
		this.maxElement=maxElement;
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
		if (contains[k] == true){
			return true;
		}else{
			return false;
		}

	}

	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		boolean returnValue = true;
		for (int i = 0; i < contains.length; i++){
			if (contains[i] == true){
				returnValue = false;

				break;
			}

		}
		return returnValue;

	}
	
	public void initIterator(){
		count= 0;
		for (int i=0;i<maxElement-1; i++){
		   if (contains[i]==true){
			   numElement++;
		   }	   
		}
	}
	public boolean hasNext(){
		if(count<numElement){
			return true;
		}else{
			return false;
		}
		
	}
	public int next(){
		int ret = 0;
		int cut=0;
		for(int i=0; i<maxElement;i++){
			if (contains[i]==true){
				if (cut==count){
					ret=i;
					break;
				}else 
					cut++;
			}
			
		}
		count++;
		return ret;
	}
		public static void main (String [] args){
			Set s = new Set (10); 
			s.insert(3);
			s.insert(5);
			s.insert(8);
			s.insert(0);
			
			for (int i=0; i<10; i++){
				System.out.println("it contains" +i+"? ..." +s.contains[i]);
			}
			s.initIterator();
			while (s.hasNext()){
				System.out.println(""+ s.next());
			}
		}

}
