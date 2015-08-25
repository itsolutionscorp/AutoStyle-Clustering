public class ModNCounter extends Counter{
	
	private int myN;
	
	public ModNCounter(int n) {
		super();
		myN = n;
	}

	@Override
	public void increment() {
		super.increment();
		if (super.value() >= myN){
			super.reset();
		}
	}
	
	public int getN() {
		return myN;
	}
	
	public static void main (String args[]){
		ModNCounter modCtr = new ModNCounter(3);
		modCtr.increment();
		System.out.println(modCtr.value()); // prints 1
		modCtr.reset();
		modCtr.increment();
		System.out.println(modCtr.value()); // still prints 1
		ModNCounter modCnt = new ModNCounter(3);
		modCnt.increment();
		modCnt.increment();
		modCnt.increment();
		modCnt.increment();
		System.out.println(modCnt.value()); // prints 1
	}

}
