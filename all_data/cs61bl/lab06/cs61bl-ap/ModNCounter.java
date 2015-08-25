
public class ModNCounter extends Counter {
	
	private int myValue;
	
	/**
	 * @param args
	 */
	public ModNCounter(int x){
		super.reset();
		myValue = x;
		
	}
	public void increment(){
		if ( super.value() == myValue) this.reset();
		else super.increment();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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

}
