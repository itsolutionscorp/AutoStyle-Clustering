abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    public int size() {
    		if (this.isEmpty()) {
    			return 0;
    		} else {
    			return 1 + next().size();
    		}
    }
    
    public Object get (int i) {
		if (i < 0) {
			throw new IllegalArgumentException();
		}
		if (i == 0) {
			return item();
		}
		
		if (next().isEmpty()){
			if (i == 0) {
				return item();
			}
			throw new IllegalArgumentException();
		
		} else {
			return next().get(i - 1);
		}
}
    
    public String toString() {
    		String init = "( ";
    		if(isEmpty()){
    			return "( )";
    		}
    		else{
    			AbstractListNode nextItem=this;
    			while (nextItem.isEmpty() == false) {
    				init = init + nextItem.item() + " ";
    				nextItem=nextItem.next();
    			}
    			return init + ")";
    		}
    }
    
    public boolean equals(AbstractListNode l) {
    		if (size() != l.size()) {
    			return false;
    		
    		} else if (isEmpty() && l.isEmpty()) {
    			return true;
    		} else {
    			if (item().equals(l.item())) {
    				return next().equals(l.next());
    			} else {
    				return false;
    			}
    		}
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
