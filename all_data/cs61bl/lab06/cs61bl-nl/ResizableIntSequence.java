package lab06;
import IntSequence;



public class ResizableIntSequence extends IntSequence{
	
	final int THRESHOLD = 6;
	public ResizableIntSequence(int capacity) {
		super(capacity);
	}
	@Override
	public void add(int toBeAdded) {
		if (myCount == myValues.length) {
			int [] temp= new int[myValues.length * 2];
			for(int i=0;i<myValues.length;i++){
				temp[i]=myValues[i];
			}
			myValues=temp;
							
		}
		super.add(toBeAdded);
	}
	@Override
	public void insert(int toInsert, int insertPos) {
			if (myCount == myValues.length) {
				int [] temp= new int[myValues.length * 2];
				for(int i=0;i<myValues.length;i++){
					temp[i]=myValues[i];
				}
				myValues=temp;
									
			}
			super.insert(toInsert, insertPos);
	}
	
	@Override 
	public void  remove(int index){
		if(myCount==myValues.length-THRESHOLD){
			int [] temp = new int[myCount+THRESHOLD/2];
			for(int i=0;i<myCount;i++){
				temp[i]=myValues[i];
			}
			myValues=temp;
		}
			super.remove(index);
	
		
	}
	

}

