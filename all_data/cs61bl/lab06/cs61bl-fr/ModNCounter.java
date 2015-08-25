
public class ModNCounter extends Counter {

	int myMax;
	
	public ModNCounter(int max) {
		super();
		myMax = max;
	}
	
	public void increment() {
		if (value() == myMax) {
			reset();
		}
		super.increment();
	}
}
