
public class ModNCounter extends Counter{

	private int num;
	
	public ModNCounter(int value) {
		super();
		num = value;
	}
	
	public void increment() {
		super.increment();
		if (super.value() == num) {
			super.reset();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ModNCounter modCounter = new ModNCounter(3);
		modCounter.increment();
		System.out.println(modCounter.value()); // prints 1
		modCounter.reset();
		modCounter.increment();
		System.out.println(modCounter.value()); // still prints 1
		
//		ModNCounter modCounter = new ModNCounter(3);
//		modCounter.increment();
//		modCounter.increment();
//		modCounter.increment();
//		modCounter.increment();
//		System.out.println(modCounter.value()); // prints 1
	}

}
