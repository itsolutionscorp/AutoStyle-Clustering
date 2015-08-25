/**
 * 
 */

/**
 * @author cs61bl-ec and cs61bl-eb
 *
 */
public class ModNCounter extends Counter{
	
	private int num;
	
	public ModNCounter(int num) {
		super(); // constructor of superclass constructor 
		this.num = num;
	}
	
	@Override
    public void increment() {
        super.increment();
        if (this.value() == num) {
        	super.reset(); // "this" or "super" 
        }
    }
    
    public static void main(String[] args) {
    	ModNCounter modCnt = new ModNCounter(3);
    	modCnt.increment();
    	modCnt.increment();
    	modCnt.increment();
    	modCnt.increment();
    	System.out.println(modCnt.value()); // prints 1
    }
}
