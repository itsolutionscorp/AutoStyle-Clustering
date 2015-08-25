

public class ResizableIntSequence extends IntSequence {            

	public ResizableIntSequence(int capacity) {
		super(capacity);              
	}
      
    @Override
    public void add(int Adding) {
    	if (myCount >= myValues.length) {
    		myCount += 1;
            int[] temp = myValues;
            myValues = new int[myCount];
            for (int i = 0; i < myValues.length; i++) {
            	if (i < temp.length) {
            		myValues[i] = temp[i];
            	} else {
            		myValues[i] = 0;
            	}
            }
            myValues[myValues.length-1] = Adding;
    	} else {
    		super.add(Adding);
    	}        
    }

        

    @Override
    public void insert(int toInsert, int insertPos) {
        if (myCount >= myValues.length) {
        	myCount += 1;
        	int[] temp = myValues;
        	myValues = new int[myCount];
        	for (int i = 0; i < myValues.length; i++) {
        		if (i == insertPos) {
        			myValues[i] = toInsert;

        		} else if (i < temp.length) {
        			myValues[i] = temp[i];
        		}
        	}
        } else {
        	super.insert(toInsert, insertPos);
        }
    }
    
    @Override
    public void remove(int pos) {
        if (myCount >= myValues.length) {
        	myCount -= 1;
        	int[] temp = myValues;
        	myValues = new int[myCount];
        	for (int i = 0; i < myValues.length; i++) {
        		if (i >= pos) myValues[i] = temp[i+1];
        		else myValues[i] = temp[i];
        	}
        } else {
        	super.remove(pos);
        }
    }  

        /**

         * @param args

         */
    public static void main(String[] args) {
    	ResizableIntSequence lala = new ResizableIntSequence(5);
    	lala.add(2);
    	lala.add(3);
    	lala.add(4);
    	lala.add(5);
    	lala.add(6);
    	lala.add(8);
    	System.out.println(lala);
    	lala.insert(7, 6);
    	System.out.println(lala);
    	lala.remove(3);
    	System.out.println(lala);
    }
}