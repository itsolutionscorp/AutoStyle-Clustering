import java.util.Iterator;
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

	private double loadFactor; // the Load FACTOR
	private ArrayList <ArrayList<KVPair>> dict;

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		capacity = DEFAULT_CAPACITY;
		size = 0;
		loadFactor = DEFAULT_LOAD_FACTOR;
		dict = new ArrayList<ArrayList<KVPair>>(capacity);
		for (int i = 0; i < capacity; i++) {
			dict.add(new ArrayList<KVPair>());
		}
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		capacity = initialCapacity;
		size = 0;
		loadFactor = DEFAULT_LOAD_FACTOR;
		dict = new ArrayList<ArrayList<KVPair>>(capacity);
		for (int i = 0; i < capacity; i++) {
			dict.add(new ArrayList<KVPair>());
		}
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
		this.loadFactor = loadFactor;
		capacity = initialCapacity;
		size = 0;
		dict = new ArrayList<ArrayList<KVPair>>(capacity);
		for (int i = 0; i < capacity; i++) {
			dict.add(new ArrayList<KVPair>());
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
		int hash = key.hashCode() & capacity;
		if (dict.get(hash) == null) {
			return false;
		}
		for (int i = 0; i < dict.size(); i++) {
			if (dict.get(hash).get(i).getKey().equals(key)) {
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
		for (int h = 0; h < dict.size(); h++) {
			if (dict.get(h) != null) {
				for (int i = 0; i < dict.get(h).size(); i++) {
					if (dict.get(h).get(i).getValue().equals(value)) {
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
		if (capacity == size * loadFactor) {
			expand(capacity * 2);
		}
		int hash = key.hashCode() % capacity;
		ArrayList<KVPair> h = dict.get(hash);
		if (!containsKey(key)) {
			return null;
		}
		for (int i = 0; i <h.size(); i++) {
			if (h.get(i).getKey().equals(key)) {
				V ret = h.get(i).getValue();
				h.remove(i);
				size--;
				return ret;
			}
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
		int hash = key.hashCode() % capacity;
		ArrayList<KVPair> h = dict.get(hash);
		if (!containsKey(key)) {
			return null;
		}
		for (int i = 0; i < h.size(); i++) {
			if (h.get(i).getKey().equals(key)) {
				V ret = h.get(i).getValue();
				h.remove(i);
				size--;
				return ret;
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
		int hash = key.hashCode() % capacity;
		ArrayList<KVPair> h = dict.get(hash);
		if (!containsKey(key)) {
			return null;
		}
		for (int i = 0; i< h.size(); i++) {
			if (h.get(i).getKey().equals(key)) {
				return h.get(i).getValue();
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
		for (int i = 0; i < (newCapacity - capacity); i++) {
			dict.add(new ArrayList<KVPair>());
		}
		capacity = newCapacity;
		for (int j = 0; j < dict.size(); j++) {
			if (dict.get(j) != null) {
				for (int i = 0; i < dict.get(j).size(); i++) {
					put(dict.get(j).get(i).getKey(), dict.get(j).get(i).getValue());
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
		private int count;
		private int index;
		private int buckets;
		
		public HashMapIterator() {
			count = 0;
			buckets = 0;
		}

		@Override
		public boolean hasNext() {
			if (count < size) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public K next() {
			while (dict.get(buckets) == null) {
				buckets++;
			}
			K ret = dict.get(buckets).get(index).getKey();
			if (index >= dict.get(buckets).size()) {
				index = 0;
				buckets++;
			}
			index++;
			return ret;
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
		
		public K getKey() {
			return key;
		} 
		
		public V getValue() {
			return value;
		}
	}

}
