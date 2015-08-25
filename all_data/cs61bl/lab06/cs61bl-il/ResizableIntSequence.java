
public class ResizableIntSequence extends IntSequence{

	public ResizableIntSequence(int capacity) {
		super(capacity);
	}
		

public void add(int toBeAdded) {
	if (myCount == myValues.length){
	  int[] resize = new int[myValues.length + 10];
	  for (int i = 0; i< myValues.length; i ++){
		  resize[i] = myValues[i];
		 
	  }
	  
	  resize[myCount] = toBeAdded;
	  myCount ++;
	  myValues = resize;
	   }

	else {
	myValues[myCount] = toBeAdded;
	myCount++;    	}}

public void remove(int pos){
	
	
		  
		

 	  if (pos < 0 || pos > myCount)
	  {
	    System.err.println("Out of Bound");
	    System.exit(1);
	  }
	   
	   for (int i = pos; i < myCount-1; i++){
		   myValues[i] = myValues[i+1];
		}
	   myValues[myCount-1] = 0;	   
	   myCount--;
	   
		if (myCount <= myValues.length/2){
			int[] resize = new int[myValues.length * 9/10];
			
			  for (int i = 0; i< resize.length; i ++){
				  resize[i] = myValues[i];
				 
			  }
			  
			  myValues = resize;}
			  
	   
 }
// Insert toInsert into the sequence at position insertPos,
// shifting the later elements in the sequence over to make room
// for the new element.
// Assumptions: The array isn't full, i.e. myCount < myValues.length
// Also, insertPos is between 0 and myCount, inclusive.
public void insert(int toInsert, int insertPos) {
	if (myCount == myValues.length){
		int[] resize = new int[myValues.length + 1];
		for (int i = 0; i< myValues.length; i ++){
			  resize[i] = myValues[i];	
		
		  }
		myValues = resize;
		this.insert(toInsert, insertPos);
		
	}
	else {
	 myValues[myCount] = myValues[myCount-1]; // shift the last element to the last+1
	for (int i = myCount-1; i>insertPos;i--){
		myValues[i] = myValues[i-1];			
	}
	myValues[insertPos] = toInsert;        
    myCount++;
}}}
