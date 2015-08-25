
public class ModNCounter extends Counter {
	private int N;
	
	public ModNCounter(int N) {
		this.N = N;
	}
	
	public void increment() {
		if (this.value() + 1 == N) {
			this.reset();
		} else {
			super.increment();
		}
	}
}
