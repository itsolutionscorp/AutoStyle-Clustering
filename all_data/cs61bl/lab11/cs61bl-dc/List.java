import java.util.Iterator;

public class List implements Iterable
{
	private ListNode	myHead;
	private int			mySize;
	private ListNode	myTail;

	public List()
	{
		myHead = null;
		mySize = 0;
		myTail = null;
	}
	public boolean isEmpty()
	{
		return myHead == null;
	}
	private static class ListNode
	{
		private Object		myItem;
		private ListNode	myNext;

		private ListNode(Object item, ListNode next)
		{
			myItem = item;
			myNext = next;
		}
		private ListNode(Object item)
		{
			myItem = item;
			myNext = null;
		}
	}
	public String toString()
	{
		String rtn = "( ";
		for (ListNode p = myHead; p != null; p = p.myNext)
		{
			rtn = rtn + p.myItem + " ";
		}
		return rtn + ")";
	}
	// Return the number of items in this list ("length" in Scheme).
	public int size()
	{
		return mySize;
	}
	// Return true if the list contains the object
	public boolean contains(Object obj)
	{
		for (ListNode p = myHead; p != null; p = p.myNext)
		{
			if (obj.equals(p.myItem))
			{
				return true;
			}
		}
		return false;
	}
	// Returns the element at the given position in this list.
	public Object get(int pos)
	{
		if (pos < 0)
		{
			throw new IllegalArgumentException("Argument to get must be at least 0.");
		}
		if (pos >= size())
		{
			throw new IllegalArgumentException("Argument to get is too large.");
		}
		int k = 0;
		for (ListNode p = myHead; p != null; p = p.myNext)
		{
			if (k == pos)
			{
				return p.myItem;
			}
			k++;
		}
		return null;
	}
	public void addToFront(Object obj)
	{
		myHead = new ListNode(obj, myHead);
		if (myTail == null)
			myTail = myHead;
		mySize++;
	}
	public boolean equals(Object obj)
	{
		ListNode node1 = myHead;
		ListNode node2 = ((List) obj).myHead;
		while (node1 != null)
		{
			if (node2 == null)
				return false;
			if (!node1.myItem.equals(node2.myItem))
				return false;
			node1 = node1.myNext;
			node2 = node2.myNext;
		}
		return node2 == null;
	}
	public void add(Object x)
	{
		mySize++;
		if (myHead == null)
		{
			myHead = new ListNode(x, null);
			myTail = myHead;
			return;
		}
		myTail.myNext = new ListNode(x, null);
		myTail = myTail.myNext;
	}
	public void appendInPlace(List l)
	{
		if (l.isEmpty())
			return;
		mySize += l.size();
		if (isEmpty())
		{
			myHead = l.myHead;
			myTail = l.myTail;
			return;
		}
		myTail.myNext = l.myHead;
		myTail = l.myTail;
	}
	@Override
	public Iterator iterator()
	{
		return new ElementIterator();
	}
	public class ElementIterator implements Iterator
	{
		private ListNode	node;

		public ElementIterator()
		{
			node = myHead;
		}

		public boolean hasNext()
		{
			return node != null;
		}

		public Object next()
		{
			Object toReturn = node.myItem;
			node = node.myNext;
			return toReturn;
		}

		public void remove()
		{
			// not used; do not implement
		}
	}
	public boolean isOk()
	{
		ListNode node = myHead;
		int count = 0;
		if (node == null)
			return mySize == count && myTail == node;
		while (node != null)
		{
			count++;
			if(node.myItem == null)
				return false;
			if(node.myNext == null && !myTail.equals(node))
				return false;
			node = node.myNext;
		}
		return mySize == count;
	}
	public void remove(Object o)
	{
		while(myHead.myItem.equals(o))
		{
			myHead = myHead.myNext;
			mySize--;
		}
		ListNode prev = myHead;
		ListNode node = myHead.myNext;
		while(node != null)
		{
			while (node.myItem.equals(o))
			{
				prev.myNext = node.myNext;
				mySize--;
				node = node.myNext;
				if (node == null)
				{
					myTail = prev;
					return;
				}
			}
			node = node.myNext;
			prev = prev.myNext;
		}
		myTail = prev;
	}
	public void doubleInPlace()
	{
		for(ListNode node = myHead; node != null; node = node.myNext)
		{
			node.myNext = new ListNode(node.myItem, node.myNext);
			node = node.myNext;
			myTail = node;
		}
		mySize *= 2;
	}
	public void reverse()
	{
		myTail = myHead;
		myHead = reverseHelper(this.myHead, null);
	}
	private static ListNode reverseHelper(ListNode l, ListNode soFar)
	{
		if (l == null)
			return soFar;
		ListNode next = l.myNext;
		l.myNext = soFar;
		return reverseHelper(next, l);
	}
}
