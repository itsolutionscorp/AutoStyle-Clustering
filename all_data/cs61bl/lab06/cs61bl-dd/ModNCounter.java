public class ModNCounter extends Counter {

	int n;

	public ModNCounter(int myN) {
		n = myN;
	}

	public void increment() {
		super.increment();;
		if (value() == n) {
			reset();
		}
	}
	
	public static void main(String[] args){
		ModNCounter modCounter = new ModNCounter(3);
		modCounter.increment();
		System.out.println(modCounter.value()); // prints 1
		modCounter.reset();
		modCounter.increment();
		System.out.println(modCounter.value()); // still prints 1
	}

}
