abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get(int a);
    abstract public String toString();
    abstract public boolean equals(Object a);
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
    
    public Object get(int index) throws IllegalArgumentException{
    	if (index==0) {
    		return this.myItem;
    	} else {
    		return myNext.get(index-1);
    	}
    }
    
    public String toString() {
    	AbstractListNode temp = this;
    	String result = "( ";
    	while (!temp.isEmpty()) {
    		result = new String(result+temp.item()+" ");
    		temp = temp.next();
    	}
    	result = new String(result+")");
    	return result;
    }
    
    public boolean equals(Object a) {
    	if (a instanceof NonemptyListNode) {
    		NonemptyListNode real = (NonemptyListNode) a;
    		if (real.size()!=size()) {
    			return false;
    		} else {
    			if (real.item().equals(myItem)) {
    				return myNext.equals(real.next());
    			} else return false;
    		}
    	} else if (a instanceof EmptyListNode) {
    		return false;
    	} else {
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
    
    public Object get(int index) throws IllegalArgumentException{
    	throw new IllegalArgumentException("The requested index is out of range.");
    }
    
    public String toString() {
    	return "()";
    }
    
    public boolean equals(Object a) {
    	return a instanceof EmptyListNode;
    }
}
