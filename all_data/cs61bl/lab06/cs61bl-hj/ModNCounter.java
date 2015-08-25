
public class ModNCounter extends Counter {
	
		private int  n;
		
		public ModNCounter(int x) {
			n = x;   
	    }
		
		@Override
		public void increment ( ) {
		        
	        if (this.value() == n) {
	        	this.reset();
	        }
	        super.increment();
	  
		}
		
}


