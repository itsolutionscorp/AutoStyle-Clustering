import java.util.NoSuchElementException;

abstract public class AbstractListNode implements Comparable {

	abstract public Comparable item();

	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	abstract public AbstractListNode next();

	abstract public boolean isEmpty();

	abstract public int size();

	abstract public Comparable get(int pos);

	abstract public String toString();

	abstract public boolean equals(Object list);

	abstract public AbstractListNode add(Comparable c);

	abstract public AbstractListNode append(AbstractListNode list);

	abstract public AbstractListNode reverse();

	abstract public AbstractListNode appendInPlace(AbstractListNode list2);

	// Every other list-processing method goes here.

	public Comparable smallest() {
		if (isEmpty()) {
			throw new NoSuchElementException(
					"Can't find smallest in empty list.");
		}
		return smallestHelper(item());
	}

	public Comparable smallestHelper(Comparable smallestSoFar) {
		for (int i = 0; i < this.size(); i++) {
			smallestSoFar = min(smallestSoFar, this.get(i));
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

	public static AbstractListNode merge(AbstractListNode a, AbstractListNode b) {
		if (b.isEmpty())
			return a;
		if (a.isEmpty())
			return b;
		if (a.item().compareTo(b.item()) <= 0) {
			((NonemptyListNode) a).setNext(merge(a.next(), b));
			return a;
		} else if (a.item().compareTo(b.item()) > 0) {
			((NonemptyListNode) b).setNext(merge(a, b.next()));
			return b;
		}
		return a;
	}

}

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

	@Override
	public Comparable item() {
		return myItem;
	}

	@Override
	public AbstractListNode next() {
		return myNext;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public int size() {
		if (!this.isEmpty())
			return 1 + this.myNext.size();
		return 0;
	}

	@Override
	public Comparable get(int pos) throws IllegalArgumentException {
		if (pos < 0 || pos > this.size() - 1) {
			throw new IllegalArgumentException("Enter a valid position.");
		}
		while (pos > 0) {
			return this.myNext.get(pos - 1);
		}
		return this.myItem;
	}

	@Override
	public String toString() {
		String s = "( ";
		for (int i = 0; i < this.size(); i++) {
			if (get(i) == null)
				s += "";
			else
				s += get(i) + " ";
		}
		return s + ")";
	}

	@Override
	public boolean equals(Object list) {
		if (((AbstractListNode) list).size() != this.size())
			return false;
		for (int i = 0; i < this.size(); i++) {
			if (((AbstractListNode) list).get(i) != this.get(i)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AbstractListNode add(Comparable c) {
		return new NonemptyListNode(this.item(), this.next().add(c));
	}

	@Override
	public AbstractListNode append(AbstractListNode list) {
		AbstractListNode appendedList = this;
		for (int i = 0; i < list.size(); i++) {
			appendedList = appendedList.add(list.get(i));
		}
		return appendedList;
	}

	@Override
	public AbstractListNode reverse() {
		if (this.isEmpty())
			return null;
		AbstractListNode reversed = new NonemptyListNode(
				this.get(this.size() - 1));
		for (int i = 0; i < this.size() - 1; i++) {
			reversed = reversed.add(this.get(this.size() - 2 - i));
		}
		return reversed;
	}

	@Override
	public AbstractListNode appendInPlace(AbstractListNode list2) {
		if (this.isEmpty()) {
			return list2;
		}
		if (this.next().isEmpty()) {
			this.setNext(list2);
			return this;
		}
		return this.next().appendInPlace(list2);
	}

	public void setNext(AbstractListNode next) {
		myNext = next;
	}

	public void setItem(Comparable item) {
		myItem = item;
	}

}

class EmptyListNode extends AbstractListNode {

	public EmptyListNode() {

	}

	@Override
	public Comparable item() {
		throw new IllegalArgumentException(
				"There is no 'item' value stored in an EmptyListNode.");
	}

	@Override
	public AbstractListNode next() {
		throw new IllegalArgumentException(
				"No elements follow an EmptyListNode.");
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public Comparable get(int pos) {
		return null;
	}

	@Override
	public String toString() {
		return "( )";
	}

	@Override
	public boolean equals(Object list) {
		if (((AbstractListNode) list).isEmpty())
			return true;
		return false;
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}

	@Override
	public AbstractListNode add(Comparable c) {
		return new NonemptyListNode(c);
	}

	@Override
	public AbstractListNode append(AbstractListNode list) {
		return null;
	}

	@Override
	public AbstractListNode reverse() {
		return null;
	}

	@Override
	public AbstractListNode appendInPlace(AbstractListNode list2) {
		return list2;
	}

}
