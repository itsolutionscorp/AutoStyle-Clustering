public class ResizableIntSequence extends IntSequence {

	private int cap;
	
	public ResizableIntSequence(int capacity) {
		super(capacity);
		cap = capacity;
	}

	public int cap() {
		return cap;
	}
	
	// Increases the array size.
	public void resize() {
		cap = cap * 2;
		int[] storage = this.myValues;
    	this.myValues = new int[cap];
    	for (int i = 0; i < myCount; i++) {
    		this.myValues[i] = storage[i];
    	}
	}
	
	// Cuts the array size.
	public void downsize() {
		cap = cap / 2;
		int[] storage = this.myValues;
    	this.myValues = new int[cap];
    	for (int i = 0; i < myCount; i++) {
    		this.myValues[i] = storage[i];
    	}
	}
	
	@Override
	public void add(int toBeAdded) {
        if (myCount == cap) {
        	resize();
        }
		super.add(toBeAdded);
	}
	
	@Override
	public void insert(int toInsert, int pos) {
		if (myCount == cap) {
			resize();
		}
		super.insert(toInsert, pos);
	}
	
	@Override
	public void remove(int pos) {
		if (myCount == cap / 2) {
			downsize();
		}
		super.remove(pos);
	}
}
