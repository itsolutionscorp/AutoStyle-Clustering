package lab10;

import java.util.NoSuchElementException;
//import AbstractListNode;
//import EmptyListNode;

abstract public class AbstractListNode {

	abstract public Comparable item();
	abstract public void setMyNext(AbstractListNode myNext);

	abstract public AbstractListNode next();

	abstract public boolean isEmpty();

	// Every other list-processing method goes here.
	abstract public String toString();

	abstract public AbstractListNode get(int i);

	abstract public int size();

	abstract public AbstractListNode add(Comparable c);

	abstract public AbstractListNode append(AbstractListNode list);

}

class NonemptyListNode extends AbstractListNode {

	private Comparable myItem;
	private AbstractListNode myNext;
	public AbstractListNode getMyNext() {
		return myNext;
	}

	public void setMyNext(AbstractListNode myNext) {
		this.myNext = myNext;
	}


	private static Comparable theMin;

	public NonemptyListNode(@SuppressWarnings("rawtypes") Comparable item,
			AbstractListNode next) {
		myItem = item;
		if (next.isEmpty()) {
			myNext = new EmptyListNode();
			theMin = item;
		} else {
			myNext = next;
			theMin = smallestHelper(item());
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

	public String toString() {
		String s = " )";
		AbstractListNode e = this;
		int c = 0;
		s = e.item().toString() + s;

		// while(e.next()!=null && c<e.size()){
		while (!e.next().isEmpty() && c < e.size()) {
			s = " " + s;
			c += 1;
			e = e.next();
			s = e.item() + s;
		}
		s = "( " + s;
		return s;

	}

	public AbstractListNode get(int i) {
		int counter = 0;

		if (this.size() <= i || i < 0) {
			throw new IllegalArgumentException("Go and ...");
		}

		AbstractListNode e = this;
		if (i == 0) {
			return e;
		} else {
			while (e.next().size() != 0) {
				counter += 1;
				e = e.next();
				if (counter == i) {
					return e;
				}
			}
		}
		return e;
	}

	public int size() {
		int count = 0;
		while (true) {
			if (this.next() != null) {
				if (count == 0) {
					count = this.next().size() + 1;
				} else {
					return count;
				}
			} else {
				return count;
			}

		}
	}

	public static void main(String[] s) {

	}

	public boolean equals(AbstractListNode e) {
		return this.toString().equals(e.toString());
	}

	/* Lab10 stuff: */
	public Comparable smallest() {
		if (isEmpty()) {
			throw new NoSuchElementException(
					"Can't find smallest in empty list.");
		}
		return theMin;
	}

	public Comparable smallestHelper(Comparable smallestSoFar) {
		if (isEmpty()) {
			return smallestSoFar;
		}
		System.out.println(smallestSoFar + "" + theMin);
		return min(smallestSoFar, theMin);
	}

	public static Comparable min(Comparable c1, Comparable c2) {
		if (c1.compareTo(c2) < 0) {

			return c1;
		} else {
			return c2;
		}
	}

	public AbstractListNode add(Comparable c) {
		NonemptyListNode a = new NonemptyListNode(c, this);
		return a;
	}

	
	public AbstractListNode append(AbstractListNode list) {
		AbstractListNode b =  list;
		AbstractListNode a =  list;
		while (true) {
			b= b.next();
			if (b.size()==1){
				b.setMyNext(this);
				return b;
				}
			}
		}
		
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

	public String toString() {
		return "()";
	}

	public EmptyListNode get(int i) {
		return this;
	}

	public int size() {
		return 0;
	}

	public boolean equals(EmptyListNode e) {
		return this.toString().equals(e.toString());
	}

	public AbstractListNode add(Comparable a) {
		return new NonemptyListNode(a);
	}
	
	public AbstractListNode append(AbstractListNode list) {
		return list;
		
	}

	@Override
	public void setMyNext(AbstractListNode myNext) {
		// TODO Auto-generated method stub
		
	}

}