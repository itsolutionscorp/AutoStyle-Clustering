import java.util.ArrayList;
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

	// TODO You may declare new instance variables here
	
	private ArrayList[] a; 
	private double loadFactor;

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		a = new ArrayList[DEFAULT_CAPACITY*2];
		for (int i = 0; i < DEFAULT_CAPACITY*2; i++) {
			a[i] = new ArrayList(); 
		}
		capacity = DEFAULT_CAPACITY;
		loadFactor = DEFAULT_LOAD_FACTOR;
		// TODO Complete this!
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		a = new ArrayList[initialCapacity*2];
		for (int i = 0; i < initialCapacity*2; i++) {
			a[i] = new ArrayList(); 
		}
		capacity = initialCapacity;
		loadFactor = DEFAULT_LOAD_FACTOR;
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
		a = new ArrayList[initialCapacity*2];
		for (int i = 0; i < initialCapacity*2; i++) {
			a[i] = new ArrayList(); 
		}
		this.loadFactor = loadFactor;
		capacity = initialCapacity;
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
		int hashCode = key.hashCode() % capacity; 
		return a[hashCode].contains(key); 
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		for (int i = capacity; i < capacity*2; i++) {
			if (a[i].contains(value)) {
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
		int index = key.hashCode() % capacity; 
		V previous; 
		if (a[index].contains(key)) {
			int keyIndex = a[index].indexOf(key); 
			previous = (V) a[index+capacity].get(keyIndex); 
			a[index+capacity].set(keyIndex, value); 
		} else {
			a[index].add(key);
			a[index+capacity].add(value); 
			size++;
			previous = null;
		}
		
		if ( (double) size/ (double) capacity > loadFactor) {
			expand(capacity*2); 
		}
		return previous;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		int hashcode = key.hashCode() % capacity; 
		V value; 
		if (a[hashcode].contains(key)) {
			int keyIndex = a[hashcode].indexOf(key); 
			value = (V) a[hashcode+capacity].get(keyIndex);
			a[hashcode].remove(key);
			a[hashcode+capacity].remove(keyIndex);
			size--; 
		} else {
			value = null;
		}
		return value;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		int hashcode = key.hashCode() % capacity; 
		V value;
		if (a[hashcode].contains(key)) {
			int keyIndex = a[hashcode].indexOf(key); 
			value = (V) a[hashcode+capacity].get(keyIndex);
		} else {
			value = null;
		}
		return value;
	}

	/**
	 * Expands the underlying array to the given capacity.
	 * 
	 * ALSO, rehashes all of the items into this new array. Items will likely
	 * end up in different buckets after the expansion.
	 */
	private void expand(int newCapacity) {
		ArrayList[] newarray = new ArrayList[newCapacity*2];
		for (int i = 0; i < newCapacity*2; i++) {
			newarray[i] = new ArrayList(); 
		}
		Iterator iter = this.iterator();  
		while (iter.hasNext()) {
			K key = (K) iter.next(); 
			int hashCode = key.hashCode() % capacity; 
			int keyIndex = a[hashCode].indexOf(key); 
			V value = (V) a[hashCode+capacity].get(keyIndex);
			int newHashCode = key.hashCode() % newCapacity; 
			newarray[newHashCode].add(key);
			newarray[newHashCode + newCapacity].add(value); 
		}
		a = newarray;
		this.capacity = newCapacity; 
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

		private ArrayList<K> keys; 
		private int index = 0; 
		
		public HashMapIterator() {
			keys = new ArrayList<K>(); 
			for (int i = 0; i < capacity; i++) {
				for (int j = 0; j < a[i].size(); j++) {
					keys.add((K) a[i].get(j)); 
				}
			}
		}

		@Override
		public boolean hasNext() {
			return !(index >= keys.size()); 
		}

		@Override
		public K next() {
			if (hasNext()) {
				index++; 
				return keys.get(index-1); 
			} else {
				throw new IllegalStateException("No more keys to iterate through"); 
			}
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
	}

}
