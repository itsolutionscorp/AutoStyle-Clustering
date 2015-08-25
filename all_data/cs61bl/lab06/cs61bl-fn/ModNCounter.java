
public class ModNCounter extends Counter{
	private int mod;
	public ModNCounter(int N) {
		mod = N;
	}
	@Override
	public void increment() {
		if (value() == mod) {
			reset();
		}
		super.increment();
	}
	public static void main (String [ ] args) {
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
