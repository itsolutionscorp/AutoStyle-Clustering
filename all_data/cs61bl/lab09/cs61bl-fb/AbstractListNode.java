abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    abstract public int size();
    abstract public Object get(int element_At);
    abstract public String toString();
    abstract public boolean equals(AbstractListNode NodeCompared);
    
}  

class NonemptyListNode extends AbstractListNode {

    private Object myItem;
    private AbstractListNode myNext;

    public NonemptyListNode (Object item, AbstractListNode next) {
        myItem = item;
        if (next == null) {
            myNext = new EmptyListNode();
        } else {
            myNext = next;
        }
    }
    @Override
    public int size(){
        int count = 1;
    	AbstractListNode placeholder= this;
        while (placeholder.next().size()!=0){
        	placeholder = placeholder.next();
    		count++;
        }
        return count;
    }
    @Override
    public Object get(int element_At){
    	AbstractListNode placeholder= this;
    	for(int i=0; i<element_At;i++){
    	    if(placeholder.size() == 0){
    	    	throw new IllegalArgumentException("Index too large	");
    	    }
    		placeholder = placeholder.next();
    	    
    	}
        return placeholder.item();
    }
    @Override
    public String toString(){
    	String returnString = "( ";
    	AbstractListNode placeholder= this;
        while (placeholder.size()!=0){
        	returnString = returnString +""+ placeholder.item()+ " ";
        	placeholder = placeholder.next();
    		
        }
        returnString = returnString + ")";
        return returnString;
    }
    @Override
    public boolean equals(AbstractListNode NodeCompared){
    	if (this.size() == NodeCompared.size()){
    		for(int i = 0; i<=this.size()-1;i++ ){
    			if(!this.get(i).equals(NodeCompared.get(i))){
    				return false;
    			}
    		}
    	}
    	return true;
    	
    }

    public NonemptyListNode (Object item) {
        this (item, new EmptyListNode());
    }

    public Object item() {
        return myItem;
    }

    public AbstractListNode next() {
        return myNext;
    }
    
    public boolean isEmpty() {
        return false;
    }

}

class EmptyListNode extends AbstractListNode {
    
    public EmptyListNode() {
        
    }
    
    public Object item() {
        throw new IllegalArgumentException ("There is no 'item' value stored in an EmptyListNode.");
    }
    
    public AbstractListNode next() {
        throw new IllegalArgumentException ("No elements follow an EmptyListNode.");
    }
    
    public boolean isEmpty() {
        return true;
    }
    @Override
    public int size(){
    	return 0;
    }
    @Override
    public Object get(int element_At){
        throw new IllegalArgumentException("It's just empty brah");	
    }
    @Override
    public String toString(){
    	return new String("( )");
    }
    @Override
    public boolean equals(AbstractListNode NodeCompared){
        
        if (NodeCompared.size() == 0){
        	return true;
        }
    return false;
    }
    

}

