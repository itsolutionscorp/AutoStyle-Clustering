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

	private int capacity = DEFAULT_CAPACITY; // the number of buckets in the map
	private int size = 0; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	private LinkedList[] myArray;
	private double myLoadFactor;

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		myArray = new LinkedList[DEFAULT_CAPACITY];
		myLoadFactor = DEFAULT_LOAD_FACTOR;
		capacity = DEFAULT_CAPACITY;
		for (int i = 0; i < capacity; i++) {
			myArray[i] = new LinkedList();
		}
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		myArray = new LinkedList[initialCapacity];
		myLoadFactor = DEFAULT_LOAD_FACTOR;
		capacity = initialCapacity;
		for (int i = 0; i < capacity; i++) {
			myArray[i] = new LinkedList();
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
		myArray = new LinkedList[initialCapacity];
		myLoadFactor = loadFactor;
		capacity = initialCapacity;
		for (int i = 0; i < capacity; i++) {
			myArray[i] = new LinkedList();
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
		// TODO Complete this!
		LinkedList<KVPair> current = myArray[key.hashCode() % capacity];
		if (!current.isEmpty()) {
			for (int i = 0; current.get(i) != null; i++) {
				if (current.get(i).key == key) {
					return true;
				}
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
		
		Iterator myIterator = new HashMapIterator();
		while (myIterator.hasNext()) {
			if (value == get((K)(myIterator.next()))){
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
		// TODO Complete this!
		if ((size + 1 / capacity) >= myLoadFactor) {
			expand(capacity * 2);
		}
		LinkedList<KVPair> current = myArray[key.hashCode() % capacity];
		if (!current.isEmpty()) {
			for (int i = 0; current.get(i) != null; i++) {
				if (current.get(i).key == key) {
					V oldVal = current.get(i).value;
					current.get(i).value = value;
					return oldVal;
				}
			}
		}
		current.add(new KVPair(key, value));
		size++;
		
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
		LinkedList<KVPair> current = myArray[key.hashCode() % capacity];
		if (!current.isEmpty()) {
			for (int i = 0; current.get(i) != null; i++) {
				if (current.get(i).key == key) {
					size--;
					return current.remove(i).value;
				}
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
		if (containsKey(key) == true) {
			LinkedList<KVPair> current = myArray[key.hashCode() % capacity];
			for (int i = 0; current.get(i) != null; i++) {
				if (current.get(i).key == key) {
					return current.get(i).value;
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
		// TODO Complete this!
		LinkedList[] newArray = new LinkedList[newCapacity];
		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = new LinkedList();
		}
		
		Iterator myIterator = new HashMapIterator();
		while (myIterator.hasNext()) {
			K oldKey = (K)myIterator.next();
			KVPair oldKV = new KVPair(oldKey, get(oldKey));
			
			newArray[oldKV.key.hashCode() % newArray.length].add(new KVPair(oldKV.key, oldKV.value));
		}
		myArray = newArray;
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
		int arrayCursor;
		int linkedCursor;
		int currentCount;

		public HashMapIterator() {
			arrayCursor = 0;
			linkedCursor = 0;
			currentCount = 0;
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			return currentCount < size;
		}

		@Override
		public K next() {
			// TODO Complete this!

			if (myArray[arrayCursor].equals(new LinkedList())) {
				arrayCursor++;
				linkedCursor = 0;
				return next();
			} else if (myArray[arrayCursor].get(linkedCursor) == myArray[arrayCursor]
					.getLast()) {
				K returningK = ((KVPair) ((myArray[arrayCursor])
						.get(linkedCursor))).key;
				linkedCursor = 0;
				arrayCursor++;
				currentCount++;
				return returningK;
			} else {
				K returningK = ((KVPair) ((myArray[arrayCursor])
						.get(linkedCursor))).key;
				linkedCursor++;
				currentCount++;
				return returningK;
			}

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

		/*
		 * TODO Fill this out if you need it. Maybe not required depending on
		 * your implementation.
		 */
		public KVPair(K thekey, V thevalue) {
			key = thekey;
			value = thevalue;
		}



	}

}
