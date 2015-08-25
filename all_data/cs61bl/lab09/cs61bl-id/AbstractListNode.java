abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    public static int size;
    abstract public Object get(int index);
    abstract public String toString();
    abstract public boolean equals(Object x);
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
    	size = size + 1;
    	return this.next().size();
    }
    
    public Object get(int index){
    	if(index == 0){
    		return myItem;
    	}
    	else if(myNext == null){
    		throw new IllegalArgumentException();
    	}
    	else{
    		return myNext.get(index-1);
    	}
    }
    
    public String toString() {
    	String saved = "(";
    	for (int x = 0; x < this.size(); x = x +1) {
    		saved = saved + " " + this.get(x); 
    	}
    	return saved + " )";
    }
    
    public boolean equals(Object x) {
    	if (this.size() == ((AbstractListNode) x).size()) {
    		for (int i = 0; i < this.size(); i = i +1) {
    			if (this.get(i) != ((AbstractListNode) x).get(i)) {
    				return false;
    			} 
    		}
    		return true;
    	} 
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
    public int size() {
    	int temp = size;
    	size = 0;
    	return temp;
    }
    
    public boolean isEmpty() {
        return true;
    }

	@Override
	public Object get(int index) {
		return null;
	}
	
	@Override
	public String toString() {
		return "( )";
	}

}
