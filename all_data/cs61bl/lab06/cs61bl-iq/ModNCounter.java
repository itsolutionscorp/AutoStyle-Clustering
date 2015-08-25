
public class ModNCounter extends Counter {
	
	private int myN;
	
	public ModNCounter(int n) {
		super();
		myN = n;
	}
	
	public void increment() {
		if (super.value() + 1 == myN) {
			this.reset();
		} else {
			super.increment();
		}
	}
	
}
