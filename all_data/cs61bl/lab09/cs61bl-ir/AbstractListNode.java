abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get(int x);
    abstract public String toString();
    abstract public boolean equals(AbstractListNode a);

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

    @Override
    public int size(){
    	return 1 + this.next().size();
    }
    
    @Override
    public Object get (int x){
    	if(x == 0){
    		return this.myItem;
    	}else{
    		return this.myNext.get(x-1);
    	}
    }
    
    @Override
    public String toString(){
    	int counter = 0;
    	String rtnValue = "( ";
    	AbstractListNode copyNode = this;
    	while (counter < this.size()){
    		rtnValue += copyNode.item() + " ";
    		counter++;
    		copyNode = copyNode.next();
    	}
    	rtnValue += copyNode.toString();
    	return rtnValue;
    }
    
    public boolean equals(AbstractListNode a){
    	int counter = 0;
    	AbstractListNode copyThisNode = this;
    	while (counter < this.size()){
    		if (copyThisNode.item() != a.item()){
    			return false;
    		}
    		copyThisNode = copyThisNode.next();
    		a = a.next();
    		counter++;
    	}
    	return copyThisNode.equals(a);
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
    
    @Override
    public Object get(int x){
    	return 10;
    	//throw new IllegalArgumentException("there is no item at that index");
    }
    
    @Override
    public String toString(){
    	return ")";
    }
    @Override
    public boolean equals(AbstractListNode a){
    	if (a.isEmpty()){
    		return true;
    	}else{
    		return false;
    	}
    	
    }

}
