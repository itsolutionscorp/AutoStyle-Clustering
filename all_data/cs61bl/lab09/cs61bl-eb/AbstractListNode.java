abstract public class AbstractListNode {

	abstract public Object item();
	abstract public AbstractListNode next();
	abstract public boolean isEmpty();

	// Every other list-processing method goes here.

	abstract public int size();
	
	abstract public Object get(int pos);
	
    public boolean equals(Object obj) {
    	String s1 = this.toString();
    	String s2 = obj.toString();
    	return s1.equals(s2); // just compare their string representations!
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



	// Every other list-processing method goes here.

	/* Recursive method!
	 * 
	 */
	public int size() {
		if (this.myNext.isEmpty()) {
			return 1;
		}
		else {
			return this.myNext.size() + 1;
		}
	}

	
	
    public Object get(int pos) {
    	int num_times_travel = 0;
    	NonemptyListNode p = this; // p points to the current node
    	while (!p.myNext.isEmpty() && num_times_travel != pos) {
    		// the next thing is not EmptyListNode and still needs further travel until pos times
    		p = (NonemptyListNode) p.myNext;
    		++num_times_travel;
    	}
    	if (num_times_travel != pos) {
    		// you require too many travels and it's already the end of list
    		throw new IllegalArgumentException("you require too many travels and it's already the end of list!");
    	}
    	return p.myItem;
    }
    
    @Override
    public String toString() {
    	String result = "( ";
    	NonemptyListNode p = this; // p points to the current node
    	while(!p.myNext.isEmpty()) {
    		// has next 
    		if (p.myItem != null) {
    			result += p.myItem.toString() + " ";
    		}
    		else {
    			result += "null ";
    		}
    		p = (NonemptyListNode) p.myNext;
    	}
    	// at the last nonempty node, the check above failed, so concatenate here
    	if (p.myItem != null) {
    		result += p.myItem.toString() + " ";
    	}
    	else {
    		result += "null ";
    	}
    	result += ")";
    	return result;
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


	// Every other list-processing method goes here.

	public int size() {
		return 0;
	}
	
	
    public Object get(int pos) {
    	throw new IllegalArgumentException("you're getting object from an empty list!");
    }
    
    
    @Override
    public String toString() {
    	return "( )";
    }

}
