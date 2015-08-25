import java.util.NoSuchElementException;

// remember to change the generic 
abstract public class AbstractListNode implements Comparable {

	abstract public Comparable item();

	abstract public AbstractListNode next();

	abstract public boolean isEmpty();

	// Every other list-processing method goes here.
	abstract public Comparable get(int pos); // lab9

	abstract public int size();

	abstract public String toString();

	abstract public boolean equals(AbstractListNode c);

	abstract public AbstractListNode add(Comparable c); // lab10

	abstract public AbstractListNode append(AbstractListNode c);

	abstract public AbstractListNode reverse();

	abstract public Comparable smallest();

	abstract public void setItem(Comparable update);

	abstract public void setNext(AbstractListNode newNext);
	
	abstract public AbstractListNode appendInPlace(AbstractListNode list);

	public static AbstractListNode merge(AbstractListNode a, AbstractListNode b) {
		if (a.isEmpty()) {
			return b;
		}
		if (b.isEmpty()) {
			return a;
		}
		if (a.get(0).compareTo(b.get(0)) == -1) {
			return a.append(merge(a.next(), b));
		} else {
			return b.append(merge(a, b.next()));
		}

	}

	public static Comparable min(Comparable c1, Comparable c2) {
		if (c1.compareTo(c2) < 0) {
			return c1;
		} else {
			return c2;
		}
	}

	@Override
	public int compareTo(Object T) {
		return this.item().compareTo(T);

	}

}

class NonemptyListNode extends AbstractListNode {

	private Comparable myItem;
	private AbstractListNode myNext;
	private static Comparable smallestSoFar;

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

	// implementation for extra method goes here
	public Comparable get(int pos) {
		AbstractListNode temp = this;
		while (pos != 0) {
			temp = temp.next();
			pos--;
		}
		return temp;
	}

	public int size() {
		if (this.isEmpty()) {
			return 0;
		} else {
			return 1 + this.next().size();
		}
	}

	public String toString() {
		String result = "( ";
		AbstractListNode temp = this;
		while (!temp.isEmpty()) {
			result += temp.item() + " ";
			temp = temp.next();
		}
		result += ")";
		return result;
	}

	public boolean equals(AbstractListNode a) {
		if (this.size() != a.size())
			return false;
		else {
			for (int i = 0; i < this.size(); i++) {
				if (!this.get(i).equals(a.get(i)))
					return false;
			}
		}
		return true;
	}

	// smallest method
	public Comparable smallest() {
		if (isEmpty()) {
			throw new NoSuchElementException(
					"Can't find smallest in empty list.");
		}
		return smallestHelper(smallestSoFar);
	}

	public Comparable smallestHelper(Comparable smallestSoFar) {
		Comparable T = min(smallestSoFar, this.item());
		this.smallestSoFar = T;
		return T;

	}


	// recursion save life
	// OMG ! long live scheme!!!!!
	public AbstractListNode add(Comparable c) {
		return new NonemptyListNode(this.item(), this.next().add(c));
	}

	public AbstractListNode append(AbstractListNode list) {
		return new NonemptyListNode(this.item(), this.next().append(list));
	}

	public AbstractListNode reverse() {
		return (this.next().reverse().add(this.item()));
	}

	public AbstractListNode appendInPlace(AbstractListNode list) {
		AbstractListNode Nownode = this;
		AbstractListNode itr = this.myNext;
		while (true) {
			if (!itr.isEmpty()) {
				Nownode = Nownode.next();
				itr = itr.next();
			} else {
				Nownode.setNext(list);
				break;
			}
		}
		return this;
	}

	public void setItem(Comparable update) {
		this.myItem = update;
	}

	public void setNext(AbstractListNode newNext) {
		this.myNext = newNext;
	}

	// call the compareTo in the String object

}

class EmptyListNode extends AbstractListNode {

	public EmptyListNode() {

	}

	public Comparable item() {
		throw new IllegalArgumentException(
				" Lok-Tar Ogar! No item! ");
	}

	public AbstractListNode next() {
		throw new IllegalArgumentException(
				"you already reached the end of the world!! Ho ho ho ");
	}

	public boolean isEmpty() {
		return true;
	}
	public int size(){
		return 0;
	}
	
	public Comparable get(int pos){
		throw new IllegalArgumentException( " Greed is not good --1000000 ");
	}
	public String toString() {
		return "()";
	}

	public boolean equals(AbstractListNode a) {
		return a.isEmpty();
	}

	public static Comparable min(Comparable c1, Comparable c2) {
		if (c1.compareTo(c2) < 0) {
			return c1;
		} else {
			return c2;
		}
	}
	
	
	public void setItem(Comparable update) {
		throw new IllegalArgumentException(
				" you can not ask something from nothing : nothing to change item in an EmptyListNode.");
	}

	public void setNext(AbstractListNode newNext) {
		throw new IllegalArgumentException(
				"Mmm. There is no next ");
	}

	public AbstractListNode add(Comparable c){
		return new NonemptyListNode(c);
	}

	 public AbstractListNode append(AbstractListNode c){
		return c;
	}

	 public AbstractListNode reverse(){
		return new EmptyListNode();
	}

	 public Comparable smallest(){
		 return "()";
	 }
	
	 public AbstractListNode appendInPlace(AbstractListNode list){
		 return list;
	 }
	 
	@Override
	public int compareTo(Object T) {
		return this.item().compareTo(T);
	}

}
