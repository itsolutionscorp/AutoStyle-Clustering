import java.util.*;

/* YOU ARE NOT allowed to use any library function related to hash maps
 if you feel the need you may use the ArrayList or LinkedList class */

public class MyHashMap<K, V> implements Iterable<K> {
	/* Default size of the map if not set in constructor */
	private static final int DEFAULT_CAPACITY = 30;

	/* Default load factor if not set in constructor */
	private static final double DEFAULT_LOAD_FACTOR = 0.7;

	private int capacity; // the number of buckets in the map
	private int size; // the number of items that have been put into the map
	private ArrayList<ArrayList<KVPair>> buckets;
	private double loadFactor;

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
	 * Constructs an empty map with the given initial capacity and the given load
	 * factor.
	 * 
	 * @param loadFactor
	 *            A fraction greater than 0 and less than 1. Once the size /
	 *            capacity exceeds this number, the map's underlying array
	 *            should expand.
	 */
	public MyHashMap(int initialCapacity, double loadFactor) {
		buckets = new ArrayList<ArrayList<KVPair>>(initialCapacity);
		for (int i = 0; i < initialCapacity; i++)
			buckets.add(new ArrayList<KVPair>());
		capacity = initialCapacity;
		this.loadFactor = loadFactor;
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
		if (key == null) return false;
		ArrayList<KVPair> pairs = buckets.get(key.hashCode() % capacity);
		for (KVPair p : pairs) if (p.key.equals(key)) return true;
		return false;
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		for (ArrayList<KVPair> b : buckets)
			for (KVPair p : b) if (p.value.equals(value)) return true;
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
		if (key == null) throw new InputMismatchException();
		ArrayList<KVPair> pairs = buckets.get(key.hashCode() % capacity);
		for (KVPair p : pairs) {
			if (p.key.equals(key)) {
				V temp = p.value;
				p.value = value;
				return temp;
			}
		}
		pairs.add(new KVPair(key, value));
		size++;
		if (size / capacity > loadFactor) expand((int) (capacity / loadFactor));
		return null;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		if (key == null) return null;
		ArrayList<KVPair> pairs = buckets.get(key.hashCode() % capacity);
		for (int i = 0; i < pairs.size(); i++) {
			if (pairs.get(i).key.equals(key)) {
				size--;
				return pairs.remove(i).value;
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
		if (key == null) return null;
		ArrayList<KVPair> pairs = buckets.get(key.hashCode() % capacity);
		for (KVPair p : pairs) {
			if (p.key.equals(key))
				return p.value;
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
		ArrayList<ArrayList<KVPair>> temp = new ArrayList<ArrayList<KVPair>>(newCapacity);
		for (int i = 0; i < newCapacity; i++)
			temp.add(new ArrayList<KVPair>());
		for (K key : this) {
			temp.get(key.hashCode() % newCapacity).add(new KVPair(key, get(key)));
		}
		buckets = new ArrayList<ArrayList<KVPair>>(temp);
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
		private int bucketNum;
		private int keyNum;
		private int totalNum;
		public HashMapIterator() {
			bucketNum = 0;
			keyNum = -1;
		}

		public boolean hasNext() {
			return totalNum < size;
		}

		public K next() {
			ArrayList<KVPair> pairs = buckets.get(bucketNum);
			if(keyNum >= pairs.size() - 1) {
				bucketNum++;
				keyNum = -1;
				return next();
			}
			keyNum++;
			totalNum++;
			return pairs.get(keyNum).key;
		}
		
		public void remove() {
		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair {
		private K key;
		private V value;
		private KVPair(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

}