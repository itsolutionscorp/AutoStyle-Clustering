abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get(int pos);
    abstract public String toString();
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
    
    public Object get(int pos) {
    	if (pos > 0 && !(myNext instanceof EmptyListNode)) {
    		return myNext.get(pos - 1);
    	}
    	else if (pos == 0) {
    		return myItem;
    	} else {
    		throw new IllegalArgumentException("Position out of range!");
    	}
    }
    
    public String toString() {
    	NonemptyListNode helper = this;
    	String ret = new String();
    	ret = ret + "( " + myItem + " ";
    	while (!(helper.myNext instanceof EmptyListNode)) {
    		ret += ((NonemptyListNode) helper.myNext).myItem + " ";
    		helper = (NonemptyListNode) helper.myNext;
    	}
    	ret += ")";
    	return ret;
    }
    
    public boolean equals(Object o) {
    	if (this.size() != ((AbstractListNode) o).size()) {
    		return false;
    	}
    	NonemptyListNode helper = this;
    	if (helper.myItem == ((NonemptyListNode) o).myItem) {
    		while (!(((NonemptyListNode) o).myNext instanceof EmptyListNode)) {
    			helper = (NonemptyListNode) helper.myNext;
    			o = ((NonemptyListNode) o).myNext;
    			if (helper.myItem != ((NonemptyListNode) o).myItem) {
    				return false;
    			}
    		}
    	}
    	return true;
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
    	throw new IllegalArgumentException("Position out of range!");
    }
    
    public String toString() {
    	return "( )";
    }
    
    public boolean equals(Object o) {
    	if (o instanceof EmptyListNode) {
    		return true;
    	}
    	return false;
    }

}
