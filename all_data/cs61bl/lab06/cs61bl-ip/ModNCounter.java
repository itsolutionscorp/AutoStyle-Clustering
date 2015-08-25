public class ModNCounter extends Counter {

	private int myN;

	public ModNCounter(int N) {
		super();
		this.myN = N;
	}
	
	public void increment() {
		super.increment();
		if (this.value() == myN) {
			this.reset();
		}
	}
	
	public int count() {
		return myN;
	}
}
	

