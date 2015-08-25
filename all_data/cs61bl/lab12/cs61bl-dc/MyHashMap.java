import java.util.Iterator;
import java.util.LinkedList;

/* YOU ARE NOT allowed to use any library function related to hash maps
 if you feel the need you may use the ArrayList or LinkedList class */

public class MyHashMap<K, V> implements Iterable<K>
{
	/* Default size of the map if not set in constructor */
	private static final int	DEFAULT_CAPACITY	= 30;

	/* Default load factor if not set in constructor */
	private static final double	DEFAULT_LOAD_FACTOR	= 0.7;

	private int					capacity;					// the number of
															// buckets in the
															// map
	private int					size;						// the number of
															// items that have
															// been put into the
															// map

	private double load_factor;
	private LinkedList<KVPair>[] buckets;
	
	/**
	 * Constructs an empty map.
	 */
	public MyHashMap()
	{
		capacity = DEFAULT_CAPACITY;
		load_factor = DEFAULT_LOAD_FACTOR;
		size = 0;
		buckets = new LinkedList[capacity];
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity)
	{
		capacity = initialCapacity;
		load_factor = DEFAULT_LOAD_FACTOR;
		size = 0;
		buckets = new LinkedList[capacity];
	}

	/**
	 * Constructs an empty map with the given intial capacity and the given load
	 * factor.
	 * 
	 * @param loadFactor
	 *            A fraction greater than 0 and less than 1. Once the size /
	 *            capacity exceeds this number, the map's underlying array
	 *            should expand.
	 */
	public MyHashMap(int initialCapacity, double loadFactor)
	{
		capacity = initialCapacity;
		load_factor = loadFactor;
		size = 0;
		buckets = new LinkedList[capacity];
	}

	/**
	 * Returns the number of items put into the map (and not subsequently
	 * removed).
	 */
	public int size()
	{
		return size;
	}

	/**
	 * Returns the capacity of the underlying array of the map.
	 */
	public int capacity()
	{
		return this.capacity;
	}

	/**
	 * Returns whether the map contains the given key. Runs in O(1) time on
	 * average, relative to the size of the map.
	 */
	public boolean containsKey(K key)
	{
		LinkedList<KVPair> bucket = buckets[key.hashCode() % capacity];
		if(bucket == null)
			return false;
		for (KVPair pair : bucket)
			if (pair.key().equals(key))
				return true;
		return false;
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value)
	{
		for(LinkedList<KVPair> bucket : buckets)
			if(bucket != null)
				for(KVPair pair : bucket)
					if(pair.value().equals(value))
						return true;
		return false;
	}

	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 * 
	 * Note: If this method causes size / capacity to be greater than the load
	 * factor, then this method should also expand the map.
	 */
	public V put(K key, V value)
	{
		size++;
		if(size / buckets.length > load_factor)
			this.expand(capacity * 2);
		int hash = key.hashCode() % capacity;
		if(buckets[hash] == null)
		{
			buckets[hash] = new LinkedList<KVPair>();
			buckets[hash].add(new KVPair(key, value));
			return null;
		}
		for(KVPair pair : buckets[hash])
		{
			if(pair.key().equals(key))
			{
				return pair.setValue(value);
			}
		}
		buckets[hash].add(new KVPair(key, value));
		return null;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key)
	{
		int hash = key.hashCode() % capacity;
		if (buckets[hash] == null)
			return null;
		for(int i = 0; i < buckets[hash].size(); i++)
			if(buckets[hash].get(i).key().equals(key))
			{
				size--;
				return buckets[hash].remove(i).value();
			}
		return null;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key)
	{
		int hash = key.hashCode() % capacity;
		if (buckets[hash] == null)
			return null;
		for (KVPair pair : buckets[hash])
			if (pair.key().equals(key))
				return pair.value();
		return null;
	}

	/**
	 * Expands the underlying array to the given capacity.
	 * 
	 * ALSO, rehashes all of the items into this new array. Items will likely
	 * end up in different buckets after the expansion.
	 */
	private void expand(int newCapacity)
	{
		LinkedList<KVPair>[] oldBuckets = buckets;
		buckets = new LinkedList[newCapacity];
		capacity = newCapacity;
		for(LinkedList<KVPair> bucket : oldBuckets)
			if (bucket != null)
				for(KVPair pair : bucket)
					this.put(pair.key, pair.value);
	}

	/**
	 * Returns an iterator over the keys of this map.
	 */
	public Iterator<K> iterator()
	{
		return new HashMapIterator();
	}

	/**
	 * An iterator for the keys of the enclosing map.
	 */
	private class HashMapIterator implements Iterator<K>
	{
		private int bucketIndex;
		private int pairIndex;

		public HashMapIterator()
		{
			for(int i = 0; i < buckets.length; i++)
			{
				if(buckets[i] != null)
					for(int j = 0; j < buckets[i].size(); j++)
						if(buckets[i].get(j) != null)
						{
							bucketIndex = i;
							pairIndex = j;
							return;
						}
			}
			bucketIndex = -1;
			pairIndex = -1;
		}

		@Override
		public boolean hasNext()
		{
			return bucketIndex != -1 && pairIndex != -1;
		}

		@Override
		public K next()
		{
			K key = buckets[bucketIndex].get(pairIndex).key();
			for(int i = bucketIndex; i < buckets.length; i++)
			{
				if(buckets[i] != null)
					for(int j = pairIndex; j < buckets[i].size(); j++)
						if(buckets[i].get(j) != null)
						{
							bucketIndex = i;
							pairIndex = j;
							return key;
						}
			}
			bucketIndex = -1;
			pairIndex = -1;
			return key;
		}

		@Override
		public void remove()
		{
			// TODO Auto-generated method stub
			
		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair
	{
		private K	key;
		private V	value;
		/*
		 * TODO Fill this out if you need it. Maybe not required depending on
		 * your implementation.
		 */
		private KVPair()
		{
			key = null;
			value = null;
		}
		private KVPair(K key, V value)
		{
			this.key = key;
			this.value = value;
		}
		private K key()
		{
			return key;
		}
		private V value()
		{
			return value;
		}
		private V setValue(V value)
		{
			V temp = this.value;
			this.value = value;
			return temp;
		}
	}

}
