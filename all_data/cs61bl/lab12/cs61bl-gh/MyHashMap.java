import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/* YOU ARE NOT allowed to use any library function related to hash maps
 if you feel the need you may use the ArrayList or LinkedList class */

public class MyHashMap<K, V> implements Iterable<K> {
	/* Default size of the map if not set in constructor */
	private static final int DEFAULT_CAPACITY = 30;

	/* Default load factor if not set in constructor */
	private static final double DEFAULT_LOAD_FACTOR = 0.7;

	private int capacity; // the number of buckets in the map
	private int size; // the number of items that have been put into the map

	private ArrayList<LinkedList<KVPair>> hashMap;
	private double loadFactor;
	private static final double RESIZE_FACTOR = 2.0;

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		this(initialCapacity, DEFAULT_LOAD_FACTOR);
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
	public MyHashMap(int initialCapacity, double loadFactor) {
		hashMap = new ArrayList<LinkedList<KVPair>>(initialCapacity);
		capacity = initialCapacity;
		size = 0;
		this.loadFactor = loadFactor;
		
		for (int i = 0; i < capacity; i++)
		{
			hashMap.add(null);
		}
	}

	/**
	 * Returns the number of items put into the map (and not subsequently
	 * removed).
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns the capacity of the underlying array of the map.
	 */
	public int capacity() {
		return this.capacity;
	}

	/**
	 * Returns whether the map contains the given key. Runs in O(1) time on
	 * average, relative to the size of the map.
	 */
	public boolean containsKey(K key) {
		LinkedList<KVPair> list = hashMap.get(key.hashCode() % capacity);
		return list != null && list.contains(new KVPair(key, null));
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		for (LinkedList<KVPair> list : hashMap)
		{
			if (list == null)
			{
				continue;
			}
			for (KVPair pair : list)
			{
				if (pair.value.equals(value))
				{
					return true;
				}
			}
		}
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
	public V put(K key, V value) {
		size++;
		if (size >= capacity * loadFactor)
		{
			expand((int)(capacity * RESIZE_FACTOR));
		}
		LinkedList<KVPair> list = hashMap.get(key.hashCode() % capacity);
		if (list == null)
		{
			list = new LinkedList<KVPair>();
			hashMap.remove(key.hashCode() % capacity);
			hashMap.add(key.hashCode() % capacity, list);
		}
		if (!containsKey(key))
		{
			list.add(new KVPair(key, value));
			return null;
		}
		KVPair prevPair = list.get(list.indexOf(new KVPair(key, null)));
		V prevValue = prevPair.value;
		prevPair.value = value;
		return prevValue;
		
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		if (!containsKey(key))
		{
			return null;
		}
		size--;
		LinkedList<KVPair> list = hashMap.get(key.hashCode() % capacity);
		KVPair prevPair = list.get(list.indexOf(new KVPair(key, null)));
		V prevValue = prevPair.value;
		list.remove(prevPair);
		return prevValue;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		if (!containsKey(key))
		{
			return null;
		}
		LinkedList<KVPair> list = hashMap.get(key.hashCode() % capacity);
		return list.get(list.indexOf(new KVPair(key, null))).value;
	}

	/**
	 * Expands the underlying array to the given capacity.
	 * 
	 * ALSO, rehashes all of the items into this new array. Items will likely
	 * end up in different buckets after the expansion.
	 */
	private void expand(int newCapacity) {
		ArrayList<LinkedList<KVPair>> newMap = 
				new ArrayList<LinkedList<KVPair>>(newCapacity);

		for (int i = 0; i < newCapacity; i++)
		{
			newMap.add(null);
		}
		
		for (LinkedList<KVPair> list : hashMap)
		{
			for (KVPair pair : list)
			{
				LinkedList<KVPair> newList = newMap.get(pair.key.hashCode() % capacity);
				if (newList == null)
				{
					newList = new LinkedList<KVPair>();
					newMap.remove(pair.key.hashCode() % newCapacity);
					newMap.add(pair.key.hashCode() % newCapacity, newList);
				}
				newList.add(pair);
			}
		}
		capacity = newCapacity;
		hashMap = newMap;
		
	}

	/**
	 * Returns an iterator over the keys of this map.
	 */
	public Iterator<K> iterator() {
		return new HashMapIterator();
	}

	/**
	 * An iterator for the keys of the enclosing map.
	 */
	private class HashMapIterator implements Iterator<K> {

		public HashMapIterator() {
			// TODO Complete this!
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			return false;
		}

		@Override
		public K next() {
			// TODO Complete this!
			return null;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair {
		private K key;
		private V value;
		/*
		 * TODO Fill this out if you need it. Maybe not required depending on
		 * your implementation.
		 */
		public KVPair(K key, V value)
		{
			this.key = key;
			this.value = value;
		}
		public boolean equals(Object obj)
		{
			return key.equals(((KVPair)obj).key);
		}
	}
	
	public static void main (String[] args) {
		
		ArrayList list = new ArrayList();
		Object x = new String("x");
		list.add(x);
		System.out.println(list.hashCode());
		list.remove(x);
		list.add("x");
		System.out.println(list.hashCode());
		System.out.println();
		ArrayList list1 = new ArrayList<Integer>();
		ArrayList list2 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		list2.add(2);
		list2.add(1);
		System.out.println(list1.hashCode());
		System.out.println(list2.hashCode());
	}

}
