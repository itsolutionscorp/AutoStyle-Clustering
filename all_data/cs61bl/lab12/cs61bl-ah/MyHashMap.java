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
	private int tracker = -1; // NSA use only. Keeps track of greatest index at
								// which keys are stored.
	private double load_factor; // the threshold at which the array should
								// expand
	private ArrayList<KVPair>[] blah; // the master array for the HashMap

	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		capacity = DEFAULT_CAPACITY;
		load_factor = DEFAULT_LOAD_FACTOR;
		blah = new ArrayList[capacity];
		for (int i = 0; i < capacity; i++)
			blah[i] = new ArrayList<KVPair>();
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		if (initialCapacity > 0) {
			capacity = initialCapacity;
		} else {
			capacity = DEFAULT_CAPACITY;
		}
		load_factor = DEFAULT_LOAD_FACTOR;
		blah = new ArrayList[capacity];
		for (int i = 0; i < capacity; i++)
			blah[i] = new ArrayList<KVPair>();
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
		if (initialCapacity > 0 && loadFactor > 0 && loadFactor < 1) {
			capacity = initialCapacity;
			load_factor = loadFactor;
		} else {
			capacity = DEFAULT_CAPACITY;
			load_factor = DEFAULT_LOAD_FACTOR;
		}
		blah = new ArrayList[capacity];
		for (int i = 0; i < capacity; i++)
			blah[i] = new ArrayList<KVPair>();
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
		return false;
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		// TODO Complete this!
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
		KVPair object = new KVPair(key, value);
		int index = hashHelper(key);
		if (!blah[index].isEmpty()) {
			for (KVPair pair : blah[index]) {
				if (pair.getKey().equals(key)) {
					int i = blah[index].indexOf(pair);
					return blah[index].set(i, object).getValue();
				}
			}
		}
		blah[index].add(object);
		size++;
		tracker=Math.max(index,tracker);
		if (size > capacity * load_factor) {
			expand(capacity * 2);
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
		int index = hashHelper(key);
		if (!blah[index].isEmpty()) {
			for (KVPair p : blah[index])
				if (p.getKey().equals(key)) {
					V val = p.getValue();
					blah[index].remove(p);
					return val;
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
		int index = hashHelper(key);
		if (!blah[index].isEmpty()) {
			for (KVPair p : blah[index])
				if (p.getKey().equals(key))
					return p.getValue();
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
		ArrayList<KVPair> temp = new ArrayList<KVPair>();
		for (int i = 0; i < capacity; i++) {
			for (KVPair pair : blah[i]) {
				temp.add(pair);
			}
		}
		blah = new ArrayList[newCapacity];
		capacity = newCapacity;
		size = 0;
		for (int i = 0; i < capacity; i++)
			blah[i] = new ArrayList<KVPair>();
		for (KVPair pair : temp) {
			this.put(pair.getKey(), pair.getValue());
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
		private int index, bucketIndex;

		public HashMapIterator() {
			index = 0;
			bucketIndex = 0;
		}

		@Override
		public boolean hasNext() {
			// boolean b =
			// !(index==capacity-1)&&!(bucketIndex==blah[index].size()-1);
			return (index<=tracker)&&bucketIndex<blah[tracker].size();
		}

		@Override
		public K next() {

			try {
				
				if (bucketIndex >= blah[index].size()) {
					index++;
					bucketIndex = 0;
				} 
				K key = blah[index].get(bucketIndex).getKey();
				bucketIndex++;
				return key;
			} catch (NullPointerException e) {
				index++;
				bucketIndex = 0;
				return next();
			}
		}

		@Override
		public void remove() {
			// n00t n00t

		}
	}

	private int hashHelper(K key) {
		int string = key.toString().getBytes()[0];
		string = Math.abs(string % capacity - 1);
		System.out.println(string);
		return string;
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

		public V getValue() {
			return value;
		}

		public K getKey() {
			return key;
		}
	}

}
