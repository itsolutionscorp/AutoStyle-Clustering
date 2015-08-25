package lab10;
import java.util.*;
abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    
    public AbstractListNode add(Comparable c) {
    	if (!isEmpty()) {
    		return new NonemptyListNode(item(), next().add(c));
    	} else {
    		return new NonemptyListNode(c);
    	}
    }
    
    public int size() {
    	if (this.isEmpty()){
    		return 0;
    	} else {
    		return this.next().size() + 1;
    	}
    }
    
    public Comparable get(int pos) {
    	if (this.isEmpty()) {
    		throw new IllegalArgumentException("This is not the position we are looking for.");
    	} else if (pos == 0) {
    		return item();
    	} else {
    		return this.next().get(pos - 1);
    	}
    }
    
    public String toString() {
    	String result = "( ";
    	AbstractListNode list = this;
    	while (!list.isEmpty()) {
    		result += list.get(0) + " ";
    		list = list.next();
    	}
    	result += ")";
    	return result;
    }
    
    public boolean equals(Object list) {
    	if (this.size() != ((AbstractListNode) list).size()) {
    		return false;
    	}
    	for (int i = 0; i < this.size(); i++) {
    		if(this.get(i) != ((AbstractListNode) list).get(i)) {
    			return false;
    		}
    	}
    	return true;
    }
    
    public Comparable smallest() {
    	if (isEmpty()) {
    		throw new NoSuchElementException("There is no smallest.");
    	}
    	return smallestHelper(item());
    }
    
    public Comparable smallestHelper(Comparable smallestSoFar) {
    	if(!(next().isEmpty())){
    		return next().smallestHelper(min(smallestSoFar, next().item()));
    	}
    	return smallestSoFar;
    }
    
    public static Comparable min(Comparable c1, Comparable c2) {
    	if(!(c1.compareTo(c2) < 0)) {
    		return c2;
    	} else {
    		return c1;
    	}
    }
    
    public AbstractListNode append(AbstractListNode list) {
    	if(!isEmpty()) {
    		return new NonemptyListNode(item(), next().append(list));
    	} else {
    		return list;
    	}
    }
    
    public AbstractListNode reverse() {
    	AbstractListNode result = new NonemptyListNode(lastVal(size()));
    	for(int i = size() - 1; i > 0; i--) {
    		result = result.add(lastVal(i));
    	}
    	return result;
    }
    
    public Comparable lastVal(int node) {
    	if (!next().isEmpty() && node > 1) {
    		return next().lastVal(node - 1);
    	} else {
    		return item();
    	}
    }
    
    public AbstractListNode appendInPlace (AbstractListNode list2) {
    	if (this.isEmpty()) {
    		return list2;
    	} else {
    		AbstractListNode current = this;
    		AbstractListNode future = this.next();
    		while (!current.isEmpty()) {
    			current = current.next();
    			future = future.next();
    		}
    		((NonemptyListNode) current).setNext(list2);
    		return this;
    	}
    }
    
    public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
        if (a.isEmpty()) {
        	return b;
        }
        else if (b.isEmpty()) {
        	return a;
        }
        else {
        	if (a.item().compareTo(b.item()) < 0 || a.item().compareTo(b.item()) == 0) {
        		((NonemptyListNode) a).setNext(merge(a.next(), b));
        	}
        	else {
        		((NonemptyListNode) b).setNext(merge(b.next(), a));
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
    	while(myNext != null) {
    		count++;
    	}
    	return count;
    }
    
    public void setItem(Comparable item) {
    	myItem = item;
    }
    
    public void setNext(AbstractListNode node) {
    	myNext = node;
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

}
