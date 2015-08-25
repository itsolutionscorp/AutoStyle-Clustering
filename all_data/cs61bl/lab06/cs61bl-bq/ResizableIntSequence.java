
public class ResizableIntSequence extends IntSequence {
	
	int capacity;

	public ResizableIntSequence(int capacity) {		
		super(capacity);
		this.capacity = capacity;
	}
		
	public void add(int toBeAdded) {

	if( myCount == myValues.length) {
		
		int[] TempArray = new int[myValues.length * 2];
		
		for (int i = 0; i < myValues.length; i+=1) {
			
			TempArray[i] = myValues[i];
			
		}
		
		
		myValues = TempArray;
		
	}
	
	else {
		super.add(toBeAdded); 
		} 
	}
	
	public void insert(int toBeInserted, int insertPos) {	
		if( myCount == myValues.length) {
		
			int[] TempArray = new int[myValues.length * 2];
		
			for (int i = 0; i < myValues.length; i+=1) {
			
				TempArray[i] = myValues[i];	
			}
		
		myValues = TempArray;
		
		}
	super.insert(toBeInserted, insertPos);
	}
	
	public void remove(int RemovePos) {
		
		super.remove(RemovePos);
		
		if (myCount <=  myValues.length/2) {
			
			int[] TempArray = new int[myValues.length/2];
			
			for (int i = 0; i < myValues.length; i+=1) {
			
				TempArray[i] = myValues[i];	
			
		}
		
		myValues = TempArray;

			
			
		
	}
	
	
}
}
