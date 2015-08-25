
public class ModNCounter extends Counter {
	
	int k;
	public ModNCounter (int n) {
		super();
		k = n;
	}
	
	public void increment () {
		if (super.value() == k-1) {
			super.reset();
		} else {
			super.increment();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		ModNCounter modCtr = new ModNCounter(3);
		modCtr.increment();
		modCtr.increment();
		modCtr.increment();
		modCtr.increment();
		System.out.println(modCtr.value()); // prints 1

	}

}
