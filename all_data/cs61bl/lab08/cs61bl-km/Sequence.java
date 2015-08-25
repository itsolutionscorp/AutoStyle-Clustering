



public class Sequence<T> {

		private Object[] myValues;
		int myCount;
		
		public Sequence (int capacity) {
			myCount = 0;
			myValues = new Object[capacity];
		}
		
		public iterator<T> iterator () {
			return (iterator<T>) new iterator<T>();
		}
		
		 
		class iterator<T> {
			private int index;
			
			public iterator() {
				index = 0;
			}
			
			public boolean hasNext() {
				if (index < myCount) {
					return true;
				}
				return false;
			}
			
			public T next() {
				T value = (T) myValues[index];
				index ++;
				return value;
				
		}
		}
		
		public void add(Object toBeAdded) {
		  	if (myCount < myValues.length) {
	    		 myValues[myCount] =  toBeAdded;
	    		myCount ++ ;
	    	}
	    	else {
	    		System.err.println("Array has reached full capacity");
	    		System.exit(1);
	    	}
		}
		
	    public void insert(T toInsert, int insertPos) {
	    	if (insertPos < 0 || insertPos > myCount) {
				return;
			}
			else {
				if (myValues.length != 0) {
					int list_len = myValues.length - 1;
					for (int i = list_len; i > insertPos; i--) {
						myValues[i] = myValues[i-1];
					}
				}
			    myValues[insertPos] = (T) toInsert;
				if (myCount < myValues.length) {
					myCount ++;
				}
			}
	    }
	    
	    public boolean isEmpty() {
	    	if (myCount > 0) {
	    		for (int i =0 ; i < myCount; i ++){
	    			if (myValues[i] != null) {
	    				return false;
	    				}
	    			}
	    		return true;
	    		}
	    	else {
	    		return true;
	    	}
	    	}
	    
	    public int size() {
	    	return myCount;
	    }
	    
	    public T elementAt(int pos){
	    	if (pos >= 0 && pos < myCount) {
	    		return (T) myValues[pos];
	    	}
	    	else {
	    		System.err.println("This element does not exist");
	    		System.exit(1);
	    		return null;
	    	}
	    }
	    public String toString(){
	    	if (myCount>0){
	    		String return_string = myValues[0].toString();
	    			if (myCount != 1) {
	    				for (int i = 1; i < myCount; i++){
	    					return_string = return_string + " " + myValues[i].toString();
	    				}
	    		}
	    		return return_string;
	    		}
	    	else {
	    		String empty_list = "";
	    		return empty_list;
	    		}
	    }
	    
	    
	    public void remove(int pos) {
	    	if (pos < 0 || pos >= myValues.length) {
				return;
			}
			else {
				int last_index = myCount - 1;
				for(int i = pos; i < last_index; i++) {
					myValues[i] = myValues[i+1];
				}
				myValues[last_index] = null;
				myCount -- ;
			}
	    }
	    
	    public boolean contains(T k) {
	    	if (myCount != 0){
	    		for (int i =0; i< myCount;i++){
	    			if (myValues[i]==k){
	    				return true;
	    			}
	    		}
	    		return false;
	    	}
	    	else {
	    		return false;
	    	}
	    }
}
