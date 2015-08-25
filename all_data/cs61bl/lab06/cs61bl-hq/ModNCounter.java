public class ModNCounter extends Counter {
	int myN;
	
	public ModNCounter(int count) {
		myN = count; 
	}
	
	public void increment() {
		super.increment();
		if (super.value() == this.myN) {
			super.reset();
		}
	}
	
	public static void main(String[] args) {
		ModNCounter modCounter = new ModNCounter(3); // standard counter test
		modCounter.increment();
		System.out.println(modCounter.value()); // prints 1
		modCounter.reset();
		modCounter.increment();
		System.out.println(modCounter.value()); // still prints 1
		System.out.println();
		
		ModNCounter modCounter2 = new ModNCounter(3); // mod functionality test
		modCounter2.increment();
		System.out.println(modCounter2.value()); // prints 1
		modCounter2.increment();
		System.out.println(modCounter2.value()); // prints 2
		modCounter2.increment();
		System.out.println(modCounter2.value()); // prints 0
		modCounter2.increment();
		System.out.println(modCounter2.value()); // prints 1
	}
	
}
