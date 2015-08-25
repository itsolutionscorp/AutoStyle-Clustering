abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    
    // Every other list-processing method goes here.
    abstract public int size();
    abstract public Object get(int pos);
    abstract public String toString();
    abstract public boolean equals(AbstractListNode list);
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
    	return 1 + next().size();
    }
    
    public Object get(int pos){
    	if (pos == 0){
    		return item();
    	} else if (next().isEmpty()){
    		throw new IllegalArgumentException("Position out of range");
    	} else {
    		return next().get(pos - 1);
    	}
    }
    
    public String toString(){
    	String s = "(";
    	for (int i = 0; i < this.size(); i++){
    		s = s + " " + get(i);
    		if (i == size() - 1){
    			s = s + " )";
    		}
    	}
    	System.out.println(System.currentTimeMillis());
    	return s;
    }
    
    public boolean equals(AbstractListNode list){
    	if(this.size() == list.size()){
    		if(this.item().equals(list.item())){
    			return this.next().equals(list.next());
    		}
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
    
    public Object get(int pos){
    	throw new IllegalArgumentException("There is no 'item' to get");
    }
    
    public String toString(){
    	return "( )";
    }
    
    public boolean equals(AbstractListNode list){
    	if(list.isEmpty()){
    		return true;
    	}
    	return false;
    }

}
