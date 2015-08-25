abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    public int size() {
    	if (this.isEmpty() == true) {
    		return 0;
    	}
    	return 1 + this.next().size();
    }
    
    public Object get(int pos) {
    	if (pos == 0) {
    		return this.item();
    	}
    	else if (this.next().isEmpty() == true) {
    		throw new IllegalArgumentException("position out of range");
    	}
    	return this.next().get(pos-1);
    }
    
    public String toString() { 	
    	String s = "";
    	AbstractListNode pointer = this;
    	while (pointer.isEmpty() != true) {
    		s += " " + pointer.item();
    		pointer = pointer.next();
    	}
    	return "(" + s + " )";
    }
    
    public boolean equals(Object o) {
    	if (this.size() != ((AbstractListNode) o).size()) {
    		return false;
    	}
    	for (int i = 0; i < this.size(); i++) {
    		for (int j = 0; j < this.size(); j++) {
    			if (this.item() != ((AbstractListNode) o).item()) {
    				return false;
    			}
    			continue;
    		}
    	}
    	return true;
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
