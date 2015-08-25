abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    

    // Every other list-processing method goes here.
    abstract public int size();
    abstract public Object get(int pos);
    abstract public String toString();
    abstract public boolean equals(Object lst);
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
    	return 1+myNext.size();
    	
    }

	@Override
	 public Object get(int pos){
    	if (pos<0) {
    		throw new IllegalArgumentException("The position selected is out of bounds");
    	}
    	if (pos ==0){
    		return myItem;
    	}else{
    		if (myNext instanceof EmptyListNode) {
    			throw new IllegalArgumentException("The position selected is out of bounds");
    		}
    	    return myNext.get(pos-1);
    	}
    }
    public String toString () {
    	String r=new String();
        r="( "+myItem.toString();
   	    AbstractListNode other = myNext;
   	    while(other.toString()!="()") {
   		    r = r +" "+other.item().toString();
   		    other = other.next();
   	    }
   	    return r+" )";
   }
    
    @Override
    public boolean equals(Object lst){
    	AbstractListNode list = (AbstractListNode)lst;
    	if (list.size()==0) {
    		return false;
    	}
    	if (this.item()!=list.item()){
    		return false;
    	}else{
    		return next().equals(list.next());
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
	public AbstractListNode get(int pos) {
		throw new IllegalArgumentException("The position selected is out of bounds");
    }
	
	@Override
	public String toString(){
		return "()";
	}
	@Override
	public boolean equals(Object lst) {
		return lst instanceof EmptyListNode;
	}
} 


