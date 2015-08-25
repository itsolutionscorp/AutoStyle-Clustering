abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public Object get(int i);
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
    public Object get (int i) {
    	if( i == 0) {
    		return myItem;
    	} else {
    		if (!myNext.isEmpty()) {
    			return ((NonemptyListNode) myNext).get(i - 1);
    		} throw new IllegalArgumentException();
    	}
    }
    public int size() {
    	int i = 0;
    	if (!myNext.isEmpty()) {
    		return ((NonemptyListNode) myNext).size() + 1;
    	} else {
    		return i;
    	}
    }
    public String toString() {
    	return "( " + stringHelp();
    }
    private String stringHelp() {
    	if (!myNext.isEmpty()){
    		return myItem.toString() + " )";
    	}
    	else{
    		return myItem.toString() + ((NonemptyListNode) myNext).stringHelp();
    	}
    }
	public boolean equals( Object obj) {
		try{
			for (int i = 0; i < size(); i++){
				if (get(i) != ((NonemptyListNode) obj).get(i)){
					return false;
				}	
			}
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}
}


class EmptyListNode extends AbstractListNode {
    public Object get (int i){
    	throw new IllegalArgumentException();
    }
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
    public String toString(){
    	return "()";
    }
    public boolean equals( Object obj) {
    	return (((AbstractListNode) obj).isEmpty());
    }
    	
}
