public class ModNCounter {

	private int myCount;
	private int certainValue;

	public ModNCounter() {
		myCount = 0;
	}
	
	public ModNCounter(int N) {
		myCount = 0;
		certainValue = N;
	}

	public void increment() {
		myCount++;
		if (myCount == certainValue) {
			myCount = 0;
		}
	}

	public void reset() {
		myCount = 0;
	}

	public int value() {
		return myCount;
	}
	
	public int certainValue() {
		return certainValue;
	}

}
