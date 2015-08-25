import java.util.Iterator;

public class Sequence<T> implements Iterable<T>
{
	T[]	myValues;
	int	myCount;

	public Sequence()
	{
		myValues = (T[]) new Object[20];
		myCount = 0;
	}

	public Sequence(int capacity)
	{
		myValues = (T[]) new Object[capacity];
		myCount = 0;
	}

	public boolean isEmpty()
	{
		return myCount == 0;
	}

	public int size()
	{
		return myCount;
	}

	public T elementAt(int pos)
	{
		if (pos < 0 || pos >= myCount)
		{
			System.out.println("IndexOutOfBoundsException");
			System.exit(1);
		}
		return myValues[pos];
	}

	public void add(T toBeAdded)
	{
		if (myCount == myValues.length)
		{
			System.out.println("Not enough space.");
			System.exit(1);
		}
		myValues[myCount] = toBeAdded;
		myCount++;
	}

	public String toString()
	{
		String str = new String();
		for (int i = 0; i < myCount; i++)
		{
			str += myValues[i] + " ";
		}
		return str.trim();
	}

	public void remove(int pos)
	{
		if (pos < 0 || pos >= myCount)
		{
			System.out.println("IndexOutOfBoundsException");
			System.exit(1);
		}
		myValues[pos] = null;
		for (int i = pos; i < myCount - 1; i++)
		{
			T temp = myValues[i];
			myValues[i] = myValues[i + 1];
			myValues[i + 1] = temp;
		}
		myCount--;
	}

	// Insert toInsert into the sequence at position insertPos,
	// shifting the later elements in the sequence over to make room
	// for the new element.
	// Assumptions: The array isn't full, i.e. myCount < myValues.length
	// Also, insertPos is between 0 and myCount, inclusive.
	public void insert(int pos, T newInt)
	{
		if (pos < 0 || pos > myCount)
		{
			System.out.println("IndexOutOfBoundsException");
			System.exit(1);
		}
		if (myCount == myValues.length)
		{
			System.out.println("Not enough space.");
			System.exit(1);
		}
		myCount++;
		T temp = myValues[pos];
		myValues[pos] = newInt;
		for (int i = pos + 1; i < myCount; i++)
		{
			T temp2 = myValues[i];
			myValues[i] = temp;
			temp = temp2;
		}
	}

	public boolean contains(T k)
	{
		for (int i = 0; i < myCount; i++)
			if (myValues[i] == k)
				return true;
		return false;
	}

	@Override
	public Iterator<T> iterator()
	{
		return new SequenceIterator<T>();
	}
	
	private class SequenceIterator<T> implements Iterator<T>
	{
		int index;
		
		public SequenceIterator()
		{
			index = 0;
		}

		@Override
		public boolean hasNext()
		{
			return index < myCount;
		}

		@Override
		public T next()
		{
			index++;
			return (T) myValues[index - 1];
		}

		@Override
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
}
