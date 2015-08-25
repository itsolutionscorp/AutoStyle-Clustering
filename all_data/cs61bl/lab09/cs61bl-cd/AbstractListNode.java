abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get(int index);
    abstract public String toString();
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
    	return myNext.size() + 1;
    }
    
    public Object get(int index) {
    	if(index >= size()-1){
    		throw new IllegalArgumentException();
    	}
    	int i = 0;
    	AbstractListNode nextNode = this;
    	while (i < index){
    		nextNode = nextNode.next();
    		i++;
    	}
    	return nextNode.item();
    }
    
    public String toString(){
    	String stringList = "( ";
    	for(int i = 0; i < size() - 1; i++){
    		stringList = stringList + get(i).toString() + " ";
    	}
    	stringList = stringList + ")";
    	return stringList;
    }
    
    public boolean equals(AbstractListNode first){
        if (this.size() == 1 && first.size() == 1){
        	return true;
        }
        else if (this.size() != first.size()){
        	return false;
        }
        else if (this.item() != first.item()){
    		return false;
    	}
    	else{
    		return this.myNext.equals(first.next());
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
    
    public int size(){
    	return 1;
    }

    public Object get(int index) {
    	throw new IllegalArgumentException();
    }
    
    public String toString(){
    	String list = "()";
    	return list;
    }

}
