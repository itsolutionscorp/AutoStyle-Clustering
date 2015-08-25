
public class ModNCounter extends Counter {

	int myN;
	public ModNCounter(int N)
	{
		myN = N;
	}
    public void increment() { 	
    	if(super.value() == myN-1)
    	{super.reset();} else {
    	super.increment();}
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		System.out.println(modCounter.value()); // prints 1
	}

}
