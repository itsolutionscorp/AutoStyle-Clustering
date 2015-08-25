public class ResizableIntSequence extends IntSequence {
	protected int[] array;
	
	
	public ResizableIntSequence(int capacity){
		super (capacity);
	}
	
	@Override 
	public void insert(int Insert, int Pos){
		if(Pos > myCount || Pos < 0){
			System.err.println("The position isn't valid");
    		System.exit(1);
		}else{
			if(myCount == myValues.length){
				array = new int [myValues.length];
			for (int i = 0; i < myValues.length; i++){
				array[i] = myValues[i];
				}
			myValues = array;
			}
			for(int i= myCount; i >= Pos+1; i--)
				myValues[i] = myValues[i - 1]; 
		}
		myCount++;
		myValues[Pos] = Insert;
	}
	
	@Override
	public void add(int toAdd){
		if (myValues.length ==  myCount) {
			array = new int [myValues.length+1];
			for (int j = 0; j < myValues.length; j++ ){
				array[j] = myValues[j];
				} 
			myValues = array;
			}
		myValues[myCount] = toAdd;
		myCount++;	
	}
}