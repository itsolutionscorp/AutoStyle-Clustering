
public class ModNCounter extends Counter {
	int myN;
	public ModNCounter(int N){
		   super();
			myN = N;
			
			
		}
	
	public void increment(){
	if (value() < myN){
        super.increment();}
	else{
		reset();
		super.increment();
	}
    }
/*	public void increment(){
			if (super.value() < myN){
		        super.increment();}
			else{
				reset();
				super.increment();
			}
		    }*/
	
	public static void main(String[] args){
		ModNCounter modCtr = new ModNCounter(3);
		modCtr.increment();
		System.out.println(modCtr.value());
		modCtr.increment();
		System.out.println(modCtr.value());
		modCtr.increment();
		System.out.println(modCtr.value());
		modCtr.increment();
		System.out.println(modCtr.value()); // prints 1
	}


}

