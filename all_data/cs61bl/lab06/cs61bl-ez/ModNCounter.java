public class ModNCounter extends Counter {

	int myN;

	public ModNCounter(int N) {
		super();
		myN = N;
	}

	@Override
	public void increment() {
		if (this.value() == myN - 1) {
			this.reset();
		} else {
			setMyCount(getMyCount() + 1);
		}
	}
}
