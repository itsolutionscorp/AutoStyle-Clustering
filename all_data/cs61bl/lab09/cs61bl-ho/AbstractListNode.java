abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get (int pos);
    abstract public String toString();
    abstract public String subToString();
    abstract public boolean equal (Object a);
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
    
    public int size(){
    	return 1+myNext.size();
    }
    public Object get (int pos){
    	if (pos==0){
    		return myItem;
    	}
    	return myNext.get(pos-1);
    }
    public String toString(){
    	return ("( " + subToString() + ")");
    }
    public String subToString(){
    	return (" " + item() + myNext.subToString());
    }
    public boolean equal (Object a){
    	if (((AbstractListNode) a).isEmpty()){
    		return false;
    	}
    	if (item().equals(((AbstractListNode) a).item())){
    		return myNext.equal(((AbstractListNode) a).next());
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
    
    public boolean isEmpty() {
        return true;
    }
    public int size(){
    	return 0;
    }
    public Object get (int pos){
    	throw new IllegalArgumentException("The position is out of range.");
    }
    public String toString(){
    	return ("( " + subToString() + ")");    	
    }
    public String subToString(){
    	return null;
    }
    public boolean equal (Object a){
    	if (((AbstractListNode) a).isEmpty()){
    		return true;
    	}
    	return false;
    }
}
