
public class ModNCounter extends Counter {
	private int myN;
	
	public ModNCounter(int n) {
		myN = n;
	}
	
	public void increment() {
		if (value() < myN - 1) {
			super.increment();
		} else {
			reset();
		}
	}

}
