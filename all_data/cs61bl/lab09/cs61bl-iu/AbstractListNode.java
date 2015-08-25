abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    abstract public int size();
    abstract public String toString();
    abstract public Object get(int index);
    abstract public boolean equals(Object b);

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
		if (myNext.isEmpty()){
    		return 1;
    	}
    	return 1 + myNext.size();
    }

	@Override
	public String toString() {
		String s = "( " + myItem;
		AbstractListNode temp = myNext;
		while (!temp.isEmpty()){
			s += " " + temp.item();
			temp = temp.next();
		}
		return s + " )";
	}
	
	@Override
	public Object get(int index){
    	if (myNext.isEmpty() && index != 0){
    		throw new IllegalArgumentException("out of bound");
    	}
    	else if (index == 0){
    		return myItem;
    	}
    	return myNext.get(index - 1);
    }

	@Override
	public boolean equals(Object b) {
		if (this.size() != ((AbstractListNode) b).size()){
			return false;
		} else{
			for (int i = 0; i <= this.size() - 1; i++){
				if (!this.get(i).equals(((AbstractListNode) b).get(i))){
					return false;
				}
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
    
	@Override
	public int size() {
		return 0;
	}

	@Override
	public String toString() {
		return "()";
	}
	@Override
	public Object get(int index){
    	throw new IllegalArgumentException("out of bound");
    }

	@Override
	public boolean equals(Object b) {
		return ((AbstractListNode) b).isEmpty();
	}
}
