
public class ModNCounter extends Counter {
	int max;
	public ModNCounter(int n) {
		max = n;
	}
	public void increment() {
		super.increment();
		if(super.value() == max) {
			super.reset();
		}
	}
	public static void main(String[] args) {
		ModNCounter modCtr = new ModNCounter(3);
		modCtr.increment();
		System.out.println(modCtr.value()); // prints 1
		modCtr.reset();
		modCtr.increment();
		System.out.println(modCtr.value()); // still prints 1
		modCtr.increment();
		modCtr.increment();
		modCtr.increment();
		modCtr.increment();
		System.out.println(modCtr.value()); // prints 1
	}
}
