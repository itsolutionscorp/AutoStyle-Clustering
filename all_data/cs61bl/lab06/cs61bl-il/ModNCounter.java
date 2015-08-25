public class ModNCounter extends Counter{


    private int N;
    public ModNCounter (int myN ) {
      super();
      N = myN;
      
    }

    public void increment ( ) {
        if (super.value() == N - 1) {
        	super.reset();
        } else {
        	super.increment();
    }}}
    