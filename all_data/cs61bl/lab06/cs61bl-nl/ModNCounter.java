
public class ModNCounter extends Counter {

	/**
	 * @param args
	 */
	int myN;
	public ModNCounter(int N) {
		myN = N;
		
	}
	
	public void increment() {
		if (value() == myN - 1) {
			 reset();
		} else {
			super.increment();
		}
	}
	
	public static void main(String[] args) {
		ModNCounter test = new ModNCounter(4);
		System.out.println(test.value());
		test.increment();
		System.out.println(test.value());
		test.increment();
		System.out.println(test.value());
		test.increment();
		System.out.println(test.value());
		test.increment();
		System.out.println(test.value());
		test.increment();
		System.out.println(test.value());
		test.increment();
		System.out.println(test.value());
		test.reset();
		System.out.println(test.value());

	}

}
