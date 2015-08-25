import java.util.Iterator;
import java.util.LinkedList;
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

	private double loadFactor;
	// private ArrayList<LinkedList> myMap = new ArrayList<LinkedList>();
	private LinkedList<KVPair>[] myMap;

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		capacity = DEFAULT_CAPACITY;
		myMap = new LinkedList[capacity];
		loadFactor = DEFAULT_LOAD_FACTOR;
		size = 0;
		for (int c = 0; c < myMap.length; c++) {
			myMap[c] = new LinkedList();
		}

	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		capacity = initialCapacity;
		myMap = new LinkedList[capacity];
		loadFactor = DEFAULT_LOAD_FACTOR;
		for (int c = 0; c < myMap.length; c++) {
			myMap[c] = new LinkedList();
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
	public MyHashMap(int initialCapacity, double loadfactor) {
		capacity = initialCapacity;
		myMap = new LinkedList[capacity];
		loadFactor = loadfactor;
		for (int c = 0; c < myMap.length; c++) {
			myMap[c] = new LinkedList();
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
		
		int find = key.hashCode() % capacity;
		for (KVPair p : myMap[find]) {
			if (p.getK().equals(key)) {
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
		
		for (int c = 0; c < capacity; c++) {
			if (myMap[c] != null) {
				for (KVPair p : myMap[c]) {
					if ((p.getV().equals(value))) {
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

		if ((double) size / capacity >= (double) loadFactor) {
			capacity = capacity * 2;
			this.expand(capacity);
		}

		int find = key.hashCode() % capacity;
		V temp;
		for (KVPair p : myMap[find]) {
			if (p.getK().equals(key)) {
				temp = p.getV();
				p.setV(value);
//				System.out.println(p.getV());
				return temp;
			}

		}
		size++;
		myMap[find].add(new KVPair(key, value));
		return null;

	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {

		int find = key.hashCode() % capacity;
		for (KVPair p : myMap[find]) {
			if (p.getK().equals(key)) {
				V temp = p.getV();
				myMap[find].remove(p);
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

		int find = key.hashCode() % capacity;
		for (KVPair p : myMap[find]) {
			if (p.getK().equals(key)) {
				return p.getV();
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

		capacity = newCapacity;
		MyHashMap myNewMap = new MyHashMap(capacity);
		for (int c = 0; c < myMap.length; c++) {
			for (KVPair p : myMap[c]) {
				myNewMap.put(p.getK(), p.getV());
			}
		}
		myMap = myNewMap.myMap;
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

		int index1;
		int index2;
		int c;

		public HashMapIterator() {
			index1 = 0;
			index2 = 0;
			c = 0;

		}

		@Override
		public boolean hasNext() {
			if (c < size) {
				return true;
			}
			return false;
		}

		@Override
		public K next() {
			while (myMap[index1].isEmpty()) {
				index1++;
			}
			K temp = myMap[index1].get(index2).getK();
			index2++;
			if (index2 == myMap[index1].size()) {
				index2 = 0;
				index1++;
			}
			c++;
			return temp;
		}

	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair {
		private K key;
		private V value;

		public KVPair(K k, V v) {
			key = k;
			value = v;
		}

		public K getK() {
			return key;
		}

		public V getV() {
			return value;
		}

		public void setV(V v) {
			value = v;
		}

	}

}
