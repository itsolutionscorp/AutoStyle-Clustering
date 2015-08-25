import java.util.ArrayList;

public class ResizableIntSequence extends IntSequence {
	
	public ResizableIntSequence(int capacity) {
        super(capacity);
    }
	
	@Override
	public void add(int toBeAdded) {
		
		if (this.size() == myValues.length){
			int length = myValues.length;
			int[] myValues2 = new int[length*2];
			
			for(int i = 0; i < myValues.length; i++){
				myValues2[i] = myValues[i];
			}
			
			myValues = null;
			myValues = new int[length * 2];
			
			for(int i = 0; i < length; i++){
				myValues[i] = myValues2[i];
			}
			myValues2 = null;
			myValues[length] = toBeAdded;
			myCount++;
			
		} else {
			myValues[myCount] = toBeAdded;
			myCount++;
		}
	}
	
	@Override
	public void insert(int toInsert, int insertPos) {
		if (insertPos < 0) {
			return;
		}
		
		if (this.size() == myValues.length){
			int length = myValues.length;
			int[] myValues2 = new int[length*2];
			
			for(int i = 0; i < myValues.length; i++){
				myValues2[i] = myValues[i];
			}
			
			myValues = null;
			myValues = new int[length * 2];
			
			for(int i = 0; i < length; i++){
				myValues[i] = myValues2[i];
			}
			myValues2 = null;
			myValues[length] = toInsert;
			myCount++;
			return;
		}
		
		if (insertPos == (myValues.length - 1)){
			myCount++;
			myValues[insertPos] = toInsert;
		} else {
			myCount++;
			for(int i = (myValues.length - 1); i > insertPos ; i--){
				myValues[i] = myValues[insertPos]; 
			}
			myValues[insertPos] = toInsert;
		}
	}
	
	@Override
	public void remove(int pos) {
		if (pos < 0 || pos >= myValues.length) {
			return;
		}
		myCount--;
		if (pos == (myValues.length - 1)){
			myValues[myValues.length-1] = 0;
		} else {
			for(int i = pos; i < (myValues.length - 1); i++){
				myValues[i] = myValues[i+1];
			}
			myValues[myValues.length - 1] = 0;
		}
		
		
		if(this.size() <= myValues.length/2){
			int length = myValues.length;
			int[] myValues2 = new int[length/2];
			
			for(int i = 0; i < myValues2.length; i++){
				myValues2[i] = myValues[i];
			}
			
			myValues = null;
			myValues = new int[length/2];
			
			for(int i = 0; i < length/2; i++){
				myValues[i] = myValues2[i];
			}
			myValues2 = null;
		}
	}
	
	
	
}