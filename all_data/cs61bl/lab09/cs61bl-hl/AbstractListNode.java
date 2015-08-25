abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    public Object get(int position){
    	if(position==0)
    		return item();
    	else {
    		if (position < 0 || position >= size())
    			throw new IllegalArgumentException("Position invalid.");
    		return next().get(position-1);
    	}
	}	
    
    public int size(){
    	if (next() != null)
    		return next().size() + 1;
    	else
    		return 1;
    }
    
    public boolean isEmpty() {
    	return size() == 0;
    }
    
    public String toString(){
    	AbstractListNode node = this;
    	String toReturn = "( ";
    	while(!node.isEmpty()) {
    		toReturn += node.item().toString();
    		toReturn += " ";
    		node = node.next();
    	}
    	toReturn += ")";
    	return toReturn;
    }
    public boolean equals(Object o){
    	if (((AbstractListNode) o).isEmpty() && !isEmpty()){
    		return false;
    	}
    	else if (!((AbstractListNode) o).isEmpty() && isEmpty()){
    		return false;
    	}else if (((AbstractListNode) o).isEmpty() && isEmpty()){
    		return true;
    	}else {
			return (((AbstractListNode) o).item().equals(item()) && ((AbstractListNode) o).next().equals(this.next()));
		}
    }
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
    public int size(){
    	return 0;
    }

}
