abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get(int i);
    abstract public String toString();
    abstract protected String strHelper();
    abstract public boolean equals(Object o);

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
    	return 1 + myNext.size();
    }
   
    public Object get(int i) {
    	if (i < 0) throw new IllegalArgumentException("i must not be negative.");
    	if (i == 0) return myItem;
    	else return myNext.get(i - 1);
    }

    public String toString() {
    	return "( " + strHelper() + ")";
    }
    
    protected String strHelper() {
    	return myItem.toString() + " " + myNext.strHelper();
    }
    
    public boolean equals(Object o) {
    	if (!(o instanceof NonemptyListNode)) return false;
    	else {
    		NonemptyListNode other = (NonemptyListNode) o;
    		return myItem.equals(other.myItem) && myNext.equals(other.myNext);
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
    
    public Object get(int i) {
    	throw new IllegalArgumentException("Out of range");
    }
    
    public String toString() {
    	return "( )";
    }
    
    protected String strHelper() {
    	return "";
    }
    
    public boolean equals(Object o) {
    	if (!(o instanceof EmptyListNode)) return false;
    	return true;   	
    }

}
