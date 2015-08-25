abstract public class AbstractListNode
{
	abstract public Object item();

	abstract public AbstractListNode next();

	abstract public boolean isEmpty();

	abstract public int size();

	abstract public Object get(int index);

	public String toString()
	{
		String result = "( ";
		AbstractListNode node = this;
		while(!node.isEmpty())
		{
			result += node.item() + " ";
			node = node.next();
		}
		result += ")";
		return result;
	}
	
	abstract public boolean equals(Object other);
}

class NonemptyListNode extends AbstractListNode
{

	private Object				myItem;
	private AbstractListNode	myNext;

	public NonemptyListNode(Object item, AbstractListNode next)
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

	public NonemptyListNode(Object item)
	{
		this(item, new EmptyListNode());
	}

	public Object item()
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

	public Object get(int index)
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
}

class EmptyListNode extends AbstractListNode
{

	public EmptyListNode()
	{

	}

	public Object item()
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

	public Object get(int index)
	{
		throw new IllegalArgumentException("Index is out of bounds");
	}
	
	public boolean equals(Object other)
	{
		return ((AbstractListNode) other).isEmpty();
	}
}
