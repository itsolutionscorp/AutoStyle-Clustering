
public class ResizableIntSequence extends IntSequence {
	
	//private int threshold;
	//private int myCount;
	
	public ResizableIntSequence(int capacity){
		super (capacity);
	}
	
	public void add(int toBeAdded){
		if (myCount == myValues.length){
			int[] tempArr = new int[2*myValues.length];
			for (int k = 0; k<(myValues.length); k++){
					tempArr[k] = myValues[k];
			}
			myValues = tempArr;
		} 
		myValues[myCount] =toBeAdded;
		myCount ++;
	}
	
	public void insert(int toInsert, int pos){
		if (myCount == myValues.length){
			int[] tempArr = new int[2*myValues.length];
			for (int k = 0; k<(myValues.length); k++){
					tempArr[k] = myValues[k];
			}
			myValues = tempArr;
		}
		super.insert(toInsert, pos);
		}

	public int remove(int a){
		if ((myCount * 4) <= myValues.length){
			int[] tempArr = new int[myValues.length/2 - myValues.length%2];
			for (int k = 0; k<(myCount); k++){
					tempArr[k] = myValues[k];
			}
			myValues = tempArr;
		}
		return super.remove(a);
	}
}
