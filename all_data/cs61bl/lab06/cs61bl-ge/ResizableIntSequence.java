
public class ResizableIntSequence extends IntSequence {
    public ResizableIntSequence(int capacity) {
		super(capacity);
		// TODO Auto-generated constructor stub
	}
    @Override
    public void add(int toBeAdded){
    	int[] copy;
    	copy = myValues;
    	if(myCount == myValues.length){
    		myValues= new int[myCount+1];
    		myValues[0]= toBeAdded;
    		myCount++;
    		for(int i=0;i<copy.length;i++){
    			myValues[i+1]= copy[i];
    		}
    		}
    		else{
    			for (int i = myValues.length-1; i>0;i--){
    	    		myValues[i]= myValues[i-1];
    	    	}
    	    	myValues[0]= toBeAdded;
    	    	if (myCount<myValues.length)
    	    	{myCount++;}
    		}
    	}
    	
    @Override
    public void insert(int toInsert, int insertPos){ 
   
    if(myCount==myValues.length){
        	int[] copy= myValues;
        	myValues= new int[myCount+1];
        	myCount+=1;
        	for(int i=0;i<insertPos;i++)
        	{myValues[i]= copy[i];}
        	myValues[insertPos]=toInsert;
        	for(int k= myCount-1;k>insertPos;k--){
        		myValues[k]=copy[k-1];
        	}
        	}
    else{super.insert(toInsert, insertPos);
        }
    }
    @Override
    public void remove(int pos){
    	if(myCount == myValues.length){
    		myCount--;
    		int[] copy = myValues;
    		myValues=new int[copy.length-1];
    		for(int i=0;i<pos;i++)
    		{myValues[i]=copy[i];}
    		for(int k=pos; k<myValues.length;k++){
    			myValues[k]=copy[k+1];
    		}
    	}else{
    		//normal way
    		for (int i =pos;i < myCount;i++){if (i == myCount-1)
    	    {myValues[i]=0;}else{
    			
    			myValues[i]= myValues[i+1];} 
    				
    			}
    			myCount--;
    	}
    }
	
    
	

}
