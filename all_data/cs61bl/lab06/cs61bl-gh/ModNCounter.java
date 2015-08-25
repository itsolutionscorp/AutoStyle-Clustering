
public class ModNCounter extends Counter{
	
	public int mymod;
	
	public ModNCounter(int mod) {
		super();
		mymod = mod;
	}
	
	@Override
	public void increment(){
		super.increment();
		if (mymod == super.value()){
			super.reset();
		}
		
	}
	
	public static void main(String[] args){
		ModNCounter test = new ModNCounter(3);
		test.increment();
		test.increment();
		test.increment();
		System.out.println(test.value());
		
	}
	

}
