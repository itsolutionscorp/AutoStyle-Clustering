public class ModNCounter {

    private int myCount;
    private int N;
    public ModNCounter (int myN ) {
        N= myN;
        myCount = 0;
    }

    public void increment ( ) {
        if (this.myCount >= N - 1) {
        	this.reset();
        } else {
        	myCount++;
    }
    }
    public void reset ( ) {
        myCount = 0;
    }
    
    public int value ( ) {
        return myCount;
    }
}
