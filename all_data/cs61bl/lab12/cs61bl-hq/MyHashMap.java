import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

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
	ArrayList<LinkedList<KVPair>> map;
	private double loadFactor;

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		map = new ArrayList<LinkedList<KVPair>>(DEFAULT_CAPACITY);
		capacity = DEFAULT_CAPACITY;
		loadFactor = DEFAULT_LOAD_FACTOR;
		for (int i = 0; i < capacity; i++) {
			map.add(i, new LinkedList<KVPair>());
		}		
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		map = new ArrayList<LinkedList<KVPair>>(initialCapacity);
		capacity = initialCapacity;
		loadFactor = DEFAULT_LOAD_FACTOR;
		for (int i = 0; i < capacity; i++) {
			map.add(i, new LinkedList<KVPair>());
		}
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
		if (loadFactor <= 0 || loadFactor >= 1) {
			throw new IllegalArgumentException();
		}
		map = new ArrayList<LinkedList<KVPair>>(initialCapacity);
		this.loadFactor = loadFactor;
		capacity = initialCapacity;
		for (int i = 0; i < capacity; i++) {
			map.add(i, new LinkedList<KVPair>());
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
		if (key == null) {
			throw new IllegalStateException("Key cannot be null.");
		}
		int keyIndex = Math.abs(key.hashCode() % capacity());
		for (KVPair k : map.get(keyIndex)) {
			if (k.key == key || k.key.equals(key)) {
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
		while (map.iterator().hasNext()) {
			if (get(iterator().next()) == value) {
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
		KVPair toPut = new KVPair(key, value);
		KVPair prev = new KVPair(null, null);
		if (containsKey(key)) {
			int index = 0;
			for (KVPair k : map.get(toPut.keyIndex)) {
				if (k.key == key) {
					prev = k;
					map.get(toPut.keyIndex).set(index, toPut);
					break;
				}
				index++;
			}
		} else {
			map.get(toPut.keyIndex).add(toPut);
			size++;
		}
		if (size > capacity * loadFactor) {
			expand(capacity * 2);
		}
		return prev.value;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		KVPair toRemove = null;
		int keyIndex = Math.abs(key.hashCode() % capacity());
		if (containsKey(key)) {
			for (KVPair k : map.get(keyIndex)) {
				if (k.key == key) {
					toRemove = k;
					map.get(keyIndex).remove(toRemove);
					size--;
					break;
				}
			}
		}
		return toRemove.value;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		if (key != null) {
			int keyIndex = Math.abs(key.hashCode() % capacity());
			KVPair want = null;
			if (containsKey(key)) {
				for (KVPair k : map.get(keyIndex)) {
					if (k.key == key) {
						want = k;
					}
				}
			}
			return want.value;
		} else {
			return null;
		}
	}

	/**
	 * Expands the underlying array to the given capacity.
	 * 
	 * ALSO, rehashes all of the items into this new array. Items will likely
	 * end up in different buckets after the expansion.
	 */
	private void expand(int newCapacity) {
		ArrayList<LinkedList<KVPair>> bigger = new ArrayList<LinkedList<KVPair>>(newCapacity);
		for (int i = 0; i < capacity; i++) {
			bigger.add(i, new LinkedList<KVPair>());
		}	
		while (iterator().hasNext()) {
			bigger.get(Math.abs(iterator().next().hashCode() % newCapacity)).add(((MyHashMap<K, V>.HashMapIterator) iterator()).getKV());
		}
		map = bigger;
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
		
		int arrayIndex;
		int linkIndex;
		LinkedList<KVPair> currentList;
		KVPair currentKV;
		
		public HashMapIterator() {
			arrayIndex = 0;
			linkIndex = 0;
			currentList = map.get(0);
		}

		@Override
		public boolean hasNext() {
			return arrayIndex < capacity();
		}

		@Override
		public K next() {
			K nextKey = null;
			if (hasNext()) {
				if (linkIndex == (currentList.size())) {
					arrayIndex++;
					currentList = map.get(arrayIndex);
					linkIndex = 0;
					while (currentList.size() == 0) {
						arrayIndex++;
						currentList = map.get(arrayIndex);
					}
					currentKV = currentList.get(linkIndex);
					nextKey = currentKV.getKey();
				} else {
					currentKV = currentList.get(linkIndex);
					nextKey = currentKV.getKey();
					linkIndex++;
				}
			}
			return nextKey;
		}
		
		public KVPair getKV() {
			return currentKV;
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
		private int keyIndex;
		
		public KVPair(K key, V value) {
			this.key = key;
			this.value = value;
			if (key != null) {
				keyIndex = Math.abs(key.hashCode() % capacity());
			}
		}
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
	}

}
