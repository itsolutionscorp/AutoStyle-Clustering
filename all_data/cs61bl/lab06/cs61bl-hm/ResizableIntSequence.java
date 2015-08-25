
public class ResizableIntSequence extends IntSequence{

	public ResizableIntSequence(int capacity) {
		super(capacity);
	}
	
	public void add(int toBeAdded) {
		if(myCount < myValues.length){
			super.add(toBeAdded);
		}else{
			int[] temp = new int[myValues.length+10];
			for(int i=0;i<myValues.length;i++)
				temp[i]=myValues[i];
			temp[myValues.length] = toBeAdded;
			myValues = temp;
			myCount++;
		}
	}
	
	public void insert(int toInsert, int insertPos) {
		if(insertPos < myCount){
			super.insert(toInsert, insertPos);
		}else if (insertPos >= myCount && insertPos<myValues.length){
			myValues[insertPos] = toInsert;
			myCount = insertPos + 1;
		}else if (insertPos >= myValues.length) {
			int[] temp = new int[insertPos+10];
			for(int i=0;i<myValues.length;i++)
				temp[i]=myValues[i];
			temp[insertPos] = toInsert;
			myValues = temp;
			myCount = insertPos + 1;
		}
	}
	public void remove(int pos){
		super.remove(pos);
		if(myValues.length/2 >= myCount){
			int[] temp = new int[myValues.length*3/5];
			for(int i=0; i<= myCount; i++) {
				temp[i] = myValues[i];
			}
			myValues = temp;
		}
	}
	
	public static void main(String[] args) {
		
	}

}
