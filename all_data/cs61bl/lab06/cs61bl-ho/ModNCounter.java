public class ModNCounter extends Counter {

	private int nMod;

	public ModNCounter() {
		super();
		nMod = 1;
	}
	
	public ModNCounter(int n) {
		super();
		nMod = n;
	}
	
	public void increment() {
		super.increment();
		if (nMod == super.value()){
			super.reset();
		}
	}
}
