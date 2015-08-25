import java.util.NoSuchElementException;
abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Comparable get(int pos);
    abstract public String toString();
    abstract public boolean equals(AbstractListNode node);
    abstract public Comparable smallestHelper(Comparable smallestSoFar);
    abstract public AbstractListNode add(Comparable c);
    abstract public void setItem(Comparable item);
    abstract public void setNext(AbstractListNode next);
    abstract public AbstractListNode appendInPlace(AbstractListNode list2);
    public Comparable smallest() {
    	  if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	  }
    	  return smallestHelper(item());
    	}
    public static Comparable min(Comparable c1, Comparable c2) {
    	if (c1.compareTo(c2) < 0) {
    		return c1;
    	} else {
    		return c2;
    	}
    }
    public AbstractListNode append(AbstractListNode list) {
    	AbstractListNode newList = new EmptyListNode();
    	for (int i = 0; i < size(); i++)
    		newList = newList.add(get(i));
    	for (int i = 0; i < list.size(); i++)
    		newList = newList.add(list.get(i));
    	return newList;
    }
    public AbstractListNode reverse() {
    	AbstractListNode list = new EmptyListNode();
    	for (int i = size() - 1; i >= 0; i--) {
    		list = list.add(get(i));
    	}
    	return list;
    }
    public static AbstractListNode merge(AbstractListNode a, AbstractListNode b) {
    	AbstractListNode c = a;
    	AbstractListNode prevA = a;
    	while (a.size() > 0 && b.size() > 0) {			
    		if (min(a.item(), b.item()).equals(b.item())) {
    			if (a.size() == c.size()) {
    				prevA = b;
    				c = b;
    				b = b.next();
    				prevA.setNext(a);
    			}
    			else {
    				prevA.setNext(b);
    				prevA = b;
    				b = b.next();
    				prevA.setNext(a);
    			}
    		}
    		else {
    			if (a.size() != c.size()) {
    				prevA = prevA.next();
    			}
    			a = a.next();
    		}
    	}
    	if (a.size() == 0) {
    		c = c.appendInPlace(b);
    	}
    	return c;
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
    	return 1 + myNext.size();
    }

    public Comparable get(int pos) {
    	if (pos == 0) return myItem;
    	return myNext.get(pos - 1);
    }
    
    public String toString() {
    	String s = myNext.toString();
    	return s.charAt(0) + " " + myItem.toString() + s.substring(1);
    }
    
    public boolean equals(AbstractListNode node) {
    	return !node.isEmpty() && myItem.equals(node.item()) && myNext.equals(node.next());
    }
    
    public Comparable smallestHelper(Comparable smallestSoFar) {
    	return myNext.smallestHelper(min(smallestSoFar, myItem));
    }
    
    public AbstractListNode add(Comparable c) {
    	 AbstractListNode lastNode = new NonemptyListNode(c, new EmptyListNode());
    	for (int i = size() - 1; i >= 0; i--)
    		lastNode = new NonemptyListNode(get(i), lastNode);
    	return lastNode;
    }
    public void setItem(Comparable item) {
    	myItem = item;
    }
    public void setNext(AbstractListNode next) {
    	myNext = next;
    }
    public AbstractListNode appendInPlace(AbstractListNode list2) {
    	AbstractListNode node = this;
    	for (int i = 0; i < size() - 1; i++)
    		node = node.next();
    	node.setNext(list2);
    	return this;
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
    	throw new IllegalArgumentException();
    }
    
    public String toString() {
    	return "( )";
    }
    
    public boolean equals(AbstractListNode node) {
    	return node.isEmpty();
    }
    
    public Comparable smallestHelper(Comparable smallestSoFar) {
    	return smallestSoFar;
    }
    
    public AbstractListNode add(Comparable c) {
    	return new NonemptyListNode(c, new EmptyListNode());
    }
    public void setItem(Comparable item) {
        throw new IllegalArgumentException ("There is no 'item' value stored in an EmptyListNode.");
    }
    public void setNext(AbstractListNode next) {
        throw new IllegalArgumentException ("No elements follow an EmptyListNode.");
    }
    public AbstractListNode appendInPlace(AbstractListNode list2) {
    	return list2;
    }
}
