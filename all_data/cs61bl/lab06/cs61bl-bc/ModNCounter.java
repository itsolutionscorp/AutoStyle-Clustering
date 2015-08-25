public class ModNCounter extends Counter {
	int N;
	public ModNCounter(int N) {
		super();
		this.N = N;
	}
	public void increment() {
		super.increment();
		if (value() == N) reset();
	}
}
