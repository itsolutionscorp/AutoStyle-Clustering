
public class ModNCounter extends Counter{
	private int mod;
	
	public ModNCounter(int value){
		mod = value;
	}

	public int value(){
		int mycount = super.value();
		return mycount % mod;
	}
	 
	
	public static void main(String[] args){
//		ModNCounter modCounter = new ModNCounter(3);
//		modCounter.increment();
//		System.out.println(modCounter.value()); // prints 1
//		modCounter.reset();
//		modCounter.increment();
//		System.out.println(modCounter.value()); // still prints 1
	
		ModNCounter modCounter = new ModNCounter(3);
		modCounter.increment();
		modCounter.increment();
		modCounter.increment();
		modCounter.increment();
		System.out.println(modCounter.value()); // prints 1
	}

}