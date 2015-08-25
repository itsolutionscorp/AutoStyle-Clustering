public class ModNCounter {

	private int myCount;
	public int N;
	
	public ModNCounter(int ModN) {
		myCount = 0;
		N = ModN;
	};

	public void increment() {
		if (myCount == (N - 1)) {
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
