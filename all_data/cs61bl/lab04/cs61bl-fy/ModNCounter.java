public class ModNCounter {

	private int myCount;
	private int myN;

	public ModNCounter(int n) {
		myCount = 0;
		myN = n-1;
	}

	public void increment() {
		if (myCount < myN){
			myCount++;
		} else {
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
