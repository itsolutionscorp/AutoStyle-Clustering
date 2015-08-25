import java.util.NoSuchElementException;

abstract public class AbstractListNode
{
	abstract public Comparable item();
	abstract public AbstractListNode next();
	abstract public boolean isEmpty();
	abstract public int size();
	abstract public Comparable get(int index);
	abstract public boolean equals(Object other);
	abstract public void setItem(Comparable item);
	abstract public void setNext(AbstractListNode node);

	public String toString()
	{
		String result = "( ";
		AbstractListNode node = this;
		while (!node.isEmpty())
		{
			result += node.item() + " ";
			node = node.next();
		}
		result += ")";
		return result;
	}
	public Comparable smallest()
	{
		if (isEmpty())
		{
			throw new NoSuchElementException("Can't find smallest in empty list.");
		}
		return next().smallestHelper(item());
	}
	public Comparable smallestHelper(Comparable smallestSoFar)
	{
		if (isEmpty())
			return smallestSoFar;
		return next().smallestHelper(min(item(), smallestSoFar));
	}
	public static Comparable min(Comparable c1, Comparable c2)
	{
		if (c1.compareTo(c2) < 0)
			return c1;
		else
			return c2;
	}
	public AbstractListNode add(Comparable c)
	{
		if (isEmpty())
			return new NonemptyListNode(c, null);
		return new NonemptyListNode(item(), next().add(c));
	}
	public AbstractListNode append(AbstractListNode list)
	{
		if (isEmpty())
		{
			if (list.isEmpty())
				return null;
			return new NonemptyListNode(list.item(), this.append(list.next()));
		}
		return new NonemptyListNode(item(), next().append(list));
	}
	public AbstractListNode reverse()
	{
		if (isEmpty())
			return new EmptyListNode();
		AbstractListNode node = next().reverse();
		if (node.isEmpty())
			return new NonemptyListNode(item(), null);
		return node.add(item());
	}
	public AbstractListNode appendInPlace(AbstractListNode list)
	{
		if (this.isEmpty())
			return list;
		if (this.next().isEmpty())
		{
			this.setNext(list);
			return this;
		}
		this.next().appendInPlace(list);
		return this;
	}
	public static AbstractListNode merge(AbstractListNode a, AbstractListNode b)
	{
		if (a.isEmpty())
		{
			a = b;
			return a;
		}
		if (b.isEmpty())
			return a;
		if (a.item().compareTo(b.item()) < 0)
		{
			a.setNext(merge(a.next(), b));
			return a;
		}
		b.setNext(merge(a, b.next()));
		return b;
	}
}

class NonemptyListNode extends AbstractListNode
{
	private Comparable			myItem;
	private AbstractListNode	myNext;

	public NonemptyListNode(Comparable item, AbstractListNode next)
	{
		myItem = item;
		if (next == null)
		{
			myNext = new EmptyListNode();
		} else
		{
			myNext = next;
		}
	}
	public NonemptyListNode(Comparable item)
	{
		this(item, new EmptyListNode());
	}
	public Comparable item()
	{
		return myItem;
	}
	public AbstractListNode next()
	{
		return myNext;
	}
	public boolean isEmpty()
	{
		return false;
	}
	public int size()
	{
		return this.myNext.size() + 1;
	}
	public Comparable get(int index)
	{
		if (index == 0)
			return this.myItem;
		return myNext.get(index - 1);
	}
	public boolean equals(Object other)
	{
		if (((AbstractListNode) other).isEmpty())
			return false;
		if (!this.myItem.equals(((AbstractListNode) other).item()))
			return false;
		return myNext.equals(((AbstractListNode) other).next());
	}
	public void setItem(Comparable item)
	{
		myItem = item;
	}
	public void setNext(AbstractListNode node)
	{
		myNext = node;
	}
}

class EmptyListNode extends AbstractListNode
{
	public EmptyListNode()
	{

	}
	public Comparable item()
	{
		throw new IllegalArgumentException("There is no 'item' value stored in an EmptyListNode.");
	}
	public AbstractListNode next()
	{
		throw new IllegalArgumentException("No elements follow an EmptyListNode.");
	}
	public boolean isEmpty()
	{
		return true;
	}
	public int size()
	{
		return 0;
	}
	public Comparable get(int index)
	{
		throw new IllegalArgumentException("Index is out of bounds");
	}
	public boolean equals(Object other)
	{
		return ((AbstractListNode) other).isEmpty();
	}
	public void setItem(Comparable item)
	{
		throw new IllegalArgumentException("There is no 'item' value stored in an EmptyListNode.");
	}
	public void setNext(AbstractListNode node)
	{
		throw new IllegalArgumentException("No elements follow an EmptyListNode.");
	}
}
