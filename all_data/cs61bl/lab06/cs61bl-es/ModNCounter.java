public class ModNCounter extends Counter{

	private int myN;

	public ModNCounter(int N) {
		super();
		myN = N;
	}

	public void increment() {
		if (value() == myN-1) {
			reset();
		}
		else {
			super.increment();
		}
	}
}
