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
	private ArrayList<ArrayList<KVPair>> hashMap;
	private double loadFactor;

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		hashMap = new ArrayList<ArrayList<KVPair>> (DEFAULT_CAPACITY);
		for (int i = 0; i < DEFAULT_CAPACITY; i++) {
			hashMap.add(new ArrayList<KVPair>());
		}
		this.capacity = DEFAULT_CAPACITY;
		this.loadFactor = DEFAULT_LOAD_FACTOR;
		this.size = 0;
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		hashMap = new ArrayList<ArrayList<KVPair>> (initialCapacity);
		for (int i = 0; i < initialCapacity; i++) {
			hashMap.add(new ArrayList<KVPair>());
		}
		this.capacity = initialCapacity;
		this.loadFactor = DEFAULT_LOAD_FACTOR;
		this.size = 0;
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
		hashMap = new ArrayList<ArrayList<KVPair>> (initialCapacity);
		for (int i = 0; i < initialCapacity; i++) {
			hashMap.add(new ArrayList<KVPair>());
		}
		this.capacity = initialCapacity;
		this.loadFactor = loadFactor;
		this.size = 0;
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
		if (key == null) {
			throw new IllegalArgumentException("key cannot be null");
		}
		// TODO Complete this!
		int bucket = key.hashCode() % this.capacity; 
		for (KVPair item: hashMap.get(bucket)) {
			if (item.key.equals(key)) {
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
		// TODO Complete this!
		for (int i = 0; i < this.capacity; i++) {
			for (KVPair item: hashMap.get(i)) {
				if (item.value == null && item.key != null && value == null) {
					return true;
				} 
				if (item.value != null) {
					if (item.value.equals(value)) {
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
		// TODO Complete this!
		int bucket = key.hashCode() % this.capacity;
		for (KVPair item: hashMap.get(bucket)) {
			if (item.key.equals(key)) {
				V v = item.value;
				item.setValue(value);
				return v;
			}
		}
		hashMap.get(bucket).add(new KVPair(key, value));
		this.size++;
		
		if (((double)this.size / this.capacity) > this.loadFactor) {
			expand(this.capacity*2);
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
		// TODO Complete this!
		if (key == null) {
			throw new IllegalArgumentException("Key cannot be null");
		}
		int bucket = key.hashCode() % this.capacity;
		for (KVPair item: hashMap.get(bucket)) {
			if (item.key.equals(key)) {
				V v = item.value;
				hashMap.get(bucket).remove(item);
				this.size--;
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
		int bucket = key.hashCode() % this.capacity;
		for (KVPair item: hashMap.get(bucket)) {
			if (item.key.equals(key)) {
				return item.value;
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
		ArrayList<ArrayList<KVPair>> newMap = new ArrayList<ArrayList<KVPair>>(newCapacity);
		for (int i = 0; i < newCapacity; i++) {
			newMap.add(new ArrayList<KVPair>());
		}
		for (int i = 0; i < capacity(); i++) {
			for (KVPair item: hashMap.get(i)) {
				newMap.get(item.key.hashCode()%newCapacity).add(new KVPair(item.key, item.value));
			}
		}
		this.capacity = newCapacity;
		this.hashMap = newMap;
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
		private int posI;
		private int posJ;

		public HashMapIterator() {
			// TODO Complete this!
			posI = 0;
			posJ = 0;
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			return !(posI == capacity());
		}

		@Override
		public K next() {
			// TODO Complete this!
			for (int i = posI; i < capacity(); i++) {
				for (int j = posJ; j < hashMap.get(i).size(); j++) {
					if (!hashMap.get(i).isEmpty()) {
						K key = hashMap.get(i).get(j).key;
						if (j == hashMap.get(i).size()-1) {
							posI++;
							posJ = 0;
						} else {
							posJ++;
						}
						return key;
					}
				}
				posJ = 0;
				posI++;
			}
			return null;
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
		
		public void setValue(V val) {
			this.value = val;
		}
	}

}
