public class ModNCounter extends Counter {
	private int myN;

	public ModNCounter(int n) {
		myN = n;
	}

	public void increment() {
		super.increment();
		if (super.value() == myN) {
			super.reset();
		}
	}
}
