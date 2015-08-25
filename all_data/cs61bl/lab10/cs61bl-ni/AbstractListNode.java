import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Comparable get(int pos);
    public String toString() {
    	String result = "( ";
    	AbstractListNode currentNode = this;
    	while (!currentNode.isEmpty()) {
    		result += currentNode.item().toString() + " ";
    		currentNode = currentNode.next();
    	}
    	result += ")";
    	return result;
    }
    abstract public boolean equals(Object x);
    public Comparable smallest() {
    	if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	}
    	AbstractListNode currentNode = this;
    	Comparable smallestSoFar = this.item();
    	while (!currentNode.next().isEmpty()) {
    		smallestSoFar = smallestHelper(currentNode.next().item());
    		currentNode = currentNode.next();
    	}
    	return smallestSoFar;
    }

    public Comparable smallestHelper(Comparable smallestSoFar) {
    	return min(this.item(), smallestSoFar);
    }

    public static Comparable min(Comparable c1, Comparable c2) {
    	if (c1.compareTo(c2) < 0) {
    	    return c1;
    	} else {
    	    return c2;
    	}
    }
    
    public AbstractListNode add (Comparable c){
    	if (this.isEmpty()) {
    		return new NonemptyListNode(c, null);
    	} else {
    		return new NonemptyListNode(this.item(), this.next().add(c));
    	}
    }
    
    public AbstractListNode append(AbstractListNode list) {
    	if (list.isEmpty()) {
    		return this;
    	} else if (this.isEmpty()) {
    		return list;
    		
    	} else {
    		return new NonemptyListNode(this.item(), this.next().append(list));
    	}
    }
    
    public AbstractListNode reverse() {
    	if (isEmpty()) {
    		return new EmptyListNode();
    	}
    	AbstractListNode copy = new EmptyListNode();
    	AbstractListNode currentNode = this;
    	while (!currentNode.next().isEmpty()) {
    		copy = new NonemptyListNode(currentNode.item(), copy);
    		currentNode = currentNode.next();
    	}
    	return new NonemptyListNode(currentNode.item(), copy.reverse().reverse());
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2) {
    	if (this.isEmpty()) {
    		return list2;
    	} else {
    		AbstractListNode currentNode = this;
    		while (!currentNode.next().isEmpty()) {
    			currentNode = currentNode.next();
    		}
    		((NonemptyListNode) currentNode).setNext(list2);
    		return this;
    	}
    }
    
    public static AbstractListNode merge(AbstractListNode a, AbstractListNode b) {
    	if (b.isEmpty()) {
    		return a;
    	} else if (a.isEmpty()) {
    		return b;
    	} else {
    		if (a.item().compareTo(b.item()) < 0) {
    			((NonemptyListNode) a).setNext(merge(a.next(), b));
    			return a;
    		} else {
    			((NonemptyListNode) b).setNext(merge(b.next(), a));
    			return b;
    		}
    	}
    }
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
    	int count = 0;
    	AbstractListNode currentNode = this;
    	while (!currentNode.isEmpty()) {
    		currentNode = currentNode.next();
    		count++;
    	}
    	return count;
    }
    
    public Comparable get(int pos) {
    	if (pos < 0) {
    		throw new IllegalArgumentException("Input is negative");
    	}
    	AbstractListNode currentNode = this;
    	for (int i = pos; i > 0; i --) {
    		currentNode = currentNode.next();
    	}
    	return currentNode.item();
    }
    
    public boolean equals(Object x) {
    	if (x instanceof NonemptyListNode) {
    		if (((NonemptyListNode) x).size() == this.size()) {
    			for (int i = 0; i < size(); i++) {
    				if (!get(i).equals(((NonemptyListNode) x).get(i))) {
    					return false;
    				}
    			}
    			return true;
    		} else {
    			return false;
    		}
    	} else {
    		return false;
    	}
    }
    
    public void setItem(Comparable newItem) {
    	myItem = newItem;
    }
    
    public void setNext(AbstractListNode newNext) {
    	myNext = newNext;
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
    
    public Comparable get(int pos) {
    	throw new IllegalArgumentException("Empty List");
    }
    
    public boolean equals(Object x) {
    	return (x instanceof EmptyListNode);
    }
}