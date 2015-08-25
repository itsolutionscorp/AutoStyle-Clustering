abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    abstract public int size();
    abstract public Object get(int index);
    abstract public String toString();
    abstract public boolean equals(Object o);
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
    	return 1 + next().size();
    }
    
    public Object get(int index) throws IllegalArgumentException {
    	if (index == 0) {
    		return item();
    	}
    	else if (index > 0 && next().isEmpty()) {
    		throw new IllegalArgumentException("Index out of bounds");
    	}
    	else {
    		return next().get(index - 1);
    	}
    }

    public String toString() {
    	String str = "( " + item() + " ";
    	AbstractListNode rest = next();
    	while (!rest.isEmpty()) {
    		str += rest.item() + " ";
    		rest = rest.next();
    	}
    	str += ")";
    	return str;
    }
    
    public boolean equals(Object o) {
    	return toString().equals(o.toString());
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

    public Object get(int index) {
    	return null;
    }
    
    public String toString() {
    	return "( )";
    }
    
    public boolean equals(Object o) {
    	return toString().equals(o.toString());
    }
}
