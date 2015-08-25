public class ModNCounter extends Counter {

	private int myN;
	
	public ModNCounter(int val) {
		myN = val;
	}
	
	public void increment() {
		super.increment();
		if (value() == myN) {
			reset();
		}
	}
}
