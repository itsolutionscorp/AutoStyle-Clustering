import java.util.NoSuchElementException;

abstract public class AbstractListNode {

	abstract public Comparable item();
	abstract public AbstractListNode next();
	abstract public boolean isEmpty();
	abstract public int size();
	public static int size;
	abstract public Comparable get(int index);
	abstract public String toString();
	abstract public boolean equals(Comparable x);

	public Comparable smallest() {
		if (isEmpty()) {
			throw new NoSuchElementException("Can't find smallest in empty list.");
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

	public AbstractListNode add (Comparable c){

		if(myItem.next().isEmpty()){

			return new NonEmptyListNode(myItem, c);

		} else if(! myItem.next().isEmpty()){

			return new NonEmptyListNode(myItem, add(next()));

		}

	}



	public AbstractListNode append(AbstractListNode list) {

		return appendHelper(this, list);

	}



	private static AbstractListNode appendHelper(ListNode list1, ListNode list2) {

		if (list1.isEmpty()) {

			return list2;

		} else {

			return new NonemptyListNode(list1.item(), appendHelper(list1.next(), list2));

		}

	}


	public AbstractListNode reverse() {

		return reverseHelper(this);

	}


	public static AbstractListNode reverseHelper(ListNode list1){

		if(list1.isEmpty()){

			return null;

		} else{

			return new NonEmptyListNode(reverseHelper(list1.next()), new NonEmptyListNode(myItem, null));

		}

	}
}    
// Every other list-processing method goes here.

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
		size = size + 1;
		return this.next().size();
	}

	public Comparable get(int index){
		if(index == 0){
			return myItem;
		}
		else if(myNext == null){
			throw new IllegalArgumentException();
		}
		else{
			return myNext.get(index-1);
		}
	}

	public String toString() {
		String saved = "(";
		for (int x = 0; x < this.size(); x = x +1) {
			saved = saved + " " + this.get(x); 
		}
		return saved + " )";
	}

	public boolean equals(Object x) {
		if (this.size() == ((AbstractListNode) x).size()) {
			for (int i = 0; i < this.size(); i = i +1) {
				if (this.get(i) != ((AbstractListNode) x).get(i)) {
					return false;
				} 
			}
			return true;
		} 
		return false;
	}

	public AbstractListNode appendInPlace(AbstractListNode list2) {
		if (this.isEmpty()) {
			return list2;
		} else {
			NonemptyListNode x = this;
			while (x.next().isEmpty() == false) {
				x = (NonemptyListNode) x.next();
			}
			return x.myNext = list2;
		}
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
	public int size() {
		int temp = size;
		size = 0;
		return temp;
	}

	public boolean isEmpty() {
		return true;
	}

	@Override
	public Comparable get(int index) {
		return null;
	}

	@Override
	public String toString() {
		return "( )";
	}


}
