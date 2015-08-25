public class ResizableIntSequence extends IntSequence{
	
	public ResizableIntSequence(int capacity){
		super(capacity);
		
	}
	public void add(int toBeAdded) {
        // YOUR CODE HERE
		myCount ++ ;
        if (myCount >= myValues.length){
        	 int[] temp = new int[myValues.length+1];
        	for (int i = 0 ; i < myValues.length ;i++){
        		temp[i] = myValues[i];
        	}
        	temp[myValues.length] = toBeAdded;
        	myValues = temp;
        }
        else{ myValues[myCount-1]=toBeAdded;}
    }
	  

	public void insert(int toInsert, int insertPos) {
		   if (myCount == myValues.length)
		   {
			   int [] temp =new int[myValues.length+1];
			   for( int k = myCount - 1;k >= insertPos ; k--)
			   {
				   temp[k+1]=myValues[k];
			   }
			   for (int k= 0; k <= insertPos;k++){
				   temp[k] = myValues[k];
			   }
			   temp[insertPos] = toInsert;
			   myCount ++ ;
			   myValues = temp;
		   }
		   else{
	        for (int k = myCount - 1; k >= insertPos ; k--) {
	            myValues[k+1] = myValues[k];
	        }  
	        myValues[insertPos] = toInsert;    
	        myCount++;}
	    }
	  public void remove(int pos){
		    int threshold = 4 ;
	    	for ( int count = pos + 1 ; count < myCount ; count++){
	    		myValues[ count - 1 ] = myValues [ count ];
	    	}
	    	myCount--;
		    if( myCount <= threshold)
		    {
		    	int temp[] =new int [threshold];
		    	for( int k =0 ;k<myCount ;k++)
		    	{
		    		temp[k] = myValues[k];
		    	}
		    	myValues = temp ;
		    }
	  
	    	
	    }

}
