import java.util.Iterator;
import java.util.ArrayList;

/* YOU ARE NOT allowed to use any library function related to hash maps
 if you feel the need you may use the ArrayList or LinkedList class */

public class MyHashMap<K, V> implements Iterable<K>{
	/* Default size of the map if not set in constructor */
	private static final int DEFAULT_CAPACITY = 30;

	/* Default load factor if not set in constructor */
	private static final double DEFAULT_LOAD_FACTOR = 0.7;

	private int capacity; // the number of buckets in the map
	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	private double loadFactor;
	private ArrayList<K> keys;
	private ArrayList<V> values;

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		capacity = DEFAULT_CAPACITY;
		loadFactor = DEFAULT_LOAD_FACTOR;
		keys = new ArrayList<K>(DEFAULT_CAPACITY);
		values = new ArrayList<V>(DEFAULT_CAPACITY);
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		capacity = initialCapacity;
		loadFactor = DEFAULT_LOAD_FACTOR;
		keys = new ArrayList<K>(initialCapacity);
		values = new ArrayList<V>(initialCapacity);
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
		capacity = initialCapacity;
		this.loadFactor = loadFactor;
		keys = new ArrayList<K>(initialCapacity);
		values = new ArrayList<V>(initialCapacity);
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
		return keys.contains(key);
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run? O(1)
	 */
	public boolean containsValue(V value) {
		// TODO Complete this!
		return values.contains(value);
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
		size++;
		if (size / capacity > loadFactor ) {
			expand(size*2);
		}
		if (containsKey(key)) {
			int index = keys.indexOf(key);
			V temp = values.get(index);
			values.set(index, value);
			return temp;
		} else {
			keys.add(key);
			values.add(value);
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
		if (keys.contains(key)) {
			int index = keys.indexOf(key);
			V temp = values.get(index);
			keys.remove(index);
			values.remove(index);
			size--;
			return temp;
		} else {
			return null;
		}
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		// TODO Complete this!
		if (containsKey(key)) {
			int index = keys.indexOf(key);
			return values.get(index);
		} else {
			return null;
		}
	}

	/**
	 * Expands the underlying array to the given capacity.
	 * 
	 * ALSO, rehashes all of the items into this new array. Items will likely
	 * end up in different buckets after the expansion.
	 */
	private void expand(int newCapacity) {
		// TODO Complete this!
		capacity = newCapacity;
		ArrayList<K> new_keys = new ArrayList<K>(newCapacity);
		ArrayList<V> new_values = new ArrayList<V>(newCapacity);
		for (int i = 0; i < size(); i++) {
			new_keys.add(keys.get(i));
			new_values.add(values.get(i));
		}
		keys = new_keys;
		values = new_values;
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
		
		private int index;

		public HashMapIterator() {
			// TODO Complete this!
			index = -1;
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			return index < size() - 1;
		}

		@Override
		public K next() {
			// TODO Complete this!
			index++;
			return keys.get(index);
		}
		
		@Override
		public void remove() {
			return;
		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
//	private class KVPair {
//		private K key;
//		private V value;
//		/*
//		 * TODO Fill this out if you need it. Maybe not required depending on
//		 * your implementation.
//		 */
//		public KVPair(K key, V value) {
//			this.key = key;
//			this.value = value;
//		}
//		
//	}

}
