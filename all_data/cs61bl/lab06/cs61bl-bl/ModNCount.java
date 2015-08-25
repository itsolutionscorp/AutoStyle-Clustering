public class ModNCount extends Counter{

    int n;

    public ModNCount(int n) {
        this.n = n;
    }

    public void increment() {
        if (value() < n - 1) {
            super.increment();
        } else {
            reset();
        }
    }
}
