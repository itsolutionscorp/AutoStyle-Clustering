import java.util.NoSuchElementException;

abstract public class AbstractListNode {
    abstract public void setNext(AbstractListNode newNext);
    abstract public void setItem(Comparable newItem);
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
	abstract public int size();
	abstract public Comparable get(int x);
	abstract public String toString();
    public String toString(boolean iscontinuation) {return toString();}
    abstract public AbstractListNode append(AbstractListNode list);
    abstract public AbstractListNode add(Comparable c);
	abstract public boolean equals(AbstractListNode compare);
    abstract public AbstractListNode reverse();
    abstract public AbstractListNode appendInPlace(AbstractListNode list2);

    public static AbstractListNode merge(AbstractListNode a,AbstractListNode b){
        if (a.isEmpty()) {
            return b;
        }
        if (b.isEmpty()) {
            return a;
        }
        if (a.item() == min(a.item(), b.item())) {
            a.setNext(merge(a.next(), b));
            return a;
        }
        b.setNext(merge(a, b.next()));
        return b;
    }

    // Fill this out

    // Every other list-processing method goes here.

    public Comparable smallest() {
      if (isEmpty()) {
        throw new NoSuchElementException("Can't find smallest in empty list.");
      }
      return this.next().smallestHelper(this.item());
    }

    public Comparable smallestHelper(Comparable smallestSoFar) {
        if (this.isEmpty()) {
            return smallestSoFar;
        }
        return this.next().smallestHelper(min(this.item(), smallestSoFar));
    }

    public static Comparable min(Comparable c1, Comparable c2) {
        if (c1.compareTo(c2) < 0) {
            return c1;
        } else {
            return c2;
        }
    }

}

class NonemptyListNode extends AbstractListNode {

    private Comparable myItem;
    private AbstractListNode myNext;

    public void setItem(Comparable newItem) {
        myItem = newItem;
    }

    public void setNext(AbstractListNode newNext) {
        myNext = newNext;
    }

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

	public Comparable get(int pos) {
		if (pos == 0) {
			return item();
		}
		return next().get(pos-1);
	}

	public String toString() {
		return "( " + toString(true);
	}

	public String toString(boolean continuation) {
		return item().toString() + " " + next().toString(true);
	}

	public boolean equals(AbstractListNode compare) {
		if (compare.isEmpty()) {
			return false;
		}
		if (item().equals(compare.item())) {
			return next().equals(compare.next());
		}
		return false;
	}

    public AbstractListNode add(Comparable c) {
        return new NonemptyListNode(this.item(), this.next().add(c));
    }

    public AbstractListNode append(AbstractListNode list) {
        return new NonemptyListNode(this.item(), this.next().append(list));
    }

    public AbstractListNode reverse() {
        if (this.next().isEmpty()) {
            return this;
        }
        return this.next().reverse().add(this.item());
    }

    public AbstractListNode appendInPlace(AbstractListNode list2) {
        this.setNext(this.next().appendInPlace(list2));
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
		return 1;
	}

	public Comparable get(int noref) {
		throw new IndexOutOfBoundsException("List index out of bounds");
	}

	public String toString() {
		return "( )";
	}

	public String toString(boolean iscontinuation) {
		return ")";
	}

	public boolean equals(AbstractListNode compare) {
		if (compare.isEmpty()) {
			return true;
		}
		return false;
	}

    public AbstractListNode add(Comparable c) {
        return new NonemptyListNode(c, this);
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

    public void setItem(Comparable newItem) {
        throw new IllegalArgumentException ("There is no 'item' value stored in an EmptyListNode.");
    }

    public void setNext(AbstractListNode newNext) {
        throw new IllegalArgumentException ("No elements follow an EmptyListNode.");
    }

}
