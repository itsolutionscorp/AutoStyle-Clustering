import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;

/* YOU ARE NOT allowed to use any library function related to hash maps
 if you feel the need you may use the ArrayList or LinkedList class */

public class MyHashMap<K, V> implements Iterable<K> {
	/* Default size of the map if not set in constructor */
	private static final int DEFAULT_CAPACITY = 30;

	/* Default load factor if not set in constructor */
	private static final double DEFAULT_LOAD_FACTOR = 0.7;

	private int capacity; // the number of buckets in the map
	private int size; // the number of items that have been put into the map
	private double load; // the load factor before expansion occurs
	private ArrayList<LinkedList<KVPair>> buckets;

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
		this(DEFAULT_CAPACITY, initialCapacity);
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
		load = loadFactor;
		buckets = new ArrayList<LinkedList<KVPair>>(capacity);
		for (int elm = 0; elm < capacity; elm++) {
			buckets.add(new LinkedList<KVPair>());
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
		if (key == null) {return false;}
		int b = Math.abs(key.hashCode() % capacity);
		LinkedList<KVPair> bucket = buckets.get(b);
		for (KVPair elm: bucket) {
			if (elm.myKey.equals(key)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		for (LinkedList<KVPair> bucket: buckets) {
			for (KVPair elm: bucket) {
				if (get(elm.myKey) == value) {
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
		int b = Math.abs(key.hashCode() % capacity);
		LinkedList<KVPair> bucket = buckets.get(b);
		for (KVPair elm: bucket) {
			if (elm.myKey.equals(key)) {
				bucket.remove(elm);
				bucket.addLast(new KVPair(key, value));
				return elm.myValue;
			}
		}
		bucket.addLast(new KVPair(key, value));
		size++;
		if ((double)size/capacity > load) {
			expand(capacity*2);
		}
		return null;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 *
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		int b = Math.abs(key.hashCode() % capacity);
		LinkedList<KVPair> bucket = buckets.get(b);
		for (KVPair elm: bucket) {
			if (elm.myKey.equals(key)) {
				bucket.remove(elm);
				size--;
				return elm.myValue;
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
		int b = Math.abs(key.hashCode() % capacity);
		LinkedList<KVPair> bucket = buckets.get(b);
		for (KVPair elm: bucket) {
			if (elm.myKey.equals(key)) {
				return elm.myValue;
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
		ArrayList<LinkedList<KVPair>> newarray = new ArrayList<LinkedList<KVPair>>(capacity);
		ArrayList<LinkedList<KVPair>> oldarray = buckets;
		buckets = newarray;
		for (int elm = 0; elm < capacity; elm++) {
			buckets.add(new LinkedList<KVPair>());
		}
		for (LinkedList<KVPair> bucket: oldarray) {
			for (KVPair elm: bucket) {
				put(elm.myKey, elm.myValue);
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

		int currentBucket;
		int currentElement;
		int arraySize;
		int bucketSize;

		public HashMapIterator() {
			currentBucket = 0;
			currentElement = 0;
			arraySize = capacity;
			bucketSize = buckets.get(0).size();
		}

		@Override
		public boolean hasNext() {
			return currentBucket != arraySize;
		}

		@Override
		public K next() {
			K toreturn = buckets.get(currentBucket).get(currentElement).myKey;
			if (currentElement == bucketSize - 1) {
				currentElement = 0;
				currentBucket++;
			} else {
				currentElement++;
			}
			return toreturn;
		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair {
		private K myKey;
		private V myValue;

		public KVPair(K key, V value) {
			myKey = key;
			myValue = value;
		}

	}

}
