import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();    
    abstract public Comparable get(int position);
    abstract public String toString();
    abstract public int size();
    
    abstract public AbstractListNode add(Comparable c);
    abstract public AbstractListNode append(AbstractListNode list);
    abstract public AbstractListNode reverse();
    abstract public AbstractListNode appendInPlace(AbstractListNode list2);
    
    abstract public void setItem(Object o);
    abstract public void setNext(AbstractListNode a);

    public boolean equals(Comparable obj) {
		String s1 = this.toString();
		String s2 = obj.toString();
		return s1.equals(s2);
	}

    public Comparable smallest() {
    	if (this.isEmpty()) {
    		throw new NoSuchElementException("Can't find smallest in empty list.");
    	}
    	return smallestHelper(this.item());
    }

    public Comparable smallestHelper(Comparable smallestSoFar) {
    	AbstractListNode nextOne = this;
    	while (!(nextOne.isEmpty())) {
    		smallestSoFar = min(smallestSoFar, nextOne.item());
    		nextOne = nextOne.next();
    	}
    	return smallestSoFar;
    }

    public static Comparable min(Comparable c1, Comparable c2) {
    	if (c1.compareTo(c2) < 0) {
    		return c1;
    	} else {
    		return c2;
    	}
    }
    
    public static AbstractListNode merge(AbstractListNode l, AbstractListNode r) {
    	if (l.isEmpty()) {
    		return r;
    	} else if (r.isEmpty()) {
    		return l;
    	} else if (min(l.smallest(), r.smallest()) == l.smallest()) {
    		l.setNext(merge(l.next(), r));
    		return l;
    	} else {
    		r.setNext(merge(l, r.next()));
    		return r;
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
    	if (myNext.isEmpty()) {
			return 1;
		}
		return myNext.size() + 1;
    }
    
    public Comparable get(int position) {
    	int count = 0;
    	AbstractListNode nextNode = this;
    	while (count < position) {
    		if (nextNode instanceof EmptyListNode) {
    			nextNode.item();
    		}
    		nextNode = nextNode.next();
    		myItem = nextNode.item();
    		count++;
    	}
    	return myItem;
    }
    
    public String toString() {
    	String rep = "( ";
    	AbstractListNode nextNode = this;
    	while (!(nextNode.isEmpty())) {
    		rep += nextNode.item() + " ";
    		nextNode = nextNode.next();
    	}
    	rep += ")";
		return rep;
    }
    
    public AbstractListNode add (Comparable c) {
    	NonemptyListNode copy = addCopy(this, c);
    	return copy;
    }
    
    public static NonemptyListNode addCopy(AbstractListNode a, Comparable c) {
    	if (a.isEmpty()) {
    		return new NonemptyListNode(c);
    	} else {
    		return new NonemptyListNode(a.item(), addCopy(a.next(), c));
    	}
    }
    
    public AbstractListNode append(AbstractListNode list) {
    	AbstractListNode copy = appendCopy(this, list);
    	return copy;
    }
    
    public AbstractListNode appendCopy(AbstractListNode a, AbstractListNode b) {
    	if (a.isEmpty()) {
    		return b;
    	} else {
    		return new NonemptyListNode(a.item(), appendCopy(a.next(), b));
    	}
    }
    
    public AbstractListNode reverse() {
    	AbstractListNode copy = revCopy(this);
    	return copy;
    }
    
    public AbstractListNode revCopy(AbstractListNode a) {
    	AbstractListNode end = new NonemptyListNode(a.item());
    	AbstractListNode rev = a.next();
    	while (!(rev.isEmpty())) {
    		end = new NonemptyListNode(rev.item(), end);
    		rev = rev.next();
    	}
    	return end;
	}
    
    public AbstractListNode appendInPlace(AbstractListNode list2) {
		NonemptyListNode n = this;
		while(n.myNext.isEmpty() == false) {
			n = (NonemptyListNode) n.myNext; 
		}
		n.myNext = list2;  
		return this;  
	}
    
    public void setItem(Object o) {
    	myItem = (Comparable) o;
    }
    
    public void setNext(AbstractListNode a) {
    	myNext = a;
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
    
    public Comparable get(int position) {
    	return this.item();
    }

    public String toString() {
    	return "( )";
    }
    
    public AbstractListNode add (Comparable c) {
    	return new NonemptyListNode(c);
    }
    
    public AbstractListNode append(AbstractListNode list) {
    	return list;
    }
    
    public AbstractListNode reverse() {
    	return this;
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2) {
		return list2;
	}
    
    public void setItem(Object o) {
    	item();
    }
    
    public void setNext(AbstractListNode a) {
    	next();
    }
    
}
