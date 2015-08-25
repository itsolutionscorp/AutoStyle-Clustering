abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public Object get(int num);

    // Every other list-processing method goes here.
    abstract public int size();
    abstract public String toString();
    abstract public boolean equals(Object list);
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
        this(item, new EmptyListNode());
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
    
    @Override
    public Object get(int num) {
		if (num < 0) {
    		throw new IllegalArgumentException("Index Out of Range!");
    	} 
    	if (num == 0) {
    		return item();
    	} else {
    		return next().get(num-1);
    	}
    }
    
    @Override
    public int size(){
    	if (myItem == null) {
    		return 0;
    	} else {
    		return (1 + next().size());
    	}
    }
    
    @Override
    public String toString() {
    	String result = "( ";
    	for (int i=0; i<size();i++) {
    		result += get(i).toString() + " ";
    	}
    	return result + ")";
    }
    
    @Override
    public boolean equals (Object list){
    	try {
    		AbstractListNode list2 = (NonemptyListNode) list;
    		if (size() == list2.size()) {
    			if (item() == list2.item() && size() == 1 ) {
    				return true;
    			} else if (item() == list2.item()) {
    				return next().equals(list2.next());
    			} else {
    				return false;
    			}
    		} else {
    			return false;
    		}
    	} catch (ClassCastException e) {
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

    @Override
    public int size() {
    	return 0;
    }
    	
    @Override
    public Object get(int num) throws IllegalArgumentException {
    	throw new IllegalArgumentException("Index Out of Range!");
    }
    
    @Override
    public String toString() {
    	return "( )";
    }
    
    @Override
    public boolean equals(Object list) {
    	if (toString().equals(list.toString())){
    		return true;
    	} else 
    		return false;
    }
}

