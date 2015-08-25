abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    
	public Object get(int pos) {
		// TODO Auto-generated method stub
		AbstractListNode cur = this;
    	
    	if (pos < 0) {
    		throw new IllegalArgumentException("Position does not exist!");
    	}
    	
    	while (!cur.isEmpty() && pos > 0) {
    		cur = cur.next();
    		pos--;
    	}
    	
    	if (cur == null || pos > 0) {
    		throw new IllegalArgumentException("Position does not exist!");
    	}
    	
    	return cur.item();
	}
	
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

	@Override
	public int size() {
		// TODO Auto-generated method stub
		AbstractListNode cur = this;
		int size = 0;
		
		while (!cur.isEmpty()) {
			size++;
			cur = cur.next();
		}
		return size;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		AbstractListNode cur = this;
		
		String result = "( ";
		while (!cur.isEmpty()) {
			result = result + cur.item() + " ";
			cur = cur.next();
		}
		
		result += ")";
		
		return result;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		if (o instanceof NonemptyListNode) {
			AbstractListNode cur = (AbstractListNode)o;
			AbstractListNode current = this;
			while (!current.isEmpty()) {
				if (current.item() != cur.item()) {
					return false;
				}
				current = current.next();
				cur = cur.next();
			}
			if (cur.isEmpty())
				return true;
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

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "( )";
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return o instanceof EmptyListNode;
	}
}
