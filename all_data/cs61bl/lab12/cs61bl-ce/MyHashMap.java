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
	private K[] keys;
	private V[] vals;
	private double load_factor;

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
		this(initialCapacity, DEFAULT_LOAD_FACTOR);
		// TODO Complete this!
	}

	/**
	 * Constructs an empty map with the given initial capacity and the given load
	 * factor.
	 * 
	 * @param loadFactor
	 *            A fraction greater than 0 and less than 1. Once the size /
	 *            capacity exceeds this number, the map's underlying array
	 *            should expand.
	 */
	public MyHashMap(int initialCapacity, double loadFactor) {
		capacity = initialCapacity;
		load_factor = loadFactor;
		keys = (K[]) new Object [capacity];
		vals = (V[]) new Object [capacity];
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
		return (keys[key.hashCode()] !=null);
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		// TODO Complete this!
		for (int i = 0; i < vals.length; i++) {
			if (vals[i].hashCode() == (value.hashCode())) {
				return true;
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
		// TODO Complete this!
		if (this.get(key) != null) {
			V temp = vals[key.hashCode()];
			vals[key.hashCode()] = value;
			return temp;	
		} else {
			keys[key.hashCode()] = key;
			vals[key.hashCode()] = value;
			size++;
			if (size/capacity > load_factor) {
				this.expand((int) (capacity/load_factor +1));
			}
			return null;
		}
		
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		int i = key.hashCode();
		V temp = vals[i];
		keys[i] = null;
		vals[i] = null;
		this.size--;
		return temp;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		// TODO Complete this!
		for (int i = 0; i < keys.length; i++) {
			if (keys[i].hashCode()==key.hashCode()) {
				return vals[i];
			}
		}
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
		MyHashMap<K, V> temp = new MyHashMap<K, V>(newCapacity, this.load_factor);
		for (int i = 0; i < keys.length; i++) {
			if (keys[i] != null) {
				temp.put(keys[i], vals[i]);
			}
		}
		this.keys = temp.keys;
		this.vals = temp.vals;
		this.capacity = temp.capacity;
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
		K[] myKeys;
		int current;
		
		public HashMapIterator() {
			// TODO Complete this!
			K[] myKeys = keys;
			int current = 0;
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			return (this.myKeys[current] != null);
		}

		@Override
		public K next() {
			// TODO Complete this!
			K temp = this.myKeys[current];
			current++;
			return temp;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			return;
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
	}

}
