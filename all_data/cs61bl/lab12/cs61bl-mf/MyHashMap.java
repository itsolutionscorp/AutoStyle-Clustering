import java.util.ArrayList;
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
	private double load;
	private LinkedList<KVPair>[] hashArray;

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		capacity = DEFAULT_CAPACITY;
		size = 0;
		load = DEFAULT_LOAD_FACTOR;
		hashArray = new LinkedList[capacity];
		for (int index = 0; index < hashArray.length; index++) {
			hashArray[index] = new LinkedList<KVPair>();
		}
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		capacity = initialCapacity;
		size = 0;
		load = DEFAULT_LOAD_FACTOR;
		hashArray = new LinkedList[capacity];
		for (int index = 0; index < hashArray.length; index++) {
			hashArray[index] = new LinkedList<KVPair>();
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
		capacity = initialCapacity;
		size = 0;
		load = loadFactor;
		hashArray = new LinkedList[capacity];
		for (int index = 0; index < hashArray.length; index++) {
			hashArray[index] = new LinkedList<KVPair>();
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
		int hash = key.hashCode() % capacity;
		LinkedList<KVPair> targetList = hashArray[hash];
		for (int targetIndex = 0; targetIndex < targetList.size(); targetIndex++) {
			if (targetList.get(targetIndex).key() == key)
				return true;
		}
		return false;
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		for (int hashIndex = 0; hashIndex < capacity; hashIndex++) {
			LinkedList<KVPair> targetList = hashArray[hashIndex];
			for (int targetIndex = 0; targetIndex < targetList.size(); targetIndex++) {
				if (targetList.get(targetIndex).value() == value)
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
		int hash = key.hashCode() % capacity;
		LinkedList<KVPair> targetList = hashArray[hash];
		for (int targetIndex = 0; targetIndex < targetList.size(); targetIndex++) {
			if (targetList.get(targetIndex).key() == key) {
				V rtn = targetList.get(targetIndex).value();
				targetList.get(targetIndex).setValue(value);
				return rtn;
			}
		}
		targetList.addFirst(new KVPair(key, value));
		size++;
		if (((double) size / capacity) >= load) {
			expand(2 * capacity);
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
		LinkedList<KVPair> targetList = hashArray[hash];
		for (int targetIndex = 0; targetIndex < targetList.size(); targetIndex++) {
			if (targetList.get(targetIndex).key() == key) {
				size--;
				return targetList.remove(targetIndex).value();
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
		LinkedList<KVPair> targetList = hashArray[hash];
		for (int targetIndex = 0; targetIndex < targetList.size(); targetIndex++) {
			if (targetList.get(targetIndex).key() == key)
				return targetList.get(targetIndex).value();
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
		ArrayList<K> kList = new ArrayList<K>();
		ArrayList<V> vList = new ArrayList<V>();
		Iterator iter = new HashMapIterator();
		while (iter.hasNext()) {
			K currentK = (K) iter.next();
			kList.add(currentK);
			vList.add(get(currentK));
		}
		capacity = newCapacity;
		hashArray = new LinkedList[capacity];
		size = 0;
		for (int index = 0; index < hashArray.length; index++) {
			hashArray[index] = new LinkedList<KVPair>();
		}
		for (int index = 0; index < kList.size(); index++) {
			put(kList.get(index), vList.get(index));
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

		int arrayIndex;
		int listIndex;

		public HashMapIterator() {
			arrayIndex = 0;
			listIndex = 0;
		}

		@Override
		public boolean hasNext() {
			if (arrayIndex >= size)
				return false;
			if (listIndex < hashArray[arrayIndex].size()) {
				return true;
			} else {
				for (int testIndex = arrayIndex + 1; testIndex < capacity; testIndex++) {
					if (!hashArray[testIndex].isEmpty()) {
						return true;
					}

				}
			}
			return false;
		}

		@Override
		public K next() {
			LinkedList<KVPair> temp = hashArray[arrayIndex];
			while (temp.isEmpty()) {
				arrayIndex++;
				temp = hashArray[arrayIndex];
			}
			K rtn = hashArray[arrayIndex].get(listIndex).key();
			listIndex++;
			if (listIndex >= hashArray[arrayIndex].size()) {
				arrayIndex++;
				listIndex = 0;
			}
			return rtn;
		}

		@Override
		public void remove() {

		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair {
		private K key;
		private V value;

		public KVPair(K newKey, V newValue) {
			key = newKey;
			value = newValue;
		}

		public K key() {
			return key;
		}

		public V value() {
			return value;
		}

		public void setValue(V newValue) {
			value = newValue;
		}

		public String toString() {
			return new String("key: " + key + ", value " + value);
		}
	}
}
