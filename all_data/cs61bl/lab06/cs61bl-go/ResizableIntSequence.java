public class ResizableIntSequence extends IntSequence {
	private int maxCapacity;
	
	public ResizableIntSequence(int capacity) {
		super(capacity);
		maxCapacity = capacity;

	}

	public void resize(){
		int[] resized = new int[myValues.length*2];
		for(int i = 0; i < myValues.length; i++){
			resized[i] = this.elementAt(i);
		}
		super.myValues = resized;
	}
	
	public void downsize(){
		int[] resized = new int[myValues.length/2];
		for(int i = 0; i < this.size(); i++){
			resized[i] = this.elementAt(i);
		}
		super.myValues = resized;
	}
	
	@Override
	public void add(int toBeAdded) {
		if(this.size() == maxCapacity){
			this.resize();
		}
		
		super.add(toBeAdded);
	}

	@Override
	public void insert(int toInsert, int insertPos) {
		if(this.size() == maxCapacity){
			this.resize();
		}
		super.insert(toInsert, insertPos);
	}
	

	@Override
	public void remove(int pos){
		super.remove(pos);
		
		if (this.size() < maxCapacity/2){
			this.downsize();
		}
	}

}
