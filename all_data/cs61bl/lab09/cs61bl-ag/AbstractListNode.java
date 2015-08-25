abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get(int index);
    //abstract public boolean equals(AbstractListNode lst);
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
		return 1+myNext.size();
	}

	@Override
	public Object get(int index){
    	if(index==0){
    		return myItem;
    	}    		
    	else
    		return next().get(--index);
    }
	
	public String toString() {
		return "( " + myItem.toString() + " " + myNext.toString().substring(2);
	}
	
	public boolean equals(Object lst) {
		try{
			AbstractListNode node = (AbstractListNode)lst;
			if (!(myItem.equals(node.item()))) {
				return false;
			}
			return myNext.equals(node.next());
			}
		catch(ClassCastException e){
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
	public Object get(int index) {
		throw new IllegalArgumentException("There's nothing here.");
	}

	public String toString() {
		return "  )";
	}
	
	public boolean equals(Object lst) {
		try{
			AbstractListNode node = (AbstractListNode)lst;
			if (node.isEmpty()) {
				return true;
			}
			return false;
		}
		catch(ClassCastException e){
			return false;
		}
	}
}
