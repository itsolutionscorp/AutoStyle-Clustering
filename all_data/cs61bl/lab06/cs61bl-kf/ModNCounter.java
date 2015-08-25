public class ModNCounter extends Counter {

	
	private int myN;

	public ModNCounter(int N) {
		myN = N;
		
	}

	public void increment() {
		if (this.value() + 1 == myN) {
			reset();
		} else {
			super.increment();
		}
	}	
}
