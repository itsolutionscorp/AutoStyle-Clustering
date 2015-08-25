import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

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
	private double loadFactor;
	private Object[] keys;
	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		capacity = DEFAULT_CAPACITY;
		loadFactor = DEFAULT_LOAD_FACTOR;
		keys = new Object[capacity];
		size = 0;
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		capacity = initialCapacity;
		loadFactor = DEFAULT_LOAD_FACTOR;
		keys = new Object[capacity];
		size = 0;
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
		loadFactor = loadFactor;
		keys = new Object[capacity];
		size = 0;
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
		if (get(key) != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		for (Object bucket : keys) {
			if (bucket != null) {
				ArrayList<KVPair> list = (ArrayList<KVPair>) bucket;
				for (KVPair pair : list) {
					if (pair.value.equals(value)) {
						return true;
					}
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
		int i = getHash(key);
		if (keys[i] == null) {
			ArrayList<KVPair> bucket = new ArrayList<KVPair>();
			keys[i] = bucket;
		}
		KVPair pair = new KVPair(key, value);
		((ArrayList) keys[i]).add(pair);
		size++;
		return value;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		int i = getHash(key);
		ArrayList<KVPair> list = (ArrayList<KVPair>) keys[i];
		for (KVPair pair : list) {
			if (pair.key.equals(key)) {
				V temp = pair.value;
				int n = list.indexOf(pair);
				list.remove(n);
				size--;
				return temp;
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
		int i = getHash(key);
		ArrayList<KVPair> list = (ArrayList<KVPair>) keys[i];
		for (KVPair pair : list) {
			if (pair.key.equals(key)) {
				return pair.value;
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
		capacity = newCapacity;
		size = 0;
		for (Object bucket : keys) {
			if (bucket != null) {
				ArrayList<KVPair> list = (ArrayList<KVPair>) bucket;
				for (KVPair pair : list) {
					put(pair.key, pair.value);
				}
			}
		}
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

		int nextRow;
		int nextCol;
		int count;

		public HashMapIterator() {
			nextRow = 0;
			nextCol = 0;
			count = 0;
		}

		@Override
		public boolean hasNext() {
			return count < size;
		}

		@Override
		public K next() {
			if (keys[nextRow] != null) {
				ArrayList<KVPair> bucket = (ArrayList<KVPair>) keys[nextRow];
				if (nextCol < bucket.size()) {
					K key = bucket.get(nextCol).key;
					count++;
					nextCol++;
					return key;
				} else if (nextRow + 1 < capacity) {
					nextCol = 0;
					nextRow++;
					return next();
				} else {
					return next();
				}
			} else {
				nextRow++;
				return next();
			}
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
	}

	private int getHash(K key) {
		int i = key.hashCode() % capacity;
		return i;
	}
}
