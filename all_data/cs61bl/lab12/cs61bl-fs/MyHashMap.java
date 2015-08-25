import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;

/* YOU ARE NOT allowed to use any library function related to hash maps
 if you feel the need you may use the ArrayList or LinkedList class */

public class MyHashMap<K, V> implements Iterable<K> {
	/* Default size of the map if not set in constructor */
	private static final int DEFAULT_CAPACITY = 30;

	/* Default load factor if not set in constructor */
	private static final double DEFAULT_LOAD_FACTOR = 0.7;

	private int capacity = DEFAULT_CAPACITY; // the number of buckets in the map
	private int size = 0; // the number of items that have been put into the map

	public LinkedList<KVPair>[] map;
	private double loadFactor = DEFAULT_LOAD_FACTOR;

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		map = new LinkedList[DEFAULT_CAPACITY];
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		if (initialCapacity < 0) {
			throw new IllegalArgumentException();
		}
		map = new LinkedList[initialCapacity];
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
		if (initialCapacity < 0 || loadFactor < 0 || loadFactor > 1) {
			throw new IllegalArgumentException();
		}
		map = new LinkedList[initialCapacity];
		capacity = initialCapacity;
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
		if (key == null) {
			throw new IllegalArgumentException();
		}
		LinkedList<KVPair> bucket = map[key.hashCode() % capacity];
		for (KVPair pair: bucket) {
			if (pair.key.equals(key)) {
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
		if (value == null) {
			throw new IllegalArgumentException();
		}
		for (LinkedList<KVPair> bucket: map) {
			if (bucket != null) {
				for (KVPair pair: bucket) {
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
		if (key == null || value == null) {
			throw new IllegalArgumentException();
		}
		V result = value;
		int index = key.hashCode() % capacity;
		LinkedList<KVPair> bucket = map[index];
		if (bucket == null) {
			map[index] =  new LinkedList<KVPair>();
			map[index].add(new KVPair(key, value));
			size++;
		}
		else if (containsKey(key)) {
			for (KVPair pair: bucket) {
				if (pair.key.equals(key)) {
					V previous = pair.value;
					pair.value = value;
					result = previous;
				}
			}
		}
		else {
			map[index].add(new KVPair(key, value));
			size++;
		}
		
		if (size/capacity > loadFactor) {
			expand(capacity*2);	
		}
		
		return result;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		int index = key.hashCode() % capacity;
		LinkedList<KVPair> bucket = map[index];
		if (bucket == null) {
			return null;
		}
		else {
			int counter = 0;
			for (KVPair pair: bucket) {
				if (pair.key.equals(key)) {
					V result = pair.value;
					bucket.remove(counter);
					size--;
					return result;
				}
				counter++;
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
		if (key == null) {
			throw new IllegalArgumentException();
		}
		
		int index = key.hashCode() % capacity;
		LinkedList<KVPair> bucket = map[index];
		if (bucket == null) {
			return null;
		}
		else {
			for (KVPair pair: bucket) {
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
		LinkedList<KVPair>[] expanded = new LinkedList[newCapacity];
		for (LinkedList<KVPair> bucket: map) {
			if (bucket != null) {
				for (KVPair pair: bucket) {
					int index = pair.key.hashCode() % newCapacity;
					if (expanded[index] == null) {
						expanded[index] = new LinkedList<KVPair>();
					}
					expanded[index].add(pair);
				}
			}
		}
		map = expanded;
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
		
		private int arrayIndex;
		private int bucketIndex;

		public HashMapIterator() {
			arrayIndex = 0;
			bucketIndex = 0;
			while (map[arrayIndex] == null) {
				arrayIndex++;
			}
		}

		@Override
		public boolean hasNext() {
			if (bucketIndex < map[arrayIndex].size()) {
				return true;
			}
			else {
				while (arrayIndex < map.length) {
					if (map[arrayIndex] != null) {
						bucketIndex = 0;
						return true;
					}
					arrayIndex++;
				}
			}
			return false;
		}

		@Override
		public K next() {
			K result = null;
			if (bucketIndex < map[arrayIndex].size()) {
				result = map[arrayIndex].get(bucketIndex).key;
			}
			bucketIndex++;
			return result;
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
		public KVPair next;
		
		KVPair(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
}
