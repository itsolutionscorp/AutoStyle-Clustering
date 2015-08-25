public class ModNCounter extends Counter {

	private int mod;
	public ModNCounter(int n) {
		mod = n;	
	}
	
	public void increment() {
		if (this.value() == mod-1) {
			this.reset();
		} else {
			super.increment();
		}
	}
}

