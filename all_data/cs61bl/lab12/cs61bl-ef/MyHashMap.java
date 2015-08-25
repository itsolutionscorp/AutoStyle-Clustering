import java.util.LinkedList;
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
	private double myLoadFactor;
	private LinkedList<KVPair>[] myHashMap;

	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		capacity = DEFAULT_CAPACITY;
		myLoadFactor = DEFAULT_LOAD_FACTOR;
		myHashMap = new LinkedList[capacity];
		for (int i = 0; i < myHashMap.length; i++) {
			myHashMap[i] = new LinkedList();
		}
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		myLoadFactor = DEFAULT_LOAD_FACTOR;
		capacity = initialCapacity;
		myHashMap = new LinkedList[capacity];
		for (int i = 0; i < myHashMap.length; i++) {
			myHashMap[i] = new LinkedList();
		}
	}

	/**
	 * Constructs an empty map with the given initial capacity and the given
	 * load factor.
	 * 
	 * @param loadFactor
	 *            A fraction greater than 0 and less than 1. Once the size /
	 *            capacity exceeds this number, the map's underlying array
	 *            should expand.
	 */
	public MyHashMap(int initialCapacity, double loadFactor) {
		capacity = initialCapacity;
		myLoadFactor = loadFactor;
		myHashMap = new LinkedList[capacity];
		for (int i = 0; i < myHashMap.length; i++) {
			myHashMap[i] = new LinkedList();
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
		int index = key.hashCode() % capacity;
		for (KVPair p : myHashMap[index]) {
			if (p.getKey().equals(key)) {
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
		int counter = 0;
		int myArrayIndex = 0;
		int myListIndex = 0;
		while (counter != size - 1) {
			while (myHashMap[myArrayIndex].isEmpty()) {
				myArrayIndex++;
			}
			V temp = myHashMap[myArrayIndex].get(myListIndex).getValue();
			if (temp.equals(value))
				return true;
			myListIndex++;
			if (myListIndex == myHashMap[myArrayIndex].size()) {
				myListIndex = 0;
				myArrayIndex++;
			}
			counter++;
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
		if ((double) size / capacity >= (double) myLoadFactor) {
			capacity *= 2;
			this.expand(capacity);
		}
		int index = key.hashCode() % capacity;
		V temp;
		for (KVPair p : myHashMap[index]) {
			if (p.getKey().equals(key)) {
				temp = p.getValue();
				p.setValue(value);
				return temp;
			}
		}
		myHashMap[index].add(new KVPair(key, value));
		size++;
		return null;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map. numberToA Returns the value associated
	 * with the key, or null if there was no key.
	 */
	public V remove(K key) {
		int index = key.hashCode() % capacity;
		V temp;

		for (KVPair p : myHashMap[index]) {
			if (p.getKey().equals(key)) {
				temp = p.getValue();
				myHashMap[index].remove(p);
				size--;
				return temp;
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

		int index = key.hashCode() % capacity;
		for (KVPair p : myHashMap[index]) {
			if (p.getKey().equals(key)) {
				return p.getValue();
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
		MyHashMap myNewHashMap = new MyHashMap(newCapacity);
		for (int i = 0; i < myHashMap.length; i++) {
			for (KVPair p : myHashMap[i]) {
				myNewHashMap.put(p.getKey(), p.getValue());
			}
		}
		myHashMap = myNewHashMap.myHashMap;
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
		int myArrayIndex, myListIndex, counter;

		public HashMapIterator() {
			myArrayIndex = 0;
			myListIndex = 0;
			counter = 0;
		}

		public boolean hasNext() {
			return counter < size;
		}

		public K next() {
			while (myHashMap[myArrayIndex].isEmpty()) {
				myArrayIndex++;
			}
			K temp = myHashMap[myArrayIndex].get(myListIndex).getKey();
			myListIndex++;
			if (myListIndex == myHashMap[myArrayIndex].size()) {
				myListIndex = 0;
				myArrayIndex++;
			}
			counter++;
			return temp;
		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair {
		private K key;
		private V value;

		public KVPair(K theKey, V theValue) {
			key = theKey;
			value = theValue;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V newValue) {
			value = newValue;
		}
	}

}
