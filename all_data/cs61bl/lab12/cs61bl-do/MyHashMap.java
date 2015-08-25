import java.util.ArrayList;
import java.util.Iterator;

/* YOU ARE NOT allowed to use any library function related to hash maps
 if you feel the need you may use the ArrayList or LinkedList class */

public class MyHashMap<K, V> implements Iterable<K> {
	/* Default size of the map if not set in constructor */
	private static final int DEFAULT_CAPACITY = 30;

	/* Default load factor if not set in constructor */
	private static final double DEFAULT_LOAD_FACTOR = 0.7;

	private int capacity; // the number of buckets in the map
	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	private ArrayList<ArrayList<KVPair>> kv;
	private double loadFactor;

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		this(initialCapacity, DEFAULT_LOAD_FACTOR);
	}

	/**
	 * Constructs an empty map with the given initial capacity and the given
	 * load factor.
	 * 
	 * @param loadFactor
	 *            A fraction greater than 0 and less than 1. Once the size /
	 *            capacity exceeds this number, the map's underlying array
	 *            should expand.
	 */
	public MyHashMap(int initialCapacity, double loadFactor) {
		kv = new ArrayList<ArrayList<KVPair>>(initialCapacity);
		capacity = initialCapacity;
		this.loadFactor = loadFactor;
		size = 0;

		// Initialize all elements of array list to avoid IndexOutOfBounds
		// exception
		for (int i = 0; i < capacity; i++)
			kv.add(new ArrayList<KVPair>());
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
		// TODO Complete this!
		if (get(key) != null)
			return true;
		return false;
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		// TODO Complete this!
		for (ArrayList<KVPair> a : kv)
			for (KVPair b : a)
				if (b.value().equals(value))
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
	public V put(K key, V value) {
		// TODO Complete this!
		// Dynamic resizing
		if (((double) size / capacity) > loadFactor)
			expand((int) (capacity / loadFactor));
		
		// Replacing KVPair if duplicate found
		V v = get(key);
		if (v != null) 
			remove(key);
		
		// Actual adding
		size++;
		int hash = key.hashCode() % capacity;
		ArrayList<KVPair> k = kv.get(hash);
		k.add(new KVPair(key, value));
		
		return v;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		int pos = key.hashCode() % capacity;
		ArrayList<KVPair> a = kv.get(pos);
		for (KVPair p : a) {
			if (p.key().equals(key)) {
				V v = p.value();
				a.remove(p);
				size--;
				return v;
			}
		}
		return null;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		// TODO Complete this!
		int pos = key.hashCode() % capacity;
		ArrayList<KVPair> a = kv.get(pos);
		for (KVPair p : a)
			if (p.key().equals(key))
				return p.value();
		return null;
	}

	/**
	 * Expands the underlying array to the given capacity.
	 * 
	 * ALSO, rehashes all of the items into this new array. Items will likely
	 * end up in different buckets after the expansion.
	 */
	private void expand(int newCapacity) {
		// TODO Complete this!
		// Initializing new ArrayList
		ArrayList<ArrayList<KVPair>> tmp = new ArrayList<ArrayList<KVPair>>(newCapacity);
		capacity = newCapacity;
		for (int i = 0; i < capacity; i++)
			tmp.add(new ArrayList<KVPair>());
		
		// Copying values from original ArrayList
		for (ArrayList<KVPair> a : kv)
			for (KVPair b : a) {
				int hash = b.key().hashCode() % capacity ;
				ArrayList<KVPair> p = tmp.get(hash);
				p.add(b);
			}
		
		kv = tmp;
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

		private int count;
		private KVPair current;

		public HashMapIterator() {
			// TODO Complete this!
			count = 0;
			for (ArrayList<KVPair> a : kv)
				for (KVPair b : a)
					if (b != null)
						current = b;
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			return count < size;
		}

		@Override
		public K next() {
			// TODO Complete this!
			count++;
			K k = current.key();
			int hash = current.key().hashCode() % capacity;			
			
			int tracker = 0;
			for(int i = hash; i < capacity; i++) {
				for(KVPair a : kv.get(i)) {
					if(a != null && a != current) {
						current = a;
						tracker = 1;
						break;
					}
				}
				if(tracker == 1)
					break;
			}
			
			return k;
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
		public KVPair(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K key() {
			return key;
		}

		public V value() {
			return value;
		}
	}
}
