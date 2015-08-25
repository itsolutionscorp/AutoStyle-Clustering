import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;


abstract public class AbstractListNode implements Comparable {

	abstract public Comparable item();

	abstract public AbstractListNode next();

	abstract public boolean isEmpty();

	// Every other list-processing method goes here.
	abstract public int size();

	abstract public AbstractListNode add(Comparable c);

	abstract public AbstractListNode append(AbstractListNode list);

	abstract public AbstractListNode reverse();

	public static AbstractListNode merge(AbstractListNode a, AbstractListNode b) {

		if (a.isEmpty()) {
			return b;
		} else if (b.isEmpty()) {
			return a;
		}

		if (min(a.item(), b.item()) == a.item()) {
			a.setNext(merge(a.next(), b));
			return a;
		} else {
			b.setNext(merge(a, b.next()));
			return b;
		}
	}

	public AbstractListNode appendInPlace(AbstractListNode list2) {
		if (this.isEmpty()) {
			return list2;
		} else {
			AbstractListNode pointer = this;
			while (!pointer.next().isEmpty()) {
				pointer = pointer.next();
			}
			pointer.setNext(list2);
			return this;
		}
	}

	abstract public void setItem(Comparable c);

	abstract public void setNext(AbstractListNode list);

	public Comparable get(int position) {
		if (position == 0) {
			return item();
		} else {
			return next().get(position - 1);
		}
	}

	public String toString() {
		String result = "( ";
		AbstractListNode pointer = this;
		while (!pointer.isEmpty()) {
			result += pointer.item() + " ";
			pointer = pointer.next();
		}
		return result + ")";

	}

	@Override
	public boolean equals(Object list) {
		if (this.toString().equals(list.toString())) {
			return true;
		}
		return false;
	}

	public Comparable smallest() {
		if (isEmpty()) {
			throw new NoSuchElementException(
					"Can't find smallest in empty list.");
		}
		return smallestHelper(this.item());
	}

	public Comparable smallestHelper(Comparable smallestSoFar) {
		if (next().isEmpty()) {
			return smallestSoFar;
		}
		return min(smallestSoFar, next().smallest());

	}

	public static Comparable min(Comparable c1, Comparable c2) {
		if (c1.compareTo(c2) < 0) {
			return c1;
		} else {
			return c2;
		}
	}

	public int compareTo(Object o) {
		return this.item().compareTo(o);
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
		return 1 + this.next().size();
	}

	public AbstractListNode add(Comparable c) {
		NonemptyListNode pointer = this;
		NonemptyListNode head = new NonemptyListNode(pointer.item(),
				pointer.next());
		NonemptyListNode copy = head;

		while (!pointer.myNext.isEmpty()) {
			pointer = (NonemptyListNode) pointer.myNext;
			copy.myNext = new NonemptyListNode(pointer.item(), pointer.next());
			copy = (NonemptyListNode) copy.myNext;

		}
		copy.myNext = new NonemptyListNode(c, new EmptyListNode());
		return head;
	}

	public AbstractListNode append(AbstractListNode list) {
		AbstractListNode pointer = this;
		NonemptyListNode copy = new NonemptyListNode(pointer.item(),
				pointer.next());
		NonemptyListNode head = copy;

		while (!pointer.next().isEmpty()) {
			pointer = pointer.next();
			copy.myNext = new NonemptyListNode(pointer.item(), pointer.next());
			copy = (NonemptyListNode) copy.next();
		}

		copy.myNext = list;
		return head;
	}

	public AbstractListNode reverse() {
		AbstractListNode pointer = this;
		AbstractListNode result = new NonemptyListNode(pointer.item(),
				new EmptyListNode());
		while (!pointer.next().isEmpty()) {
			pointer = pointer.next();
			result = new NonemptyListNode(pointer.item(), result);
		}
		return result;

	}

	public void setNext(AbstractListNode list) {
		this.myNext = list;

	}

	public void setItem(Comparable c) {
		this.myItem = c;
	}

	// ******TESTING******
	// public static void main(String[] args) {
	// AbstractListNode list1 = new NonemptyListNode(2, null);
	// list1.appendInPlace(new NonemptyListNode(4, null));
	// list1.appendInPlace(new NonemptyListNode(8, null));
	// list1.appendInPlace(new NonemptyListNode(9, null));
	//
	// AbstractListNode list2 = new NonemptyListNode(1, null);
	// list2.appendInPlace(new NonemptyListNode(2, null));
	// list2.appendInPlace(new NonemptyListNode(3, null));
	// list2.appendInPlace(new NonemptyListNode(6, null));
	//
	// AbstractListNode result = merge(list1, list2);
	// System.out.println(result.toString());
	// }
	// *****TESTING********

}

class EmptyListNode extends AbstractListNode {

	public EmptyListNode() {

	}

	public Comparable item() {
		throw new IllegalArgumentException(
				"There is no 'item' value stored in an EmptyListNode.");
	}

	public AbstractListNode next() {
		throw new IllegalArgumentException(
				"No elements follow an EmptyListNode.");
	}

	public boolean isEmpty() {
		return true;
	}

	public int size() {
		return 0;
	}

	public AbstractListNode append(AbstractListNode list) {
		AbstractListNode pointer = list;
		NonemptyListNode copy = new NonemptyListNode(pointer.item(),
				pointer.next());
		NonemptyListNode head = copy;

		while (!pointer.next().isEmpty()) {
			pointer = pointer.next();
			copy.add(pointer.item());
		}
		return head;
	}

	public AbstractListNode reverse() {
		return new EmptyListNode();
	}

	public void setNext(AbstractListNode list) {
	}

	public void setItem(Comparable c) {
	}

	@Override
	public AbstractListNode add(Comparable c) {
		return new NonemptyListNode(c, new EmptyListNode());
	}

}
