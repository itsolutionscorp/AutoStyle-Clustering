import java.util.Iterator;
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

	// TODO You may declare new instance variables here
	public double myloadFactor;
	private ArrayList<KVPair>[] bucketArray;

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		myloadFactor = MyHashMap.DEFAULT_LOAD_FACTOR;
		capacity = MyHashMap.DEFAULT_CAPACITY;
		size = 0;
		bucketArray = new ArrayList[capacity];
		// TODO Complete this!
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		myloadFactor = MyHashMap.DEFAULT_LOAD_FACTOR;
		capacity = initialCapacity;
		size = 0;
		bucketArray = new ArrayList[capacity];
		// TODO Complete this!
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
		myloadFactor = loadFactor;
		capacity = initialCapacity;
		size = 0;
		bucketArray = new ArrayList[capacity];
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
		if (bucketArray[key.hashCode() % capacity] != null) {
			for (KVPair item : bucketArray[key.hashCode() % capacity]) {
				if (item.getKey().equals(key)) {
					return true;
				}
			}
			return false;
		} else {
			return false;
		}
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		for (int i = 0; i < bucketArray.length; i++) {
			if (bucketArray[i] != null) {
				for (KVPair pair : bucketArray[i]) {
					if (pair.getValue() == value) {
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
		int hash = key.hashCode() % capacity;
		KVPair pair = new KVPair(key, value);
		if ((((double) size + (double) 1) / ((double) capacity)) > myloadFactor) {
			this.expand(2 * capacity);
		}
		if (!containsKey(key)) {
			if (bucketArray[hash] == null) {
				bucketArray[hash] = new ArrayList<KVPair>();
			}
			bucketArray[hash].add(pair);
			size++;
			return null;
		} else {
			for (KVPair current : bucketArray[hash]) {
				if (current.getKey().equals(key)) {
					V rtn = current.getValue();
					current.updateValue(value);
					return rtn;
				}
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
		if (!containsKey(key)) {
			throw new IllegalArgumentException(key.toString() + " not in map.");
		} else {
			ArrayList<KVPair> thisBucket = bucketArray[key.hashCode()
					% capacity];
			int index = 0;
			for (KVPair pair : thisBucket) {
				if (pair.getKey() == key) {
					V rtn = pair.getValue();
					thisBucket.remove(index);
					size--;
					return rtn;
				} else {
					index++;
				}
			}
			return null;
		}
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		if (containsKey(key)) {
			for (KVPair pair : bucketArray[key.hashCode() % capacity]) {
				if (pair.getKey().equals(key)) {
					return pair.getValue();
				}
			}
		} else {
			throw new IllegalArgumentException(key.toString() + " not in map.");
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
		ArrayList<KVPair>[] newArray = new ArrayList[newCapacity];
		for (ArrayList<KVPair> bucket : bucketArray) {
			if (bucket != null) {
				for (KVPair pair : bucket) {
					int newHash = pair.getKey().hashCode() % newCapacity;
					if (newArray[newHash] == null) {
						newArray[newHash] = new ArrayList<KVPair>();
					}
					newArray[newHash].add(pair);
				}
			}
		}
		capacity = newCapacity;
		bucketArray = newArray;
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
		private int bucketIndex;
		private int arrayIndex;
		private int total;

		public HashMapIterator() {
			// TODO Complete this!
			total = 0;
			arrayIndex = 0;
			bucketIndex = 0;
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			return (total < size());
		}

		@Override
		public K next() {
			while (bucketArray[bucketIndex] == null) {
				bucketIndex++;
			}
			K key = (bucketArray[bucketIndex].get(arrayIndex)).getKey();
			arrayIndex++;
			total++;
			if (arrayIndex == bucketArray[bucketIndex].size()) {
				arrayIndex = 0;
				bucketIndex++;
			}
			return key;
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

		public KVPair(K first, V second) {
			key = first;
			value = second;
		}

		public KVPair() {
			key = null;
			value = null;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public void updateValue(V val) {
			value = val;
		}
	}

}
