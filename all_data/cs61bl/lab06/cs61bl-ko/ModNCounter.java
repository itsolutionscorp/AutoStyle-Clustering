
public class ModNCounter extends Counter {

	/**
	 * @param args
	 */
	private int limit;
	
	public ModNCounter(int max) {
		limit = max;
	}
	
	public void increment() {
		if (super.value() == limit - 1) {
			this.reset();
		}
		else {
			super.increment();
		}
		

	}

}
