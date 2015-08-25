
public class ModNCounter extends Counter {
	
	int N;
	
	public ModNCounter(int n) {
		super();
		N = n;
	}
	
	public void increment() {
		super.increment();
		if (super.value() == N) {
			super.reset();
		}
	}
	public static void main(String[] args) {
		ModNCounter modCounter = new ModNCounter(3);
		modCounter.increment();
		System.out.println(modCounter.value()); // prints 1
		modCounter.reset();
		modCounter.increment();
		System.out.println(modCounter.value()); // still prints 1
		modCounter = new ModNCounter(3);
		modCounter.increment();
		modCounter.increment();
		modCounter.increment();
		modCounter.increment();
		System.out.println(modCounter.value()); // prints 1
	}
}
