public class ResizeableIntSequence extends IntSequence{

	public ResizeableIntSequence(int capacity) {
		super(capacity);
	}
	
	public int[] resize(int[] values, int size) {
		int loop;
		int[] newValues = new int[size];
		if (size < values.length)
			loop = size;
		else
			loop = values.length;
		for (int i = 0; i < loop; i++) {
			newValues[i] = values[i];
		}
		return newValues;
	}

	public int capacity() {
		return this.myValues.length;
	}
	
	public void add(int toBeAdded) {
		super.add(toBeAdded);
		if (this.myCount == this.myValues.length) {
			int capacity = this.myValues.length + (this.myValues.length / 2);
			this.myValues = resize(this.myValues, capacity);
		}
	}
	
	public void insert(int toBeAdded, int pos) {
		super.insert(toBeAdded, pos);
		if (this.myCount == this.myValues.length) {
			int capacity = this.myValues.length + (this.myValues.length / 2);
			this.myValues = resize(this.myValues, capacity);
		}
	}
	
	public void remove(int pos) {
		super.remove(pos);
		if (this.myCount == this.myValues.length / 4)
			this.myValues = resize(this.myValues, this.myValues.length / 2);
	}

}
