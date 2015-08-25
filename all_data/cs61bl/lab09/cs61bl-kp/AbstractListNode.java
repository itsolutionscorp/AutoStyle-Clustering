abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get(int x);
    abstract public String toString();
    abstract public boolean equals(Object m);

    // Every other list-processing method goes here.

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
    
    public Object get(int x) {
    	if (x == 0){
    		return this.item();
    	}
    	return this.next().get(x-1);
    }
    
    public String toString(){
    	String rtn = "( ";
    	AbstractListNode currNode = this;
    	while (!currNode.isEmpty()) {
    		rtn = rtn + currNode.item().toString() + " ";
    		currNode = currNode.next();
    	}
    	return rtn + ")";
    }
    
    public boolean equals(Object m){
    	if (!(m instanceof AbstractListNode)) {
    		return false;
    	}
    	if (((AbstractListNode) m).isEmpty()) {
    		return false;
    	}
    	if (!(this.item().equals(((AbstractListNode) m).item()))) {
    		return false;
    	}
    	return this.next().equals(((AbstractListNode) m).next());
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
    
    public Object get(int x) {
    	throw new IllegalArgumentException ("There is no 'item' to get from an EmptyListNode.");
    }
    
    public String toString(){
    	return "()";
    }
    
    public boolean equals(Object m){
    	if (!(m instanceof AbstractListNode)) {
    		return false;
    	}
    	return ((AbstractListNode) m).isEmpty();
    }

}
