public class ModNCounter {

	private int myCount;
	
	public ModNCounter(int N) {
		myCount = 0;
	}

	public void increment() {
		myCount++;
	}

	public void reset() {
		myCount = 0;
	}

	public int value() {
		return myCount;
	}

}
