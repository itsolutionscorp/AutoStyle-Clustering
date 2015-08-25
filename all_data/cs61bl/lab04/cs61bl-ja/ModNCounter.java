public class ModNCounter {

	private int myCount;
	int a;

	public ModNCounter(int n) {
		myCount = 0;
		a = n;
	}

	public void increment() {
		myCount++;
		if (myCount == a) {
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
