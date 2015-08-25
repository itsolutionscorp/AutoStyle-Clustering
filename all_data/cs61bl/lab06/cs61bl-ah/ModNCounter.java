public class ModNCounter extends Counter {

	private int myInt;
	
	public ModNCounter(int N) {
		super();
		myInt = N;
	}

	public void increment() {
		if (super.value() < (myInt - 1)) {
			super.increment();
		}
		else {
			reset();
		}
	}
	
	public static void main(String[] args){
		ModNCounter modCtr = new ModNCounter(3);
		modCtr.increment();
		System.out.println(modCtr.value()); // prints 1
		modCtr.reset();
		modCtr.increment();
		System.out.println(modCtr.value()); // still prints 1
		
		modCtr = new ModNCounter(3);
		modCtr.increment();
		modCtr.increment();
		modCtr.increment();
		modCtr.increment();
		System.out.println(modCtr.value()); // prints 1
		}
}
