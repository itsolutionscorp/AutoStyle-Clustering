import java.util.NoSuchElementException;

abstract public class AbstractListNode {

    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Comparable get(int pos);
    abstract public String toString();
    abstract public boolean equals(AbstractListNode lst);
    abstract void setItem(Comparable item);
    abstract void setNext(AbstractListNode next);
    
    public AbstractListNode add (Comparable c) {
    	AbstractListNode add = new NonemptyListNode(c);
    	AbstractListNode result = new NonemptyListNode(item(), add);
    	for (int i = 0; i < size() - 1; i++) {
        	result = new NonemptyListNode(next().item(), result);
    	}
    	return result;
    }
    
    public AbstractListNode append(AbstractListNode list) {
    	if (this.isEmpty())
    		return list;
    	AbstractListNode result = next().append(list);
    	return new NonemptyListNode(item(), result);
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2) { 
    	if (this.isEmpty())
    		return list2;
    	else if (next().isEmpty())
    		setNext(list2);
    	else
    		next().appendInPlace(list2);
    	return this;
    }
    
    public static AbstractListNode merge(AbstractListNode a, AbstractListNode b) {
    	if (a.isEmpty())
    		return b;
    	else if (b.isEmpty())
    		return a;
    	
    	Comparable small = min(a.item(), b.item());

    	if (a.item() == small) {
    		AbstractListNode temp = a.next();
    		a.setNext(b); 
    		b.next().merge(temp, a.next());
    		return a;
    	} else {
    		AbstractListNode temp = b.next();
    		b.setNext(a);
    		a.next().merge(b.next(), temp);
    		return b;
    	}

    }

    public Comparable smallest() {
        if (isEmpty()) {
            throw new NoSuchElementException ("can't find smallest in empty list");
        }
        return smallestHelper(next(), item());
    	}

    public static Comparable smallestHelper(AbstractListNode list, Comparable smallestSoFar) {
        if (list.isEmpty()) {
            return smallestSoFar;
        } else {
            return smallestHelper(list.next(), min(smallestSoFar, list.item()));
        }
    }

    public static Comparable min(Comparable c1, Comparable c2) {
    	if (c1.compareTo(c2) < 0) {
    		return c1;
    	} else {
    		return c2;
    		}
    }
}
