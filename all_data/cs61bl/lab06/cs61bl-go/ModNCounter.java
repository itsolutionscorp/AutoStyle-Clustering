public class ModNCounter extends Counter {
	private int myN;

	public ModNCounter(int N) {
		myN = N;
	}

	@Override
	public void increment() {
		if (this.value() < myN - 1) {
			super.increment();
		} else {
			this.reset();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Test1");
		ModNCounter modCtr = new ModNCounter(3);
		modCtr.increment();
		System.out.println(modCtr.value()); // prints 1
		modCtr.reset();
		modCtr.increment();
		System.out.println(modCtr.value()); // still prints 1
		
		System.out.println("Test2");
		ModNCounter modCnt = new ModNCounter(3);
		modCnt.increment();
		modCnt.increment();
		modCnt.increment();
		modCnt.increment();
		System.out.println(modCnt.value()); // prints 1
		
		
	}

}
