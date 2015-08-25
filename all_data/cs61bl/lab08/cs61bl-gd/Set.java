package lab8;
public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	private int index=-100;

	
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		contains = new boolean[maxElement];
	
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		if(k>=0){if( k<contains.length){
			contains[k] = true;
		}
		}
		
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
		if(k>=0){if(k<contains.length){contains[k] = false;}
		}
		}
	
	
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		return contains[k];
	
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		boolean result = true;
		for( int i = 0; i <contains.length; i++){
			if(contains[i]){
				result = false;
				break;
			}
			
		}
		return result;
		
	}
	
	public void initIterator(){
	      index = 0;
	}
	public boolean hasNext(){
		if(index<0){throw new IllegalStateException();}
		else{
	    int copy = index;
		
		boolean found = false;
		while(!found&&copy<contains.length){
			
			if(member(copy)){
				found = true;
			}else{copy++;}
			
			
		}
		return found;}
		}
		

	public int next(){
		if(hasNext()){
			index++;
		while(!member(index)&&index<contains.length){index++;
			
		
	}
		return index;}
		else{throw new IllegalStateException("out of numbers");}
	}
		
	}


