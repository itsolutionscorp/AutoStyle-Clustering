public class ModNCounter extends Counter {
	
	private int myN;
	
	public ModNCounter(int N) {
		this.myN = N;
	}
	
	public void increment() {
		if (this.value() == this.myN - 1) {
			this.reset();
		} else {
			super.increment();
		}
    }
}
