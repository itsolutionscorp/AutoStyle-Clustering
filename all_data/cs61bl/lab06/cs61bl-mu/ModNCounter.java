
public class ModNCounter extends Counter {

	public ModNCounter() {
		super();
	}
	
	public ModNCounter(int x) {
		super();
		myN = x;
	}

	private int myN = 0;
	
	 public void increment() {
	     if(value()+1 <= myN-1){   
		 super.increment();
	     }else{
	    	 super.reset();
	     }
	    }
	
	public static void main(String[] args) {
	
		
		ModNCounter modCounter = new ModNCounter(3);
		modCounter.increment();
		modCounter.increment();
		modCounter.increment();
		modCounter.increment();
		System.out.println(modCounter.value()); // prints 1
	}

}
