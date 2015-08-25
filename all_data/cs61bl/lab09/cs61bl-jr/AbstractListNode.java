abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size ();
    abstract public Object get(int pos);
    abstract public String toString();
    abstract public boolean equals(Object o);
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
    
    public Object get(int pos){
    	if (next().isEmpty() || pos == 0){
    		return myItem;
    	} else {
    	return next().get(pos-1);
    	}
    }
    
    public String toString(){
    	int getSize = size();
    	String s = "( ";
    	for (int i = 0; i < getSize; i++){
    		if (i == 0){
    			s += get(i);
    		} else {
    		s += " " + get(i);
    		}
    	}
    	s += " )";
    	return s;
    }
    
    public boolean equals(Object o){
    	NonemptyListNode a = (NonemptyListNode)o;
    	for (int i = 0; i <= size(); i++){
    		if (!(a.get(i).equals(get(i)))){
    			return false;
    		}
    		
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
    
    public Object get(int pos){
    	return null;
    }
    
    public String toString() {
    	return "()";
    }
    
    public boolean equals(Object o) {
    	EmptyListNode a = (EmptyListNode)o;
    	return a.isEmpty() && a.size() == 0;
    }
}
