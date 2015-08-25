
public class ModNCounter extends Counter {

	int myN;
	
	public ModNCounter(int N) {
		myN = N;
	}
	
	public void increment() {
		if (super.value() >= myN) {
			super.reset();
		} else {
			super.increment();
		}
	}
	
	public static void main(String[] args) {
		ModNCounter modCtr = new ModNCounter(3);
		modCtr.increment();
		System.out.println(modCtr.value()); // prints 1
		modCtr.reset();
		System.out.println(modCtr.value());
		modCtr.increment();
		System.out.println(modCtr.value()+" next test"); // still prints 1
		
		ModNCounter modCnt = new ModNCounter(3);
		modCnt.increment();
		System.out.println(modCnt.value());
		modCnt.increment();
		System.out.println(modCnt.value());
		modCnt.increment();
		System.out.println(modCnt.value());
		modCnt.increment();
		System.out.println(modCnt.value()); 
	}
}
