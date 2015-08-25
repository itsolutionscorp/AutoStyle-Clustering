
public class ModNCounter extends Counter{
	
int myValues;
	
	public ModNCounter(int n){
		myValues = n;
		
	}
	
	public void increment(){
		if(super.value() == myValues){
		    super.reset();
		}else{
			super.increment();
		}
	}
	/**
	 * @param
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ModNCounter modCtr = new ModNCounter(3);
		modCtr.increment();
		System.out.println(modCtr.value()); // prints 1
		modCtr.reset();
		System.out.println(modCtr.value());
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
