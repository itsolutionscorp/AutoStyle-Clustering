
public class ResizableIntSequence extends IntSequence {
	
	
	public ResizableIntSequence(int capacity) {
		super(capacity);
	}

	public void enlargeArray(int amount ){
		int[] newValues = new int[myValues.length + amount]; 
    	for (int i=0; i < size(); i++)
    		newValues[i] = myValues[i]; 
    	myValues = newValues;
    	} 	
	
	public void shrinkArray(int amount){
		int[] newValues = new int[myValues.length - amount]; 
    	for (int i=0; i < (size()); i++)
    		newValues[i] = myValues[i]; 
    	myValues = newValues;
    	} 	

	@Override
    public void add(int toBeAdded) {
        if (size() == myValues.length)
        	enlargeArray(1);
        super.add(toBeAdded);
    }
	  
	@Override
	public void insert(int toInsert, int insertPos) {
    	if (size() + 1 >= myValues.length)
    		enlargeArray(1);
    	super.insert(toInsert, insertPos);
    }
     
	@Override
	  public int remove(){
		int removed = super.remove();
		if (size() < myValues.length)
    		shrinkArray(myValues.length - size());
    	return removed;
	}
     
    public void main (String[] args){	
    		ResizableIntSequence mysequence = new ResizableIntSequence(5);
    		mysequence.add(3);
    		mysequence.add(4);
    		mysequence.add(5);
    		mysequence.add(6);
    		mysequence.add(7);
    		mysequence.add(8); 
    }
     
    
    }
	


