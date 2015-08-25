
public class ModNCounter extends Counter {
	int N;
	public ModNCounter(int k) {
		super();
		N = k;
	}
	public void increment() {		
		if (super.value() == N)
			super.reset();
		else
			super.increment();
	}
}
