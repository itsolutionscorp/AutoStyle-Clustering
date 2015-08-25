
public class ModNCounter extends Counter{
	/**
	 * author 61b1bl-en
	 */
	private int num;
    public ModNCounter(int N){
    	super();
    	this.num = N;
    	
    }
    @overide
    public void increment(){
    	super.increment();
    }
    public void reset(){
    	super.reset();
    }
    public int value(){
    	return (super.value() % num);
    }
    @overide

	public static void main(String[] args) {
		// TODO Auto-generated method stub

    	ModNCounter modCounter = new ModNCounter(3);
    	modCounter.increment();
    	modCounter.increment();
    	modCounter.increment();
    	modCounter.increment();
    	System.out.println(modCounter.value()); // prints 1
        
	}

}
