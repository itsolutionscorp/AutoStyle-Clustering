public class ModNCounter extends Counter {

	private int myN;

    public ModNCounter(int N) {
        myN = N;
    }
    
	public int value() {
		return super.value()%myN;
		}
}