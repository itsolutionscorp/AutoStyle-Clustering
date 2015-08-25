public class ModNCounter extends Counter {
	private int bound;

	public ModNCounter(int N) {
		bound = N;
	}

	@Override
	public void increment() {
		if (super.value() < bound) {
			super.increment();
		} else {
			this.reset();
		}	
	}

}