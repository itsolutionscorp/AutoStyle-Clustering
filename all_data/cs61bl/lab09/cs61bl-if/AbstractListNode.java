abstract public class AbstractListNode {
	
    public static final String Type = null;

	abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    abstract public int size();
    
	public Object get(int a){	    	
		AbstractListNode temp = this; 
		int count = 0;
		while (temp!=null && a>count){
			temp = temp.next();
		    count++;
		}		
		if (a!=count || temp==null || temp instanceof EmptyListNode){
			throw new IllegalArgumentException();
		}		
		return temp.item();
	}
	
    abstract public String toString();
    
    abstract public boolean equals(Object t);
    
    abstract public String Type();
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
		int count = 1;
		AbstractListNode temp = next();
		
		while (!temp.Type().equals("EmptyListNode")){
			temp = temp.next();
			count++;
		}
		return count;
	}

	@Override
	public String toString() {
		String TempString = "( ";
		AbstractListNode temp = this;
		
		while(!temp.Type().equals("EmptyListNode")){
			TempString = TempString + temp.item() + " ";
			temp = temp.next();
		}
		
		return TempString + ")";
	}

	@Override
	public boolean equals(Object t) {
		
		if (((AbstractListNode) t).Type().equals("NonemptyListNode") == false)
			return false;		
		
		AbstractListNode t1 = (NonemptyListNode) t;		
		AbstractListNode temp = this;
		
		while (!temp.Type().equals("EmptyListNode") && !t1.Type().equals("EmptyListNode")){
			if (temp.item() != t1.item()){
				return false;
			}
			if ( (temp.next().Type().equals("EmptyListNode")  && t1.next().Type().equals("NonemptyListNode")) ||
					(temp.next().Type().equals("NonemptyListNode")  && t1.next().Type().equals("EmptyListNode")))
				return false;
			temp = temp.next();
			t1 = t1.next();
		}		
		return true;
	}

	@Override
	public String Type() {
		// TODO Auto-generated method stub
		return "NonemptyListNode";
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
		return "( )";
	}

	@Override
	public boolean equals(Object t) {
		AbstractListNode t1 = (EmptyListNode) t;
		return t1.Type().equals("EmptyListNode");
	}

	@Override
	public String Type() {
		// TODO Auto-generated method stub
		return "EmptyListNode";
	}

}
