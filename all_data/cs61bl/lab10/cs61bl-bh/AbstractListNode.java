import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Comparable get(int index);
    abstract public AbstractListNode add(Comparable c);
    abstract public void setItem(Comparable item);
    abstract public void setNext(AbstractListNode next);

    public static Comparable min(Comparable c1, Comparable c2) {
    	if (c1.compareTo(c2) < 0) {
    		return c1;
    	} else {
    		return c2;
    	}
    }
    
    public Comparable smallest() {
    	if (isEmpty()) {
    		throw new NoSuchElementException("Can't find smallest in empty list");
    	}
    	Comparable result;
    	try {
    		result = min(item(), next().smallest());
    	} catch (NoSuchElementException e) {
    		result = item();
    	}
    	return result;
    }
    
    public AbstractListNode append(AbstractListNode list) {
    	AbstractListNode newList = new EmptyListNode();
    	for (int i = 0; i < size(); i++) {
    		newList = newList.add(get(i));
    	}
    	if (list.isEmpty()) return newList;
    	for (int i = 0; i < list.size(); i++) {
    		newList = newList.add(list.get(i));
    	}
    	return newList;
    }
    
    public AbstractListNode reverse() {
    	AbstractListNode newList = new EmptyListNode();
    	for (int i = size() - 1; i >= 0; i--) {
    		newList = newList.add(get(i));
    	}
    	return newList;
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2) {
    	if (isEmpty()) {
    		return list2;
    	}
    	this.setNext(this.next().appendInPlace(list2));
    	return this;
    }
    
    public static AbstractListNode merge(AbstractListNode a, AbstractListNode b) {
    	if (a.isEmpty()) return b;
    	else if (b.isEmpty()) return a; 
    	else if (a.item().compareTo(b.item()) > 0) {
    		b.setNext(merge(b.next(), a));
    		return b;
    	} else {
    		a.setNext(merge(a.next(), b));
    		return a;
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
    
    public Comparable get(int index) {
    	if (index == 0) {
    		return myItem;
    	} else {
    		return myNext.get(index-1);
    	}
    }
    
    public String toString() {
    	return "( " + myItem + myNext.toString().substring(1);
    }
    
    public boolean equals(Object arg) {
    	if (arg instanceof NonemptyListNode) {
    		NonemptyListNode n = (NonemptyListNode) arg;
    		return myItem.equals(n.myItem) && myNext.equals(n.myNext);
    	} else return false;
    }
    
    public AbstractListNode add(Comparable c) {
    	return new NonemptyListNode(myItem, myNext.add(c));
    }
    
    public void setItem(Comparable item) {
    	myItem = item;
    }
    
    public void setNext(AbstractListNode next) {
    	myNext = next;
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
    
    public Comparable get(int index) {
    	throw new IllegalArgumentException("index out of bounds");
    }
    
    public String toString() {
    	return "( )";
    }
    
    public boolean equals(Object arg) {
    	if (arg instanceof EmptyListNode) return true;
    	else return false;
    }
    
    public AbstractListNode add(Comparable c) {
    	return new NonemptyListNode(c, new EmptyListNode());
    }
    
    public void setItem(Comparable item) {
    	throw new IllegalArgumentException("EmptyListNodes cannot hold an item");
    }
    
    public void setNext(AbstractListNode next) {
    	throw new IllegalArgumentException("No element can follow an EmptyListNode");
    }
    
}
