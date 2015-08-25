import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Comparable get(int i);
    abstract public String toString();
    abstract protected String strHelper();
    abstract public boolean equals(Object o);
    abstract public AbstractListNode add (Comparable c);
    abstract public AbstractListNode append(AbstractListNode list);
    abstract public AbstractListNode clone();
    abstract public AbstractListNode reverse();
    abstract public AbstractListNode appendInPlace(AbstractListNode list2);
    
    abstract public void setNext(AbstractListNode n);
    
    /**
     * Sorts p1 and p2 in memory. Assumes that both are in ascending order.
     * @param p1
     * @param p2
     * @return
     */
    public static AbstractListNode merge (AbstractListNode p1, AbstractListNode p2) {
    	AbstractListNode head = new EmptyListNode();
    	AbstractListNode next = null;
    	while (!(p1.isEmpty()) || !(p2.isEmpty())) {
    		if (!(p1.isEmpty()) && !(p2.isEmpty())) {
    			if (p1.item().compareTo(p2.item()) <= 0) {
    				//p1 <= p2
    				if (next != null) { 
    					next.setNext(p1);
    					next = next.next();
    				}
    				else {
    					next = p1;
    					head = next;
    				}
    				p1 = p1.next();
    			}
    			else {
    				if (next != null) {
    					next.setNext(p2);
    					next = next.next();
    				}
    				else {
    					next = p2;
    					head = next;
    				}
    				p2 = p2.next();
    			}
    		}
    		else if (!(p1.isEmpty())) {
    			if (next != null) {
    				next.setNext(p1);
    				return head;
    			}
    			else {
    				return p1;
    			}
    		}
    		else {
    			if (next != null) {
    				next.setNext(p2);
    				return head;
    			}
    			else {
    				return p2;
    			}
    		}
    	}
    	return head;
    }

    public static Comparable min(Comparable c1, Comparable c2) {
    	if (c1.compareTo(c2) < 0) {
    		return c1;
    	} else {
    		return c2;
    	}
    }

    public Comparable smallest() {
    	if (isEmpty()) {
    		throw new NoSuchElementException("Can't find smallest in empty list.");
    	}
    	return smallestHelper(this.item()) ;
    }
    
    public Comparable smallestHelper(Comparable smallestSoFar) {
    	if (isEmpty()) {
    		return smallestSoFar;
    	} else {
    		return smallestHelper(min(smallestSoFar, next().item()));
    	}
    }
    
    // Every other list-processing method goes here.

}

class NonemptyListNode extends AbstractListNode {

    private Comparable myItem;
    private AbstractListNode myNext;

    public NonemptyListNode (Comparable item, AbstractListNode next) {
        myItem = item;
        if (next == null) {
            myNext = new EmptyListNode();
        } else {
            myNext = next;
        }
    }

    public NonemptyListNode (Comparable item) {
        this (item, new EmptyListNode());
    }
    
    private NonemptyListNode (NonemptyListNode toCopy) {
    	myItem = toCopy.myItem;
    	myNext = toCopy.myNext;
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
    	return 1 + myNext.size();
    }
   
    public Comparable get(int i) {
    	if (i < 0) throw new IllegalArgumentException("i must not be negative.");
    	if (i == 0) return myItem;
    	else return myNext.get(i - 1);
    }

    public String toString() {
    	return "( " + strHelper() + ")";
    }
    
    protected String strHelper() {
    	return myItem.toString() + " " + myNext.strHelper();
    }
    
    public boolean equals(Object o) {
    	if (!(o instanceof NonemptyListNode)) return false;
    	else {
    		NonemptyListNode other = (NonemptyListNode) o;
    		return myItem.equals(other.myItem) && myNext.equals(other.myNext);
    	}
    }
    
    public AbstractListNode add (Comparable c) {
    	NonemptyListNode copy = new NonemptyListNode(this);
    	copy.myNext = this.next().add(c);
    	return copy;
    }

    public AbstractListNode clone() {
    	NonemptyListNode copy = new NonemptyListNode(this);
    	copy.myNext = this.next().clone();
    	return copy;
    }
    
    public AbstractListNode append(AbstractListNode list) {
    	AbstractListNode first = this.clone();
    	AbstractListNode second = list.clone();
    	AbstractListNode current = first;
    	while (!current.next().isEmpty()) { current = current.next(); }
    	((NonemptyListNode)current).myNext = second;
    	return first;
    }
    
    public AbstractListNode reverse () {
    	AbstractListNode copy = this.clone();
    	AbstractListNode toReturn = new EmptyListNode();
    	while (!copy.isEmpty()) {
    		AbstractListNode temp = copy;
    		copy = copy.next();
    		((NonemptyListNode)(temp)).myNext = toReturn;
    		toReturn = temp;
    	}
    	return toReturn;
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2){
    	AbstractListNode current = this;
    	while (!current.next().isEmpty()) {
    		current = current.next();
    	}
    	((NonemptyListNode)current).myNext = list2;
    	return this;
    }
    
    public void setNext(AbstractListNode n) {
    	myNext = n;
    }
}

class EmptyListNode extends AbstractListNode {
    
    public EmptyListNode() {
        
    }
    
    public Comparable item() {
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
    
    public Comparable get(int i) {
    	throw new IllegalArgumentException("Out of range");
    }
    
    public String toString() {
    	return "( )";
    }
    
    protected String strHelper() {
    	return "";
    }
    
    public boolean equals(Object o) {
    	if (!(o instanceof EmptyListNode)) return false;
    	return true;   	
    }
    
    public AbstractListNode add (Comparable c) {
    	return new NonemptyListNode(c, new EmptyListNode());
    }
    
    public AbstractListNode clone() {
    	return new EmptyListNode();
    }
    
    public AbstractListNode append (AbstractListNode list) {
    	return list.clone();
    }
    
    public AbstractListNode reverse () {
    	return new EmptyListNode();
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2){
    	return list2;
    }
    
    public void setNext(AbstractListNode n){
    	throw new IllegalStateException("Has no field.");
    }

}