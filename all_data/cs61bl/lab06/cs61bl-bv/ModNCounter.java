
public class ModNCounter extends Counter {

	private int my_number;
	public ModNCounter(int number) {
		super();
		my_number = number;
		
	}
	
	public void increment() {
		if (value() != my_number) {
			super.increment();
		} else {
			reset();
		}
	}
	
	public static void main(String[] args) {
		ModNCounter modCtr = new ModNCounter(3);
		modCtr.increment();
		System.out.println(modCtr.value()); // prints 1
		modCtr.reset();
		modCtr.increment();
		System.out.println(modCtr.value()); // still prints 1
		
		ModNCounter modCnt = new ModNCounter(3);
		modCtr.increment();
		modCtr.increment();
		modCtr.increment();
		modCtr.increment();
		System.out.println(modCtr.value()); // prints 1
	}
}
