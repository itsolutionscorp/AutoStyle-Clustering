
public class ModNCounter extends Counter {
	
	int modN;

	/**
	 * @param args
	 */
	public ModNCounter(int N){
		this.modN = N;
	}
	
	public void increment(){
		super.increment();
		if (super.value() == this.modN){
			super.reset();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ModNCounter modCounter = new ModNCounter(3);
		modCounter.increment();
		modCounter.increment();
		modCounter.increment();
		modCounter.increment();
		System.out.println(modCounter.value()); // prints 1
		modCounter.increment();
		modCounter.increment();
		modCounter.increment();
		modCounter.increment();
		System.out.println(modCounter.value()); //prints 2
		
	}

}
