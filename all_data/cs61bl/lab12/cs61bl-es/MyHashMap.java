import java.util.Iterator;
import java.util.ArrayList;
import java.util.LinkedList;

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
	ArrayList<LinkedList<KVPair>> hashMap;
	
	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		this.capacity = DEFAULT_CAPACITY;
		this.size = 0;
		this.loadFactor = DEFAULT_LOAD_FACTOR;
		this.hashMap = new ArrayList<LinkedList<KVPair>>(this.capacity);
		for (int i = 0; i < this.capacity; i++) {
			hashMap.add(new LinkedList<KVPair>());
		}
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		this.capacity = initialCapacity;
		this.size = 0;
		this.loadFactor = DEFAULT_LOAD_FACTOR;
		this.hashMap = new ArrayList<LinkedList<KVPair>>(this.capacity);
		for (int i = 0; i < this.capacity; i++) {
			hashMap.add(new LinkedList<KVPair>());
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
		this.capacity = initialCapacity;
		this.size = 0;
		this.loadFactor = loadFactor;
		this.hashMap = new ArrayList<LinkedList<KVPair>>(this.capacity);
		for (int i = 0; i < this.capacity; i++) {
			hashMap.add(new LinkedList<KVPair>());
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
		LinkedList<KVPair> currentBucket = hashMap.get(key.hashCode() % capacity());
		for (int i = 0; i < currentBucket.size(); i++) {
			KVPair currentPair = currentBucket.get(i);
			if (currentPair.getKey() == key) {
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
		for (int i = 0; i < capacity(); i++) {
			LinkedList<KVPair> currentBucket = hashMap.get(i);
			for (int j = 0; j < currentBucket.size(); j++) {
				KVPair currentPair = currentBucket.get(j);
				if (currentPair.getValue() == value) {
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
		if (!this.containsKey(key)) {
			if ((size() + 1) / capacity() > this.loadFactor) {
				expand(capacity() * 2);
			}
			LinkedList<KVPair> currentBucket = hashMap.get(key.hashCode() % capacity);
			currentBucket.add(new KVPair(key, value));
			this.size++;
			return null;
		}
		else {
			LinkedList<KVPair> currentBucket = hashMap.get(key.hashCode() % capacity);
			V rtn = null;
			for (int i = 0; i < currentBucket.size(); i++) {
				KVPair currentPair = currentBucket.get(i);
				if (currentPair.getKey() == key) {
					rtn = currentPair.getValue();
					currentPair.setValue(value);
					break;
				}
			}
			return rtn;
		}
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		V rtn = null;
		LinkedList<KVPair> currentBucket = hashMap.get(key.hashCode() % capacity);
		for (int i = 0; i < currentBucket.size(); i++) {
			KVPair currentPair = currentBucket.get(i);
			if (currentPair.getKey() == key) {
				rtn = currentPair.getValue();
				currentBucket.remove(i);
				size--;
				break;
			}
		}
		return rtn;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		LinkedList<KVPair> currentBucket = hashMap.get(key.hashCode() % capacity);
		for (int i = 0; i < currentBucket.size(); i++) {
			KVPair currentPair = currentBucket.get(i);
			if (currentPair.getKey() == key) {
				return currentPair.getValue();
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
		this.capacity = newCapacity;
		this.size = 0;
		ArrayList<LinkedList<KVPair>> newHashMap = new ArrayList<LinkedList<KVPair>>(capacity());
		for (int i = 0; i < this.capacity; i++) {
			newHashMap.add(new LinkedList<KVPair>());
		}
		ArrayList<LinkedList<KVPair>> oldHashMap = this.hashMap;
		this.hashMap = newHashMap;
		for (int i = 0; i < oldHashMap.size(); i++) {
			LinkedList<KVPair> currentBucket = oldHashMap.get(i);
			for (int j = 0; j < currentBucket.size(); j++) {
				KVPair currentPair = currentBucket.get(j);
				K key = currentPair.getKey();
				V value = currentPair.getValue();
				put(key, value);
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
		int currentHashValue;
		LinkedList<KVPair> currentBucket;
		int currentBucketIndex;
		int index;
		
		public HashMapIterator() {
			currentHashValue = 0;
			currentBucket = hashMap.get(currentHashValue);
			while (currentBucketIndex >= currentBucket.size() && currentHashValue + 1 < hashMap.size()) {
				currentBucketIndex = 0;
				currentHashValue++;
				currentBucket = hashMap.get(currentHashValue);
			}
			currentBucketIndex = 0;
			index = 0;
		}

		@Override
		public boolean hasNext() {
			return index < size();
		}

		@Override
		public K next() {
			K rtn = currentBucket.get(currentBucketIndex).getKey();
			currentBucketIndex++;
			while (currentBucketIndex >= currentBucket.size() && currentHashValue + 1 < hashMap.size()) {
				currentBucketIndex = 0;
				currentHashValue++;
				currentBucket = hashMap.get(currentHashValue);
			}
			index++;
			return rtn;
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
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
		
		public void setKey(K newKey) {
			this.key = newKey;
		}
		
		public void setValue(V newValue) {
			this.value = newValue;
		}
	}

}
