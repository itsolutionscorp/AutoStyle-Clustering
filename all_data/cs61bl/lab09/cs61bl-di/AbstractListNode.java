abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get(int n);
    abstract public String toString();
    abstract public String toStringHelper();
    abstract public boolean equals(AbstractListNode a);
    // Every other list-processing method goes here.

}

class NonemptyListNode extends AbstractListNode {

    private Object myItem;
    private AbstractListNode myNext;
    private int total;
    private String paran = "( ";
    
    public boolean equals(Object o) {
        AbstractListNode recast = (AbstractListNode) o;
        if (recast.size() == this.size()) {
        	for (int i = 0; i<this.size(); i++) {
        		if (this.next() != recast.next()) {
        			return false;
        		}
        	} return true;
        } 
        return false;
    }

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
    

    public int size() {
        total++;
        return total + this.next().size();
    }
    
    public Object get(int i) {
        if (i<0) {
        	throw new IllegalArgumentException("Index out of bounds");
        } else if (i == 0) {
            return item();
        } else {
            return next().get(i-1);
        }
    }
    
    public String toString(){
  
    	return paran + this.toStringHelper(); 
    	
    };
    
    public String toStringHelper(){	
    	
    	if (this.next().isEmpty()) {
    		return this.item() + " " + ")";
    	} else {
    		return this.item() + " " + next().toStringHelper();
    	}
    }

	public boolean equals(AbstractListNode a) {
		AbstractListNode recast = (AbstractListNode) a;
	    if (recast.size() == this.size()) {
	    for (int i = 0; i<this.size(); i++) {
	    if (this.next() != recast.next()) {
	    return false;
	    }
	    } return true;
	    } 
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
    
    public int size() {
        return 0;
    }
    
    public Object get(int n) {
        throw new IllegalArgumentException("Index out of range");
    }
    
    public String toString(){
    	return "( )";
    }
    
    public String toStringHelper(){	
    	return "";
    }
    
    public boolean equals(AbstractListNode o) {
        if (o.size() == 0) {
        	return true;
        } 
        return false;
    }
    
}

