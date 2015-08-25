public class ModNCounter {

	private int myCount;
	private int myN;

	public ModNCounter(int n) {
		myCount = 0;
		myN = n;
		
	}

	public void increment() {
		if ((myN -1) == myCount) {
			myCount = 0;
		} else {
		myCount++;
		}
	}

	public void reset() {
		myCount = 0;
	}

	public int value() {
		return myCount;
	}

}
