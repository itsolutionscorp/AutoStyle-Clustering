public class ModNCounter extends Counter {
	
	private int myN;

	public ModNCounter(int n) {
		super();
		myN = n;
	}

	public void increment() {
		if (super.value() < myN - 1) {
			super.increment();
		} else {
			this.reset();		
		}
	}
}

