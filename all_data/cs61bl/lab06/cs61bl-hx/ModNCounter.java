
public class ModNCounter extends Counter {
	int a;
	
	public ModNCounter(int newInt){
		a = newInt;
	}
	

	public void increment(){
		if (super.value() == a){
			reset();
		}
		super.increment();
	}
	
	public static void main(String[] args){
	ModNCounter modCounter = new ModNCounter(3);
	modCounter.increment();
	modCounter.increment();
	modCounter.increment();
	modCounter.increment();
	System.out.println(modCounter.value()); // prints 1
    }
}