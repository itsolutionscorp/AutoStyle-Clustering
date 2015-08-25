import static org.junit.Assert.assertEquals;


public class ResizableIntSequence extends IntSequence {
	
	
	public ResizableIntSequence(int capacity) {
		super(capacity);
		// TODO Auto-generated constructor stub
	}
	public void add(int toBeAdded) {
		if (myCount == myValues.length) {
    		int[] p = new int[myValues.length+1];
    		for (int j=0;j<p.length-1;j++){
    			p[j]=myValues[j];
    		}
    		p[p.length-1]=toBeAdded;
    		myValues = p;
    		myCount++;
    	} else{
		myValues[myCount]= toBeAdded;
    	myCount++;
    	}
	}
	public void insert(int toInsert, int insertPos) {
    	if (myCount == myValues.length) {
    		int[] p = new int[myValues.length+1];
    		for (int i = 0; i< p.length; i++) {
    			if (i<insertPos) {
    				p[i] = myValues[i];
    			}else if(i>insertPos) {
    				p[i]= myValues[i-1];
    			} else{
    				p[insertPos]=toInsert;
    			}
    		}
    		myValues = p;
    		myCount++;
    	} else{
    	    for (int k = myCount-1; k >=insertPos; k--) {
               myValues[k] = myValues[k-1];
            }
            myValues[insertPos] = toInsert;
            myCount++;
           }
        }
	
} 
