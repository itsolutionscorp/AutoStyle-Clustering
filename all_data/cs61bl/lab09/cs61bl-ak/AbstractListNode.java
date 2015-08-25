abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get(int index);
    abstract public String toString();
    abstract public boolean equals(AbstractListNode list);
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
    
    public Object get(int index) {
    	if (index == 0) {
    		return myItem;
    	}
    	return myNext.get(index-1);
    }
    
    public String toString() {
    	AbstractListNode next = myNext;
    	Object items = "" + myItem + " ";
    	while(! next.isEmpty()){
    		items = "" + items + next.item() + " ";
    		next = next.next();
    	} 
    	return "( " + items + ")";
    }
    
    public boolean equals(AbstractListNode list) {
    	AbstractListNode next = this;
    	AbstractListNode next1 = list;
    	if (this.size() != list.size()) {
    		return false;
    	}
    	while (! next.isEmpty()) {
    		if (next.item() == next1.item()) {
    			next = next.next();
    			next1 = next1.next();
    		} else {
    			return false;
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
    
    public Object get(int index) {
    	throw new IllegalArgumentException("index out of bounds");
    }
    
    public String toString() {
    	return "";
    }
    
    public boolean equals(AbstractListNode list) {
    	return false;
    }
}
