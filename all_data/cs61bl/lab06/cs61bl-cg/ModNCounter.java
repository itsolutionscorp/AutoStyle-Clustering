public class ModNCounter extends Counter {

private int myN;

	public ModNCounter(int N) {
		super();
		myN = N;
	}

	public void increment() {
		super.increment();
		if (this.value() == this.myN){
			this.reset();
		}
	}
}
