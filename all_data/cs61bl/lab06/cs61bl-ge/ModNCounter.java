
public class ModNCounter extends Counter{
	public int limit;
	public ModNCounter(int max){
		limit =max;
		
	}
	public void increment(){
		super.increment();
		if(this.value()==limit){
			reset();
		}
		}
	public static void main(String[] args)
	{ModNCounter modCounter = new ModNCounter(3);
	modCounter.increment();
	
	System.out.println(modCounter.value()); // prints 1
	modCounter.reset();
	modCounter.increment();
	System.out.println(modCounter.value()); // still prints 1
	modCounter = new ModNCounter(3);
		modCounter.increment();
		modCounter.increment();
		modCounter.increment();
		modCounter.increment();
		System.out.println(modCounter.value()); // prints 1
	}

}
