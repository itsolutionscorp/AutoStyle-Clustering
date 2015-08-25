
public class ModNCounter extends Counter {
	private int cap;
	/**
	 * @param args
	 */
	public ModNCounter(int x){
		super();
		cap =x;
	}
	public void increment (){
		int count =super.value();
		if(count >cap){
			super.reset();
		}
		super.increment();
		
	}
	public static void main (String args[]){
		ModNCounter modCounter = new ModNCounter(3);
		modCounter.increment();
		System.out.println(modCounter.value()); // prints 1
		modCounter.reset();
		modCounter.increment();
		System.out.println(modCounter.value()); // still prints 1
		modCounter.increment();
		modCounter.increment();
		modCounter.increment();
		modCounter.increment();
		System.out.println(modCounter.value()); // prints 1
	}

}
