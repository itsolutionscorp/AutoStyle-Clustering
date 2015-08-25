public class ModNCounter extends Counter{

	private int newCount = super.value() ;
	private int mod;
	public ModNCounter(int x) {
		newCount = 0;
		mod = x;
	}

	public void increment() {
		newCount++;
	}

	public void reset() {
		newCount = 0;
	}

	public int value() {
		return newCount % mod;
	}

	public static void main(String[]args) {
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
