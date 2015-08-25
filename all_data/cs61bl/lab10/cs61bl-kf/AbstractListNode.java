import java.util.NoSuchElementException;

abstract public class AbstractListNode {

	abstract public Comparable item();
	abstract public AbstractListNode next();
	abstract public boolean isEmpty();

	// Every other list-processing method goes here.
	abstract public int size();
	abstract public Comparable get(int pos);
	abstract public String toString();
	abstract public boolean equals(Comparable arg);	
	abstract public AbstractListNode append(AbstractListNode list);
	abstract public AbstractListNode appendInPlace(AbstractListNode list2);

	abstract public AbstractListNode setItem(AbstractListNode newItem);
	abstract public AbstractListNode setNext(AbstractListNode newNext);
	abstract public AbstractListNode reverse();


	protected Comparable smallestYet;

	abstract public AbstractListNode add(Comparable c);

	public Comparable smallest() {
		if (isEmpty()) {
			throw new NoSuchElementException("Can't find smallest in empty list.");
		}

		int counter = 1;

		while (counter < this.size()) {
			smallestYet = this.smallestHelper(this.get(counter));
			counter ++;
		}
		return smallestYet;
	}

	public Comparable smallestHelper(Comparable smallestSoFar) {
		return min(smallestSoFar, smallestYet);

	}

	public static Comparable min(Comparable c1, Comparable c2) {
		if (c1.compareTo(c2) < 0) {
			return c1;
		} else {
			return c2;
		}
	}

	public static AbstractListNode merge(AbstractListNode a, AbstractListNode b ) {
		if (a.isEmpty()) {
			return b;
		}
		else if (b.isEmpty()) {
			return a;
		}
		else if ((int) a.get(0) < (int) b.get(0)) {
			return a.setNext(merge(a.next(), b));

		}
		else if ((int) b.get(0) < (int) a.get(0)) {
			return b.setNext(merge(b.next(), a));
		}

		else {
			return a.setNext(merge(a.next(), b));
		}
	}
}

class NonemptyListNode extends AbstractListNode {

	private Comparable myItem;
	private AbstractListNode myNext;

	public NonemptyListNode (Comparable item, AbstractListNode next) {
		myItem = item;
		smallestYet = item;
		if (next == null) {
			myNext = new EmptyListNode();
		} else {
			myNext = next;
		}
	}

	public AbstractListNode reverse() {
		return reverseHelper(this, this.size() - 1);
	}

	public static NonemptyListNode reverseHelper (NonemptyListNode head, int index) {
		if (index == 0) {
			return new NonemptyListNode(head.get(0));
		} else {
			return new NonemptyListNode(head.get(index), reverseHelper(head, index - 1));
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
		if (this.isEmpty()) {
			return 0;
		}
		else {
			return 1 + this.next().size(); 
		}
	}

	public Comparable get(int pos) {
		if (pos == 0) {
			return this.item();
		}
		else if (this.isEmpty() && pos > 0) {
			throw new IllegalArgumentException("out of range");
		}
		else {
			return this.next().get(pos-1);
		}

	}

	public String toString() {
		String total = "( ";
		int counter = 0;
		while (counter < this.size()) {
			total += this.get(counter) + " ";
			counter ++;
		}
		return total + ")";
	}

	public boolean equals(Comparable arg) {
		if (arg instanceof NonemptyListNode) {
			NonemptyListNode node = (NonemptyListNode) arg;
			int counter = 0;
			if (this.size() != node.size()) {
				return false;
			}
			while (counter < this.size()) {
				if (this.get(counter) != node.get(counter)) {
					return false;
				}
				counter ++;
			} 
			return true;
		} else {
			return false;
		}

	}


	public AbstractListNode add(Comparable c) {		
		if (this.isEmpty()) {
			return new NonemptyListNode(c);
		} else {
			return new NonemptyListNode(this.item(), this.next().add(c));
		}
	}


	public AbstractListNode append(AbstractListNode list) {
		AbstractListNode returnList = this;
		for (int i = 0; i < list.size(); i++) {
			returnList = returnList.add(list.get(i));
		}
		return returnList;
	}

	public AbstractListNode setItem(AbstractListNode newItem) {
		this.myItem = (Comparable) newItem;
		return this;
	}

	public AbstractListNode setNext(AbstractListNode newNext) {
		this.myNext = newNext;
		return this;
	}

	public AbstractListNode appendInPlace(AbstractListNode list2){
		if (this.isEmpty()) {
			return list2;
		}
		else {
			AbstractListNode temp = this;
			for (int i = 0; i < this.size() - 1; i ++) {
				temp = temp.next();
			}
			temp.setNext(list2);
			return temp;

		}
	}
}

class EmptyListNode extends AbstractListNode {

	public EmptyListNode() {

	}

	public AbstractListNode appendInPlace(AbstractListNode list2){
		return list2;
	}

	public AbstractListNode reverse() {
		return this;
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
		return null;
	}
	public String toString() {
		return "( )";
	}
	public boolean equals(Comparable arg) {
		if (arg instanceof EmptyListNode) {
			return true;
		} else {
			return false;
		}
	}

	public AbstractListNode add(Comparable c) {
		return new NonemptyListNode(c);
	}

	public AbstractListNode append(AbstractListNode list) {
		return list;
	}

	public AbstractListNode setItem(AbstractListNode newItem) {
		return new EmptyListNode();
	}

	public AbstractListNode setNext(AbstractListNode newNext) {
		return new EmptyListNode();
	}




}

