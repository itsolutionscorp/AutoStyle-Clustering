
public class ModNCounter extends Counter {
	
	public int myN;
	
	public ModNCounter(int n){
		myN = n;
	}
	
	public void increment(){
		if (this.value() < this.myN - 1){
		    super.increment();
		}else{
			super.reset();
			}
		
	}

}
