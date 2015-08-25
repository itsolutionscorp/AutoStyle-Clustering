abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();


    // Every other list-processing method goes here.
    abstract public int size();
    abstract public Object get(int i);
    abstract public String toString();
    public abstract boolean equals(AbstractListNode b);
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
	public Object get(int i) {
    	if(i == 0){
    		return myItem;
    	}else{
    		if(myNext == null){
    			throw new IllegalArgumentException();
    		}
    		return myNext.get(i - 1);
    	}
    
	}

	@Override
	public String toString() {
		AbstractListNode curr = this;
		String myStr = "";
		while (!curr.isEmpty()){
			myStr += " " + curr.get(0).toString();
			curr = curr.next();
		}
		return "(" + myStr + " )";
	}

	@Override
	public boolean equals(AbstractListNode b) {
//		if (this.myItem != b.get(0)){
//			return false;
//		}
//		return true && this.next().equals(b.next());
		if (this.myItem != b.get(0)){
			return false;
		}else if (this.myItem == b.get(0) && this.myNext.isEmpty()){
			return true;
		}else{
			return true && this.next().equals(b.next());
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

	@Override
	public int size() {
		return 0;
	}

	@Override
	public Object get(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ")";
	}

	@Override
	public boolean equals(Object b) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean equals(AbstractListNode b) {
		// TODO Auto-generated method stub
		return false;
	}


}
