class NonemptyListNode extends AbstractListNode {

    private Comparable myItem;
    private AbstractListNode myNext;

    public NonemptyListNode(Comparable item, AbstractListNode next) {
    	myItem = item;
        if (next == null) {
            myNext = new EmptyListNode();
        } else {
            myNext = next;
        }
    }

    public void setItem(Comparable item) {
    	myItem = item;
    }
    
    public void setNext(AbstractListNode node) {
    	myNext = node;
    }
    
    public NonemptyListNode(Comparable item) {
        this (item, new EmptyListNode());
    }

    public Comparable item() {
        return myItem;
    }

    public AbstractListNode next() {
        return myNext;
    }

    public boolean isEmpty() {
    	return false;
    }

    public int size() {
    	return 1 + next().size();
    }

	public Comparable get(int pos) {
    	if (pos == 0)
    		return item();
    	return next().get(pos - 1);
    }
	
	public String toString() {
		String result = "( ";
		AbstractListNode current = this;
		for (int i = 0; i < size(); i++) {
			result += current.item() + " ";
			current = current.next();
		}
		result += ")";
		return result;
	}
	
	public boolean equals(AbstractListNode lst) {
		return item().equals(lst.item()) && next().item().equals(lst.next().item());
	}
}