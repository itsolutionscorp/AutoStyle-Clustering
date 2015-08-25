
public class ResizableIntSequence extends IntSequence{
	
	public ResizableIntSequence(int capacity){
		super(capacity);
	}
	
	@Override
	public void add(int toBeAdded) {
		if (myCount == myValues.length) {
			int[] c = new  int [myCount+1] ;
			for (int i = 0; i < myCount; i++){
				c[i] = this.myValues[i];
			}
			this.myValues=c;
		}
		insert(toBeAdded, myCount);
	}
	
	public void insert(int toInsert, int insertPos) {
		if (myCount == myValues.length) {
			int[] c = new  int [myCount+1] ;
			for (int i = 0; i < myCount; i++){
				c[i] = this.myValues[i];
			}
			this.myValues=c;
		}
		for (int k = insertPos + 1; k <= myCount; k++) {
			myValues[k] = myValues[k-1];
		}
		myValues[insertPos] = toInsert;
		myCount++;

	}
	
	public void remove(int pos) {
		if (myCount == myValues.length) {
			super.remove(pos);
			int[] c = new  int [myCount] ;
			for (int i = 0; i < myCount; i++){
				c[i] = this.myValues[i];
			}
			this.myValues=c;
		}else {
			super.remove(pos);
		}

	}
}
