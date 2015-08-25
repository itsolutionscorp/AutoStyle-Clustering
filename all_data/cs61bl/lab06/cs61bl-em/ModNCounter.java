public class ModNCounter extends Counter {
	int myN;
	
	public ModNCounter (int N) {
		super();
		myN = N;
		
	}	
	
	@Override
	public void increment() {
		if (value() == myN - 1) {
			reset();
		}
		else {
			super.increment();
		}
	}
	
	public static void main (String[] args) {
		ModNCounter modCtr = new ModNCounter(3);
		modCtr.increment();
		System.out.println(modCtr.value()); // prints 1
		modCtr.reset();
		modCtr.increment();
		System.out.println(modCtr.value()); // still prints 1
		
		ModNCounter modCtr2 = new ModNCounter(3);
		modCtr2.increment();
		modCtr2.increment();
		modCtr2.increment();
		modCtr2.increment();
		System.out.println(modCtr2.value()); // prints 1
	}
}
