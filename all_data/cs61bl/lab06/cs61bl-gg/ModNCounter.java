
public class ModNCounter extends Counter {

	private int myN;
	
	public ModNCounter(int n){
		super();
		this.myN = n;
	}
	
	@Override
	public void increment(){
		if (myN - this.value() == 1)
			reset();
		else
			super.increment();
	}
	
	public static void main(String[] args){
		ModNCounter modCounter = new ModNCounter(3);
		modCounter.increment();
		System.out.println(modCounter.value()); // prints 1
		modCounter.reset();
		modCounter.increment();
		System.out.println(modCounter.value()); // still prints 1
		
		ModNCounter modCounter2 = new ModNCounter(3);
		modCounter2.increment();
		modCounter2.increment();
		modCounter2.increment();
		modCounter2.increment();
		System.out.println(modCounter2.value()); // prints 1
	}
}
