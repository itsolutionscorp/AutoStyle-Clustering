
public class ModNCounter extends Counter {
	
	int mod;
	
	public ModNCounter(int n) {
		mod = n;
	}

	public void increment() {
		super.increment();
		if (super.value() == mod) {
			super.reset();
		}
	}
}
