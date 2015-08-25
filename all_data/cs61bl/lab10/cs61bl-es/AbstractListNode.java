import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    abstract public int size();
    abstract public Comparable get(int index);
    abstract public String toString();
    abstract public boolean equals(Object o);
    
    public Comparable smallest() throws NoSuchElementException {
    	if (isEmpty()) {
    		throw new NoSuchElementException("Can't find smallest in empty list.");
    	}
    	return this.next().smallestHelper(item());
    }

    public Comparable smallestHelper(Comparable smallestSoFar) {
    	if (this.isEmpty()) {
    		return smallestSoFar;
    	}
    	else {
    		return this.next().smallestHelper(min(smallestSoFar, item()));
    	}
    }

    public static Comparable min(Comparable c1, Comparable c2) {
    	if (c1.compareTo(c2) < 0) {
    		return c1;
    	}
    	else {
    		return c2;
    	}
    }
    
    public AbstractListNode add(Comparable c) {
    	AbstractListNode newList = new NonemptyListNode(c);
    	for (int i = this.size() - 1; i >= 0; i--) {
    		newList = new NonemptyListNode(this.get(i), newList);
    	}
    	return newList;
    }
    
    public AbstractListNode append(AbstractListNode list) {
    	AbstractListNode newList = this;
    	for (int i = 0; i < list.size(); i++) {
    		newList = newList.add(list.get(i));
    	}
    	return newList;
    }
    
    public AbstractListNode reverse() {
    	AbstractListNode newList = new EmptyListNode();
    	for (int i = this.size() - 1; i >= 0; i--) {
    		newList = newList.add(this.get(i));
    	}
    	return newList;
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2) {
    	if (this.isEmpty()) {
    		return list2;
    	}
    	else {
    		NonemptyListNode pointer = (NonemptyListNode) this;
    		for (int i = 0; i < this.size() - 1; i++) {
    			pointer = (NonemptyListNode) pointer.next();
    		}
    		pointer.setNext(list2);
    	}
    	return this;
    }
    
    public static AbstractListNode merge(AbstractListNode a, AbstractListNode b) {
    	if (a.isEmpty()) {
    		a = b;
    		return b;
    	}
    	else if (b.isEmpty()) {
    		b = a;
    		return a;
    	}
    	else if (min(a.item(), b.item()) == b.item()) {
    		((NonemptyListNode) b).setNext(merge(a, b.next()));
    		a = b;
    		return b;
    	}
    	else {
    		((NonemptyListNode) a).setNext(merge(a.next(), b));
    		b = a;
    		return a;
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
    	return 1 + next().size();
    }
    
    public Comparable get(int index) throws IllegalArgumentException {
    	if (index == 0) {
    		return item();
    	}
    	else if (index > 0 && next().isEmpty()) {
    		throw new IllegalArgumentException("Index out of bounds");
    	}
    	else {
    		return next().get(index - 1);
    	}
    }

    public String toString() {
    	String str = "( " + item() + " ";
    	AbstractListNode rest = next();
    	while (!rest.isEmpty()) {
    		str += rest.item() + " ";
    		rest = rest.next();
    	}
    	str += ")";
    	return str;
    }
    
    public boolean equals(Object o) {
    	return toString().equals(o.toString());
    }
    
    public void setItem(Comparable c) {
    	myItem = c;
    }
    
    public void setNext(AbstractListNode l) {
    	myNext = l;
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
    	return null;
    }
    
    public String toString() {
    	return "( )";
    }
    
    public boolean equals(Object o) {
    	return toString().equals(o.toString());
    }
}
