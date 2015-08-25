public class ModNCounter extends Counter {
    private int n;
    public ModNCounter(int n) {
        super();
        this.n = n;
    }

    @Override
    public void increment() {
        super.increment();
        if (value() == n)
            reset();
    }
}
