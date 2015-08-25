public class ModNCounter {

    private int myCount;
    int myN;

    public ModNCounter (int n) {
    	myN = n;
        myCount = 0;
    }

    public void increment ( ) {
    	if (myCount < this.myN - 1){
    		myCount++;
    	}
    	else {
    		this.reset();
    	}
    }

    public void reset ( ) {
        myCount = 0;
    }
    
    public int value ( ) {
        return myCount;
    }
}
