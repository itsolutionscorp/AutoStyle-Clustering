abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public AbstractListNode get(int k);
    abstract public String toString();
    abstract public boolean equals(AbstractListNode node);
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
    
    public AbstractListNode get(int index) {
    	AbstractListNode rtnList = this;
    	int i = 0;
    	while (i < index) {
    		rtnList = rtnList.next();
    		i++;
    	}
    	return rtnList;
    }
    
    public String toString(){
    	String rtnString = "( ";
    	AbstractListNode temp = this;
    	while(!temp.isEmpty()){
    		rtnString = rtnString + temp.item() + " ";
    		temp = temp.next();
    	}
    	return rtnString + ")";
    }
    
    public boolean equals (AbstractListNode node){
    	if(this.size()!=node.size())
    		return false;
    	AbstractListNode lst1 = this;
    	AbstractListNode lst2 = node;
    	while( !lst1.isEmpty() ){
    		if(!lst1.item().equals(lst2.item()))
    			return false;
    		lst1 = lst1.next();
    		lst2 = lst2.next();
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
    
    public AbstractListNode get(int index) {
  
    	throw new IllegalArgumentException();
    }
    
    public String toString(){
    	return "()";
    }
    
    public boolean equals(AbstractListNode other){
    	if(other.isEmpty()){
    		return true;
    	}
    	return false;
    }
    

}
