import java.awt.Point;
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
	Object[] map;
	Object[] keys;
	private double loadFactor;
	

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		map = new Object[DEFAULT_CAPACITY];
		capacity = DEFAULT_CAPACITY;
		loadFactor = DEFAULT_LOAD_FACTOR;
		keys = new Object[DEFAULT_CAPACITY];
	}
	
	public static void main(String[] args) {
		Point p = new Point(3, 4);
		String s = "Hello";
		Integer i = 10;
		ArrayList<Object> obj = new ArrayList<Object>();
		System.out.println(obj.hashCode());
		obj.add(i);
		System.out.println(obj.hashCode());
//		System.out.println(obj.hashCode() - p.hashCode());
		obj.add(s);
		System.out.println(obj.hashCode());
//		System.out.println(obj.hashCode() - p.hashCode() - s.hashCode());
		obj.add(p);
		System.out.println(obj.hashCode());
		System.out.println(p.hashCode());
		System.out.println(s.hashCode());
		System.out.println(i.hashCode());
		
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		map = new Object[initialCapacity];
		capacity = initialCapacity;
		this.loadFactor = DEFAULT_LOAD_FACTOR;
		keys = new Object[initialCapacity];
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
		map = new Object[initialCapacity];
		capacity = initialCapacity;
		this.loadFactor = loadFactor;
		keys = new Object[initialCapacity];
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
		for (Object o: keys) {
			if(o==null)
				continue;
			if (o.equals(key))
				return true;
		}
		return false;
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		for (Object o: map) {
			if (o.equals(value))
				return true;
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
		int index = key.hashCode() % this.capacity;
		this.size++;
		double check = (double) size / (double) capacity;
		if (check > loadFactor) {
			this.expand(capacity*2);
		}
		if (this.containsKey(key)) {
			V prev = this.get(key);
			map[index] = value;
			return prev;
		} else {
			while (map[index] != null) {
				if (index >= capacity) {
					index = -1;
				}
				index++;
			}
			keys[index] = key;
			map[index] = value;
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
		if (! this.containsKey(key))
			return null;
		V result = this.get(key);
		int index = java.util.Arrays.asList(keys).indexOf(key);
		map[index] = null;
		keys[index] = null;
		size--;
		return result;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		if (! this.containsKey(key))
			return null;
		int index = java.util.Arrays.asList(keys).indexOf(key);
		return (V) (map[index]);
		
	}

	/**
	 * Expands the underlying array to the given capacity.
	 * 
	 * ALSO, rehashes all of the items into this new array. Items will likely
	 * end up in different buckets after the expansion.
	 */
	private void expand(int newCapacity) {
		this.capacity = newCapacity;
		Object[] newMap = new Object[newCapacity];
		Object[] newKeys = new Object[newCapacity];
		Object[] tempMap = map;
		Object[] tempKeys = keys;
		this.size = 0;
		this.map = newMap;
		this.keys = newKeys;
		for (int i = 0; i < tempMap.length; i++){
			if (tempKeys[i] != null && tempMap[i] != null)
				this.put((K) tempKeys[i], (V) tempMap[i]);
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

		private int index;
		
		public HashMapIterator() {
			index = 0;
		}

		@Override
		public boolean hasNext() {
			return index < size;
		}

		@Override
		public K next() {
			K result = (K) (keys[index]);
			index++;
			while (keys[index] == null) {
				index++;
			}
			return result;
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
