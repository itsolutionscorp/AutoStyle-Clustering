public class ModNCounter {

	private int myCount;
	private int myN;

	public ModNCounter(int n) {
		myCount = 0;
		myN = n;
	}

	public void increment() {
		if(myCount < (myN-1)) {
			myCount++;
		} else if (myCount == (myN-1)){
			myCount = 0;
		}
		
		
	}

	public void reset() {
		myCount = 0;
	}

	public int value() {
		return myCount;
	}

}
