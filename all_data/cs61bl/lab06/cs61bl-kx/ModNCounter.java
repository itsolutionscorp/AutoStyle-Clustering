public class ModNCounter extends Counter{
	private int n;
	public ModNCounter(int N){
		n = N;
		
	}
	public void increment(){
		super.increment();
		if(value()==n-1){
			reset();
		}
	}
	public static void main(String[] args){
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