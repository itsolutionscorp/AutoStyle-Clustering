public class ModNCounter {

	private int myCount;
	int myN;

	public ModNCounter() {
		myCount = 0;
	}
	public ModNCounter (int modnum) {
		myCount = 0;
		myN = modnum;
	}

	public void increment() {
		myCount++;
		if ( myCount % myN == 0) {
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
