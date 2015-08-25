import java.util.NoSuchElementException;

abstract public class AbstractListNode {

	abstract public Comparable item();

	abstract public AbstractListNode next();

	abstract public boolean isEmpty();

	abstract public int size();

	abstract public Comparable get(int a);

	abstract public boolean equals(Object b);

	abstract public static Comparable min(Comparable c1, Comparable c2);

	abstract public Comparable smallest();

	abstract public Comparable smallestHelper(Comparable smallestSoFar);

	abstract public AbstractListNode add(Comparable c);

	abstract public NonemptyListNode getLast();

	abstract public AbstractListNode append(AbstractListNode list);
	
	abstract public AbstractListNode reverse();

	}

//	 abstract public AbstractListNode appendInPlace(AbstractListNode list2);

class NonemptyListNode extends AbstractListNode {

	private Comparable myItem;
	private AbstractListNode myNext;

	public NonemptyListNode(Comparable item, AbstractListNode next) {
		myItem = item;
		if (next == null) {
			myNext = new EmptyListNode();
		} else {
			myNext = next;
		}
	}

	public NonemptyListNode(Comparable item) {
		this(item, new EmptyListNode());
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
		if (this.isEmpty() == true) { // base case
			return this.size();
		} else {
			return 1 + this.next().size();
		}
	}

	public Comparable get(int a) {
		if (a > size()) {
			return null;
		} else if (a == 0) { // base case
			return this.item();
		} else {
			return this.next().get(a - 1);
		}
	}

	public String toString() {
		String temp = "(";
		for (int i = 0; i < size(); i++) {
			temp = temp + " " + get(i);
		}
		return temp + " )";
	}

	public boolean equals(Object b) {
		boolean temp = true;
		if (((AbstractListNode) b).size() != this.size()) {
			return false;
		}
		for (int i = 0; i < size(); i++) {
			if (((AbstractListNode) b).get(i) != get(i)) {
				temp = false;
				break;
			}
		}
		return temp;
	}

	public Comparable smallest() {
		if (isEmpty()) {
			throw new NoSuchElementException("Can't find smallest in empty list.");
		}
		return smallestHelper(item());
	}

	public Comparable smallestHelper(Comparable smallestSoFar) {
		if (isEmpty()) {
			return smallestSoFar;
		} else {
			return this.next().smallestHelper(min(smallestSoFar, this.item()));
		}
	}

	public static Comparable min(Comparable c1, Comparable c2) {
		if (c1.compareTo(c2) < 0) {
			return c1;
		} else {
			return c2;
		}
	}

	public NonemptyListNode getLast() {
		if (this.next().isEmpty()) {
			return this;
		} else {
			return this.next().getLast();
		}
	}

	public AbstractListNode add(Comparable c) {
		AbstractListNode temp = new NonemptyListNode(myItem, myNext.add(c));
		return temp;
	}

	public AbstractListNode append(AbstractListNode list) {
		AbstractListNode app = new NonemptyListNode(myItem, myNext.append(list));
		return app;
	}

	public AbstractListNode reverse() {
		AbstractListNode temp = new NonemptyListNode(get(size() - 1));
		for (int i = size() - 2; i >= 0; i--) {
			temp = temp.add(this.get(i));
		}
		return temp;
	}

}

class EmptyListNode extends AbstractListNode {

	public EmptyListNode() {

	}

	public Comparable item() {
		throw new IllegalArgumentException("There is no 'item' value stored in an EmptyListNode.");
	}

	public AbstractListNode next() {
		throw new IllegalArgumentException("No elements follow an EmptyListNode.");
	}

	public boolean isEmpty() {
		return true;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Comparable get(int a) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object b) {
		// TODO Auto-generated method stub
		return false;
	}

	public static Comparable min(Comparable c1, Comparable c2) {
		if (c1.compareTo(c2) < 0) {
			return c1;
		} else {
			return c2;
		}
	}

	@Override
	public Comparable smallest() {
		if (isEmpty()) {
			throw new NoSuchElementException("Can't find smallest in empty list.");
		}
		return smallestHelper(item());
	}

	@Override
	public Comparable smallestHelper(Comparable smallestSoFar) {
		if (isEmpty()) {
			return smallestSoFar;
		} else {
			return this.next().smallestHelper(min(smallestSoFar, this.item()));
		}
	}

	@Override
	public AbstractListNode add(Comparable c) {
		AbstractListNode temp = new NonemptyListNode(c);
		return temp;
	}

	@Override
	public NonemptyListNode getLast() {
		return null;
	}

	@Override
	public AbstractListNode append(AbstractListNode list) {
		return list;
	}
	
	public AbstractListNode reverse() {
		return null;
	}

}