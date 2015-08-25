
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Consumer;

/* YOU ARE NOT allowed to use any library function related to hash maps
 if you feel the need you may use the ArrayList or LinkedList class */

public class MyHashMap<K, V> implements Iterable<K> {
	/* Default size of the map if not set in constructor */
	private static final int DEFAULT_CAPACITY = 30;

	/* Default load factor if not set in constructor */
	private static final double DEFAULT_LOAD_FACTOR = 0.7;

	private int capacity; // the number of buckets in the map
	private int size; // the number of items that have been put into the map
	private LinkedList[] buckets;

	private double loadFactor;

	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		loadFactor = DEFAULT_LOAD_FACTOR;
		buckets = new LinkedList[initialCapacity];
		for (int i = 0; i < initialCapacity; i++) {
			buckets[i] = new LinkedList<KVPair>();

		}
		capacity = initialCapacity;

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
		this(initialCapacity);
		this.loadFactor = loadFactor;
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
		LinkedList<KVPair> list = getBucket(key);
		for (KVPair pair : list) {
			if (pair.key.equals(key))
				return true;
		}
		return false;
	}

	public LinkedList<KVPair> getBucket(K key) {
		return buckets[Math.abs(key.hashCode()) % capacity];
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		for (int i = 0; i < buckets.length; i++) {
			LinkedList<KVPair> list = buckets[i];
			for (KVPair pair : list) {
				if (pair.value.equals(value))
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
		V item = null;

		LinkedList<KVPair> pairs = getBucket(key);
		if (containsKey(key)) {
			for (int i = 0; i < pairs.size(); i++) {
				KVPair pair = pairs.get(i);
				if (pair.key.equals(key)) {
					item = pair.value;
					pairs.set(i, new KVPair(key, value));
				}
			}
		} else {
			pairs.add(new KVPair(key, value));
		}
		size++;
		if ((double) size / capacity > loadFactor) {
			expand(2 * capacity);
		}

		return item;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		V item = null;
		if (containsKey(key)) {
			LinkedList<KVPair> pairs = getBucket(key);
			for (int i = 0; i < pairs.size(); i++) {
				KVPair pair = pairs.get(i);
				if (pair.key.equals(key)) {
					item = pair.value;
					pairs.remove(i);
					break;
				}
			}
		}
		if (item != null)
			size++;
		return item;

	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		if (containsKey(key)) {
			LinkedList<KVPair> pairs = getBucket(key);
			for (int i = 0; i < pairs.size(); i++) {
				KVPair pair = pairs.get(i);
				if (pair.key.equals(key)) {
					return pair.value;
				}
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
		LinkedList[] newBuckets = new LinkedList[newCapacity];
		for (int i = 0; i < newCapacity; i++) {
			newBuckets[i] = new LinkedList<KVPair>();
		}
		int numPairs = 0;
		for (int i = 0; i < capacity; i++) {
			LinkedList<KVPair> pairs = buckets[i];
			for (int j = 0; j < pairs.size(); j++) {
				KVPair pair = pairs.get(j);
				int index = Math.abs(pair.key.hashCode()) % newCapacity;
				newBuckets[index].add(pair);
				numPairs++;
			}
		}
		buckets = newBuckets;
		size = numPairs;
		capacity = newCapacity;
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
		int indexInBuckets;
		int indexInLists;

		public HashMapIterator() {
			indexInBuckets = 0;
			indexInLists = 0;
		}

		public K next() {
			KVPair pair = (KVPair) buckets[indexInBuckets].get(indexInLists);
			indexInLists++;
			if (indexInLists >= buckets[indexInBuckets].size()) {
				indexInLists = 0;
			}
			indexInLists++;
			return pair.key;
		}

		public boolean hasNext() {
			return indexInBuckets < capacity;
		}

		public void remove() {
			// TODO Auto-generated method stub

		}

		public void forEachRemaining(Consumer<? super K> action) {
			// TODO Auto-generated method stub

		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair {
		private K key;
		private V value;

		public KVPair(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

}
