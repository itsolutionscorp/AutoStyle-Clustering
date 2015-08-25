abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
   
    // Every other list-processing method goes here.
    abstract public int size();
    abstract public Object get(int pos);
    abstract public String toString();
    abstract public String toStringHelper();
    abstract public boolean equals(Object a);
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
    
    public int size() {
    	return 1 + this.next().size();
    }
    public Object get(int pos) {
    	if (pos == 0) return this.item();
    	else return this.next().get(pos-1);
    }
    
    public String toStringHelper() {
    	return (this.item() + " " + this.next().toStringHelper());
    }
    
    public String toString() {
    	return "( " +this.toStringHelper();
    }

    public boolean equals(Object a) {
    	if (this.getClass() == a.getClass()){
    		NonemptyListNode a2 = (NonemptyListNode) a;
    		if(this.size() == a2.size()){
    			if(this.item() == a2.item()){
    				return this.next().equals(a2.next());
    			}else{
    				return false;
    			}  		
    		}else{
    			return false;
    		}
    	}else{
    		return false;
    	}
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
    
    public Object get(int pos) {
    	if (pos == 0) return null;
    	else throw new IllegalArgumentException("List does not go that long");
    }
    public String toStringHelper() {
    	return (")");
    }
    
    public String toString() {
    	return ("()");
    }
    
    public boolean equals(Object a) {
    	if(this.getClass() == a.getClass()){
    		return true;
    	}else{
    		return false;
    	}
    }
}
