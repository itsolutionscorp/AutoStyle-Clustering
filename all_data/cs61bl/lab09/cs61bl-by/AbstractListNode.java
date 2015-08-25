import java.awt.List;

abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.

    public int size(){
    	if (isEmpty()){
    		return 0;
    	} else {
    		return 1 + (this.next()).size();
    	}
    }
    
    public Object get(int pos){
    	return null;
    }
    
    public String toString(){
    	String s = new String("(");
    	for (int i = 0; i < this.size(); i++){
    		s+= " "+this.get(i).toString();
    	}
    	s+= " )";
    	return s;
    }
    
    public boolean equals(Object j){
    	if (this.size() != ((AbstractListNode)j).size()){
    		return false;
    	}
    	
    	
    	for (int i = 0; i<size();i++){
    		if (!(this.get(i).equals( ((AbstractListNode)j).get(i)))){
    			return false;
    		}
    	}
    	return true;
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
    
    public Object get(int pos){
    	if (pos == 0){
    		return this.item();
    	} else {
    		return this.next().get(pos -1);
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
    


}
