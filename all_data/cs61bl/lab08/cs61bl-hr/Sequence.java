public class Sequence <T>  {


    private Object[] myValues ;   // sequence elements
    private int myCount;                // number of array cells used by sequence

	
	
	private class Iterator {
		
		private int iteratorIndex;
		private boolean done;
			
		public void Iterator() {
			iteratorIndex = 0;
			done = false;
		}
		
		public boolean hasNext() {
			if (done) {
				return false;
			} else if (iteratorIndex == myCount - 1) {
				done = true;
			}
			return true;
		}
		
		public Object next() {
			Object val = myValues[iteratorIndex];
			this.iteratorIndex++;
			return val;
		}
	
	}


    public Sequence(int capacity) {
        myValues = new Object[capacity];
    }


    public void add(T toBeAdded) {
    	if(myCount == myValues.length){
    		System.err.println("Index Error");
    		System.exit(1);
    	}
        myValues[myCount] = toBeAdded;
    	myCount++;
    }

   
    public void insert(T toInsert, int insertPos) {
    	if (insertPos < 0 || insertPos >myCount) {
			return;
		}
        for (int k = myCount; k > insertPos; k--) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }


    public boolean isEmpty(){
    	return myCount==0;
    }
    
    public int size(){
    	return myCount;
    }
    
    public Object elementAt(int pos){
    	if(pos > myCount){
    		System.err.println("Index Error");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    
    public String toString(){
    	String vals = "";
    	Iterator iter = new Iterator();
    	while (iter.hasNext()) {
    		Object obj = iter.next();
    		if (obj != null) {
		    	vals = vals + " " + obj;
    			}
    		} 
    	return vals.substring(1);
    }
    
    public void remove(int pos) {
		if (pos < 0 || pos >= myCount) {
			return;
		}
		for(int i = pos; i < myCount-1; i++) {
			myValues[i] = myValues[i+1];
		}
		myCount--;
	}

    public boolean contains(Object k){
    	Iterator iter = new Iterator();
    	while (iter.hasNext()) {
    		Object elm = iter.next();
    		if(elm == k){
    			return true;
    		}
    	}
    	return false;
    }
}

