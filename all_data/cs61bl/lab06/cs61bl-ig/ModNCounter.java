
public class ModNCounter extends Counter {

	private int n;
	
	public ModNCounter(int n) {
		super();
		this.n= n;
	}
	
	@Override
	public void increment() {
		super.increment();
		//myCount++;
		if(value() % n == 0) {
			reset();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ModNCounter modCounter = new ModNCounter(3);
		modCounter.increment();
		modCounter.increment();
		modCounter.increment();
		modCounter.increment();
		System.out.println(modCounter.value()); // prints 1

	}

}
