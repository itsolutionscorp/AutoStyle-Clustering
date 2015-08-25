abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public String toString();
    // Every other list-processing method goes here.
    
    public Object get(int pos){
    	AbstractListNode x = this;
    	for (int i = 0; i < pos; i++){
    		if (!x.isEmpty() && !x.next().isEmpty()){
    			x = ((AbstractListNode) x).next();
    		}
    		else{
    			throw new IllegalArgumentException("Position out of range.");
    		}
    	}
    	return ((AbstractListNode) x).item();
    }
    
    public boolean equals(Object obj){
    	AbstractListNode x = this;
    	Boolean same = false;
    	if (this.size() == ((AbstractListNode) obj).size()){
    		for (int i = 0; i < this.size(); i++){
    			if (x.get(i) == ((AbstractListNode) obj).get(i)){
    				same = true;
    			}
    			else{
    				return false;
    			}
    		}
    	}
		return same;
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
    
    public int size(){
    	AbstractListNode x = this;
    	if(!x.isEmpty()){
    		x = ((NonemptyListNode) x).next();
    		return 1 + x.size();
    	}
    	return 0;
    }
    
    public String toString(){
    	String str = "( ";
    	AbstractListNode x = this;
    	for (int i = 0; i < this.size(); i++){
    		str += x.get(i) + " ";
    	}
    	return str + ")";
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
    
    public int size(){
    	return 0;
    }
    
    public String toString(){
    	return "( )";
    }

}
