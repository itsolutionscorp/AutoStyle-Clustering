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
    
    public Object get(int pos) {
    	throw new IllegalArgumentException ("The position is out of bounds.");
    }
    
    public String toString() {
    	return ")";
    }
    
    public boolean equals(AbstractListNode lst) {
    	return lst.isEmpty();
    }
}
