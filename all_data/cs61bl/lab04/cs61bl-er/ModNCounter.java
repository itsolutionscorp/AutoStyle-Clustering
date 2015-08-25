public class ModNCounter {

	private int myCount;
	private int N;

	public ModNCounter(int x) {
		myCount = 0;
		N = x;
	}

	public void increment() {
		if (myCount == N-1){
			reset();
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
