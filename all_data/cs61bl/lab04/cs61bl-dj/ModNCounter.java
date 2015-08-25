public class ModNCounter {

	private int myCount;
	int goal;

	public ModNCounter(int x) {
		myCount = 0;
		goal = x;
	}

	public void increment() {
		if (myCount == goal-1) {
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
