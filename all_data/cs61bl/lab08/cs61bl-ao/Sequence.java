import java.util.Iterator;


public class Sequence<T> implements Iterable<T>{

	public T[] myValues;   
    public  int myCount;
    public  int index;
   
   public Sequence(int capacity) {
    	myValues = (T[]) new Object[capacity];
    	myCount = 0;
    }
    
    public boolean isEmpty() {
    	return this.myCount == 0;
    }
    

    @Override
    public Iterator<T> iterator(){
    	return new myIterator();
    }
    
    public int size(){
    	return this.myCount;
    }
    
    public T elementAt(int pos){
    	if(pos < this.myValues.length) return this.myValues[pos];
    	else{
    		System.err.println("Please give an acceptable number");
    		System.exit(1);
    		return null;
    	}
    }
    
    public void add(T toBeAdded) {
    	if(this.myCount<this.myValues.length) { // meaning there is space
    		this.myValues[myCount] = toBeAdded;
    		myCount++;
    		return;
    	} else {
    		System.err.println("Sequence is already full");
    		System.exit(1);
    	}
    }
    
    
	public String toString() {
		String answer = "";
		for(int i = 0; i< this.myCount; i++){
			answer += this.myValues[i];
			if (i != this.myCount-1) {
				answer += " ";
			}
		}
		return answer;
	}
	
	public void insert(T toInsert, int insertPos) {
    	if (this.myCount < this.myValues.length && insertPos >= 0 && insertPos < myValues.length) {
            for (int k = myCount +1; k > insertPos; k--) {
            	this.myValues[k] = this.myValues[k-1];
            }
            this.myValues[insertPos] = toInsert;
            this.myCount++;
    	}
    	else {
    		System.err.println("You have given an invalid position or the array is full.");
    		System.exit(1);
    	}
    }
	
	 public void remove (int pos) {
			if (pos < 0 || pos >= this.myValues.length) {
				System.err.println("Give an acceptable number.");
	    		System.exit(1);
			}
			// YOUR CODE HERE
			else {
				for (; pos < this.myValues.length-1; pos++){
				this.myValues[pos] = this.myValues[pos + 1];
				}
				this.myValues[this.myCount-1] = null;
				this.myCount = this.myCount - 1;
			}
	    }
	 
	 public boolean contains(T k) {
	    	for (int i = 0; i < this.myCount; i++){
	    		if (this.myValues[i].equals(k)) return true;
	    	}
	    	return false;
	    }
	    


	private class myIterator implements Iterator<T> {
	
		 public void initIterator(){
			 index = -1;
		 }

		 public boolean hasNext(){
			 return index < myCount;
		 }
	
		 public T next(){
				 index++;
				 return myValues[index];
		 }
	 }	
	
}
