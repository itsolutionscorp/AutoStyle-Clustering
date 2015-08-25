
public class ModNCounter extends Counter {
	
	int myN; //ask LA about using new variable
	public ModNCounter(int N) {
		myN = N;
	}
	
	public void increment() {
		if (super.value() +1 == myN){
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
		modCtr.increment();
		System.out.println(modCtr.value()); // still prints 1
		
		ModNCounter modCtr1 = new ModNCounter(3);
		modCtr1.increment();
		modCtr1.increment();
		modCtr1.increment();
		modCtr1.increment();
		System.out.println(modCtr1.value()); // prints 1
	}
}
