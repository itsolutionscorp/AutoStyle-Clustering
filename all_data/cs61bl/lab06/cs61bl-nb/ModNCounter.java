
public class ModNCounter extends Counter{

	public ModNCounter(int n) {
		myN = n;// TODO Auto-generated constructor stub
	}
	
	public void increment(){
		super.increment();
		if(super.value()== myN){
			super.reset();
		}
	}
	
	int myN = 0 ;

}
