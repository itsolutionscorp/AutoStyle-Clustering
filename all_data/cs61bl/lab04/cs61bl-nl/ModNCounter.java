public class ModNCounter {

	private int myCount;
	private int myN;
	

	public ModNCounter(int myN) {
		myCount = 0;
		this.myN=myN;
		
	}

	public void increment() {
		myCount++;
		if(myCount==myN)myCount=0;
		
	}

	public void reset() {
		myCount = 0;
	}

	public int value() {
		return myCount;
	}

}
