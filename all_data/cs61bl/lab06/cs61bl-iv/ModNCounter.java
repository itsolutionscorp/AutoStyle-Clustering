
public class ModNCounter extends Counter {

	public int N;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ModNCounter modCounter1 = new ModNCounter(3);
		modCounter1.increment();
		System.out.println(modCounter1.value()); // prints 1
		modCounter1.reset();
		modCounter1.increment();
		System.out.println(modCounter1.value()); // still prints 1
		
		ModNCounter modCounter2 = new ModNCounter(3);
		modCounter2.increment();
		modCounter2.increment();
		modCounter2.increment();
		modCounter2.increment();
		System.out.println(modCounter2.value()); // prints 1

	}
	
	public ModNCounter(int n) {
		super(); 
		N = n;
	}
	
	public void increment() {
		if (super.value() < N-1) {
			super.increment();
		}
		else {
			super.reset();
		}
	}

}
