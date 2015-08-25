
public class ModNCounter extends Counter{

	public ModNCounter(int myN) {
		N = myN;
	}
	
	public void increment() {
		super.increment();
		if (value() > N) {
			reset();
		
		}
		
	
	}
	
	public static void main (String[] args) {
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
	
	private int N;
}
