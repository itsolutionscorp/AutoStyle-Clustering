import java.util.Iterator;
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
	private double myLoadFactor; // load factor (size/capacity)
	private LinkedList<KVPair>[] myMap;

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		capacity = DEFAULT_CAPACITY;
		myLoadFactor = DEFAULT_LOAD_FACTOR;
		size = 0;
		myMap = new LinkedList[capacity];
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		capacity = initialCapacity;
		myLoadFactor = DEFAULT_LOAD_FACTOR;
		size = 0;
		myMap = new LinkedList[capacity];
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
		myLoadFactor = loadFactor;
		size = 0;
		myMap = new LinkedList[capacity];
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
		int index = key.hashCode() % capacity;
		LinkedList<KVPair> temp = myMap[index];
		int i = 0;
		while ((temp.get(i) != null) && (i < temp.size())) {
			if ((temp.get(i).key).equals(key)) {
				return true;
			}
			i++;
		}
		return false;
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		// TODO Complete this!
		for (int index = 0; index < capacity; index++) {
			int i = 0;
			LinkedList<KVPair> temp = myMap[index];
			while ((temp.get(i) != null) && (i < temp.size())) {
				if ((temp.get(i).value).equals(value)) {
					return true;
				}
				i++;
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
		if ((size() + 1)/capacity() > myLoadFactor) {
			expand((int) (capacity() + (capacity() * 0.5)));
		}
		
		int index = key.hashCode() % capacity;
		if (containsKey(key)) {
			V tempValue = get(key);
			for (KVPair p: myMap[index]) {
				if (p.key == key) {
					p.value = value;
				}
			}
			return tempValue;
		}
		else {
			myMap[index].add(new KVPair(key, value)); 
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
		// TODO Complete this!
		int index = key.hashCode() % capacity;
		if (containsKey(key)) {
			V tempValue = get(key);
			for (KVPair p: myMap[index]) {
				if (p.key == key) {
					myMap[index].remove(p);
				}
			}
			return tempValue;
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		// TODO Complete this!
		int index = key.hashCode() % capacity;
		for (KVPair p: myMap[index]) {
			if (p.key.equals(key)) {
				return p.value;
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
		LinkedList<KVPair>[] expanded = new LinkedList[newCapacity];
		for (LinkedList<KVPair> p: myMap) {
			for(KVPair pair: p) {
				int index = (pair.key.hashCode()) % newCapacity;
				expanded[index].add(pair);
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

		private int hashIndex;
		private int KVPIndex;
		
		public HashMapIterator() {
			hashIndex = -1;
			KVPIndex = -1;
		}

		@Override
		public boolean hasNext() {
			if (hashIndex < capacity) {
				return (KVPIndex < myMap[hashIndex].size());
			}
			return false;
		}

		@Override
		public K next() {
			if (KVPIndex >= myMap[hashIndex].size()) {
				KVPIndex = 0;
				hashIndex++;
			} else {
				KVPIndex++;
			}
			return myMap[hashIndex].get(KVPIndex).key;
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
		
		private KVPair(K key1, V value1) {
			key = key1;
			value = value1;
		}
		
	}
	
	

}
