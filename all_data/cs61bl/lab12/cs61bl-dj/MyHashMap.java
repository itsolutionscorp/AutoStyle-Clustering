import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import com.sun.javafx.css.StyleCacheEntry.Key;

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
	private LinkedList<KVPair> [] myPairs;

	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		capacity = DEFAULT_CAPACITY;
		loadFactor = DEFAULT_LOAD_FACTOR;
		myPairs = new LinkedList[capacity];
		for (int i = 0; i <capacity; i ++) {
			myPairs[i] = new LinkedList<KVPair>();
		}
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		capacity = initialCapacity;
		loadFactor = DEFAULT_LOAD_FACTOR;
		myPairs = new LinkedList[capacity];
		for (int i = 0; i <capacity; i ++) {
			myPairs[i] = new LinkedList<KVPair>();
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
		this.loadFactor = loadFactor;
		myPairs = new LinkedList[capacity];
		for (int i = 0; i <capacity; i ++) {
			myPairs[i] = new LinkedList<KVPair>();
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
		int bucket = Math.abs(key.hashCode() % capacity);
		if (myPairs[bucket] == null) {
			return false;
		}else {
		for (int j = 0; j < myPairs[bucket].size(); j++) {
			if (myPairs[bucket].get(j).getKey() == key) {
				return true;
			}
		}
		return false;
		}
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		if (myPairs == null) {
			return false;
		}
		for (int i = 0; i < capacity; i++) {
			for (int j = 0; j < myPairs[i].size(); j++) {
				if ((myPairs[i].get(j).getValue()) == value) {
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
		int bucket = Math.abs(key.hashCode() % capacity);
		V stored = null;
		if (size >= loadFactor*capacity) {
			expand(capacity*2);
		}
		
		if (containsKey(key)) {
			for (int j = 0; j < size(); j++) {
				for (int i = 0; i < myPairs[j].size(); i++) {
					if (myPairs[j].get(i).getKey() == key) {
						stored = myPairs[j].get(i).getValue();
						myPairs[j].get(i).setValue(value);
					}
				}
			}
		} else {
			
			myPairs[bucket].add(new KVPair(key, value));
			size ++;
		}
		return stored;

	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		int bucket = Math.abs(key.hashCode() % capacity);
		V storedVal = null;
		if (containsKey(key)) {
			for (int j = 0; j < size(); j++) {
				for (int i = 0; i < myPairs[j].size(); i++) {
					if (myPairs[j].get(i).getKey() == key) {
						storedVal = myPairs[j].get(i).getValue();
						myPairs[j].remove(key);
						size --;
						return storedVal;
					}
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
		V storedVal = null;
		if (containsKey(key)) {
			for (int j = 0; j < size(); j++) {
				for (int i = 0; i < myPairs[j].size(); i++) {
					if (myPairs[j].get(i).getKey() == key) {
						storedVal = myPairs[j].get(i).getValue();
						return storedVal;
					}
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
	public void expand(int newCapacity) {
		// TODO Complete this!
		MyHashMap<K,V> copy = new MyHashMap(newCapacity);
		for (int i = 0; i< myPairs.length; i ++) {
			for (int j=0; j< myPairs[i].size(); j++) {
				copy.put(myPairs[i].get(j).getKey(), myPairs[i].get(j).getValue());
			}
		}
		this.myPairs = copy.myPairs;
		this.capacity = copy.capacity;
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
		int index;
		int counter;
		LinkedList<KVPair> myNode;
		public HashMapIterator() {
			myNode = myPairs[index];
		}

		@Override
		public boolean hasNext() {
			if (index < capacity) {
				while (myPairs[index] == null) {
					index++;
					counter = 0;
				}
				return true;
			} else{
				return false;
			}
		}

		@Override
		public K next() {
			K stored = myPairs[index].get(counter).getKey();
			counter++;
			return stored;
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

		public KVPair() {
			key = null;
			value = null;
		}

		public KVPair(K myKey, V myValue) {
			key = myKey;
			value = myValue;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}


		public void setValue(V set) {
			value = set;
		}
		
		}
}
