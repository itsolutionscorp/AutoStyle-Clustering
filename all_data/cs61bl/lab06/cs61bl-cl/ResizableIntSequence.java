package lab06;

      public class ResizableIntSequence extends intSequence {
    	  
    	 
		public ResizableIntSequence(int capacity) {
			super(capacity);
			// TODO Auto-generated constructor stub
		}
		public void RessizableIntSequence (int [] myValues, int myCount) {
    		  
    	  }
	     public static void main (String [] args){
	    	 ResizableIntSequence P= new ResizableIntSequence(myCount);
	    	 P.add();
	    	 
	}
	     @Override
	     public int add (){
	    	 if (this.myCount == this.myValues.length) {
	 			System.err.println("There is no space!");
	 			System.exit(1);
	 		}
	 		
			int toBeAdded = 0;
			this.myValues[this.myCount] = toBeAdded;
	 		return this.myCount = this.myCount + 1;
	     }
	     
	     @Override
	     public int insert(){
	    	 for (int k = insert() + 1; k <= myCount; k++) {
	 			myValues[k] = myValues[k-1];
	 		}
	 		myValues[insert()] = insert();
	 		return myCount++;
	 	}
	 	public boolean isEmpty() {
	 		return true;
  
	     }
}
