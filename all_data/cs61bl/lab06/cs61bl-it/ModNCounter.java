
public class ModNCounter extends Counter {
	int myN;
	
	public ModNCounter (int num) {     
		
		this.myN = num; 
	}
	
	public void increment() {
		if (value() < myN-1){
			super.increment();
		}else {
			reset();
		}
	}
	public static void main (String[] arg){
		ModNCounter N= new ModNCounter(4);
		System.out.println(N.value());
		N.increment();
		System.out.println(N.value());
		System.out.println(N.value());	
		N.increment();
		System.out.println(N.value());
		N.increment();
		System.out.println(N.value());
		N.increment();
		System.out.println(N.value());
		N.increment();
		System.out.println(N.value());
	}

}
