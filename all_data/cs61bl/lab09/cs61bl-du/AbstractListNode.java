abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.

    public int size() {
    	if (isEmpty()) {
    		return 0;
    	} else {
    		return 1 + next().size();
    	}
    }
    
    public Object get(int position) {
    	if (isEmpty()) {
    		throw new IllegalArgumentException();
    	} else if (position == 0) {
    		return item();
    	} else {
        	if (next().isEmpty()) {
        		throw new IllegalArgumentException();
        	} else {
    		return next().get(position - 1);
        	}
    	}
    }
    
    public String toString() {
    	String returnString = "(";
    	for (int i = 0; i < size(); i++){
    		returnString += " " + get(i);
    	}
    	return returnString + " )";

    }
    
    public boolean equals(AbstractListNode lst) {
    	return this.toString().equals(lst.toString());
    }
    
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

}
