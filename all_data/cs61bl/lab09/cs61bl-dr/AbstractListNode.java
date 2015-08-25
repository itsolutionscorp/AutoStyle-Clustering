abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();

    // Every other list-processing method goes here.
    
    abstract public Object get(int posn);
    abstract public String toString();
    abstract public boolean equals(AbstractListNode node);

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
    public int size(){    	
    	return 1 + next().size();
    }
    public Object get(int posn){    				
		if (posn == 0)
			return item();  			
		else return next().get(posn - 1);
	} 
    public String toString(){
    	return "( " + toString(this) + ")";
    }
    public String toString(AbstractListNode n){  
    	if (n.isEmpty()) return "";
    	else return n.item() + " " + toString(n.next());
    }
    public boolean equals(AbstractListNode node){
    	if(size() == node.size() && item().equals(node.item()))    		
    		return next().equals(node.next());        	        	    	
    	else 
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
    public int size(){
    	return 0;
    }
    
	public Object get(int posn){
		throw new IllegalArgumentException("Cannot retrieve elements from an EmptyListNode.");
	}
	public String toString(){
		return "( )";
	}
	public boolean equals(AbstractListNode node){
		if(node.size() == 0) return true;
		else return false;
    }

}
