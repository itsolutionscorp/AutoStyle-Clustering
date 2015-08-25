public class ModNCounter extends Counter {
	int myN;

	public ModNCounter(int N) {
		super();
		myN = N;
	}

	public void increment() {
		super.increment();
		if ( super.value() >= myN) {
			super.reset();
		}
	}
}
