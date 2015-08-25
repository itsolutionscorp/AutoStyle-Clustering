
public class ModNCounter extends Counter {
	
	private int limit;
	
	public ModNCounter(int limit) {
		 this.limit = limit;
	}

    public void increment ( ) {
    	if (value() == limit){
    		reset();
    	} super.increment();
    }

	
	public static void main (String[] args){
		ModNCounter modCtr = new ModNCounter(3);
		modCtr.increment();
		System.out.println(modCtr.value()); // prints 1
		modCtr.reset();
		modCtr.increment();
		System.out.println(modCtr.value()); // still prints 1
	
		ModNCounter modCnt = new ModNCounter(3);
		modCnt.increment();
		modCnt.increment();
		modCnt.increment();
		modCnt.increment();
		System.out.println(modCnt.value()); // prints 1
	}

	
	

}
