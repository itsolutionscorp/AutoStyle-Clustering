abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get(int y);
    abstract public String toString();
    abstract public String helperFunction();
    abstract public boolean equals(AbstractListNode lst);

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
    	if(next().isEmpty()) {
    		return 1;
    	} else {
    		return 1 + next().size();
    	}
    }
    public Object get(int pos) {
    	if(next().isEmpty() && pos != 0 ) {
    		throw new IllegalArgumentException("out of bounds");
    	} else if (pos == 0) {
    		return myItem;
    	} else {
    		pos -= 1;
    		return next().get(pos);
    	}
    	
    }
    public String toString() {
    		return "(" + helperFunction();
    }
    
    public String helperFunction() {
    	if (next().isEmpty()) {
    		return " " + item() + " )";
    	} else {
    		return " " + item() + next().helperFunction();
    	}
    }
    
    public boolean equals(AbstractListNode lst) {
    	if (next().isEmpty() && lst.next().isEmpty() && lst.item() == item()) {
    		return true;
    	} else if (!next().isEmpty() && lst.next().isEmpty()) {
    		return false;
    	} else if (next().isEmpty() && !lst.next().isEmpty()) {
    		return false;
    	} else if(item() == lst.item()) {
    		return next().equals(lst.next());
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
    public Object get(int pos) {
    	return null;
    }
    public String toString () {
    	return "( )";
    }
    public String helperFunction () {
    	return "( )";
    }
    public boolean equals(AbstractListNode lst) {
    	if (lst.isEmpty()) {
    		return true;    		
    	} else {
    		return false;
    	}
    }

}
