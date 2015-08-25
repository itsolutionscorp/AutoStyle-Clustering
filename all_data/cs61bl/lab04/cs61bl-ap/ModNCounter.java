public class ModNCounter {
	

	private int myCount;
	private int myN;

	public int getMyCount(){
		return this.myCount;
	}
	
	public void setMyCount(int number){
		this.myCount = number;
	}
	
	public int getMyN(){
		return this.myN;
	}
	
	public void setMyN(int number){
		this.myN = number;
	}
	
	
	public ModNCounter(int N) {
		this.setMyN(N);
		this.setMyCount(0);
	}

	public void increment() {
		if (this.getMyCount() < this.getMyN() - 1){
			this.setMyCount(this.getMyCount()+1);
		}else{
			this.myCount = 0;
			}
		}

	public void reset() {
		this.myCount = 0;
	}

	public int value() {
		return this.getMyCount();
	}

}
