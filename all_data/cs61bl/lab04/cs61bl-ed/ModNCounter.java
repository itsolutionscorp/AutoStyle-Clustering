public class ModNCounter {

	private int myCount;
	public int myN;

	public ModNCounter(int s) {
		myN = s;
		myCount = 0;
	}

	public void increment() {
		if(myN != myCount){
			 myCount++;}
		else{
			  
			reset();      
		 }
		 }
	

	public void reset() {
		myCount = 0;
	}

	public int value() {
		return myCount;
	}

}
