public class ModNCounter extends Counter {

    int myMax;

    public ModNCounter(int n) {
        myMax = n;
    }

    public void increment() {
        super.increment();
        if (value() == myMax) {
            reset();
        }
    }

}
