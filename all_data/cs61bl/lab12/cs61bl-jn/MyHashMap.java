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
	private double loadFactor;
	private LinkedList[] buckets;
	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		capacity = DEFAULT_CAPACITY;
		size = 0;
		loadFactor=DEFAULT_LOAD_FACTOR;
		buckets = new LinkedList[capacity];
		for(int i = 0; i < capacity; i++){
			buckets[i] = new LinkedList();
		}
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		capacity = initialCapacity;
		size=0;
		loadFactor=DEFAULT_LOAD_FACTOR;
		buckets = new LinkedList[capacity];
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
		capacity=initialCapacity;
		size=0;
		this.loadFactor=loadFactor;
		buckets = new LinkedList[capacity];
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
		int hash = key.hashCode();
		int index = hash % capacity;
		LinkedList<KVPair> l = buckets[index];
		for (KVPair item: l) {
			if (item.getK().equals(key)) {
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
		for (LinkedList<KVPair> l: buckets) {
			for (KVPair item: l) {
				if (item.getV().equals(value)) {
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
		
		if (size > capacity * loadFactor) {
			expand(capacity * 2);
		}
		
		V oldV = null;
		boolean tap = false;
		int hash = key.hashCode();
		int index = hash % capacity;
		LinkedList<KVPair> l = buckets[index];
		for (KVPair item: l) {
			if (item.getK().equals(key)) {
				// override //
				oldV = item.setV(value);
				tap = true;
				break;
			}
		}
		
		if (!tap) {
			buckets[index].add(new KVPair(key, value));
			size ++;
		}
		
		return oldV;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		V oldV = null;
		int count = 0;
		int hash = key.hashCode();
		int index = hash % capacity;
		LinkedList<KVPair> l = buckets[index];
		for (KVPair item: l) {
			// check for key //
			if (item.getK().equals(key)) {
				oldV = item.getV();
				l.remove(count);
				size--;
				break;
			}
			count ++;
		}	
		return oldV;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		V val = null;
		int hash = key.hashCode();
		int index = hash % capacity;
		LinkedList<KVPair> l = buckets[index];
		for (KVPair item: l) {
			if (item.getK().equals(key)) {
				val = item.getV();
				break;
			}
		}
		return val;
	}

	/**
	 * Expands the underlying array to the given capacity.
	 * 
	 * ALSO, rehashes all of the items into this new array. Items will likely
	 * end up in different buckets after the expansion.
	 */
	private void expand(int newCapacity) {
		capacity = newCapacity;
		LinkedList[] replacement = new LinkedList[newCapacity];
		for(int i = 0; i < capacity; i++){
			replacement[i] = new LinkedList();
		}
		for (LinkedList<KVPair> l: buckets) {
			for (KVPair i: l) {
				int hash = i.hashCode();
				int newIndex = hash % capacity;
				replacement[newIndex].add(i);			
			}
		}
		buckets = replacement;
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
		private int inner;
		private int elems;

		public HashMapIterator() {
			index = 0;
			inner = 0;
			elems = 0;
		}

		@Override
		public boolean hasNext() {
			return elems < size;
		}

		@Override
		public K next() {
			KVPair temp;
			while (buckets[index].size() == 0) {
				index ++;
			}
			if (inner < buckets[index].size()) {
				temp = (KVPair) buckets[index].get(inner);
				inner ++;
			} else {
				index++;
				inner = 0;
				while (buckets[index].size() == 0) {
					index ++;
				}
				temp = (KVPair) buckets[index].get(inner);
				inner ++;
			}
			elems ++;
			return temp.getK();
		}
		
		@Override
		public void remove() {
			// nothings
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
		public KVPair(K key, V val) {
			this.key = key;
			value = val;
		}
		
		public K getK() {
			return key;
		}
		
		public V setV(V obj) {
			V temp = value;
			value = obj;
			return temp;
		}
		
		public V getV() {
			return value;
		}
		
		public int hashCode() {
			return key.hashCode();
		}
	}

}
