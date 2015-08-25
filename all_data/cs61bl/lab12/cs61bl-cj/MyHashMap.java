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
	
	private double LoadFactor;
	
	private KVPair[] array;

	int maxIndex = 0;
	
	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		array = (KVPair[]) new Object[DEFAULT_CAPACITY];
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		array = (KVPair[]) new Object[initialCapacity];
		capacity = initialCapacity;
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
		array = (KVPair[]) new Object[initialCapacity];
		capacity = initialCapacity;
		LoadFactor = loadFactor;
		

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
		int index = key.hashCode() % this.capacity;
		if (array[index] != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		for (int i = 0; i < capacity; i ++) {
			if (array[i].value == value) {
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
		
		if (((1 + this.size())/this.capacity()) > this.LoadFactor) {
			this.expand(2*this.capacity());
		}
		
		int index = key.hashCode();
		if (index > maxIndex) {
			maxIndex = index;
		}
		V preValue;
		if (array[index] != null) {
			preValue = array[index].value;
			return preValue;
		}
		array[index] = new KVPair(key, value);
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
		int index = key.hashCode();
		if (get(key) != null){
			V temp = get(key);
			array[index] = null;
			return temp;
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
		int index = key.hashCode();
		if (array[index] != null){
			return array[index].value;
		}
		else{
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
		int temp = this.capacity();
		KVPair[] newArray = (KVPair[]) new Object[newCapacity];
		KVPair[] oldarray = this.array;
		this.array = newArray;
		for (int i = 0; i < temp; i ++) {
			KVPair pair = oldarray[i];
			this.put(pair.key, pair.value);
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

		int index;
		
		public HashMapIterator() {
			// TODO Complete this!
			index = 0;
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			if (index < maxIndex) {
				return true;
			}
			return false;
		}

		@Override
		public K next() {
			// TODO Complete this!
			if (array[index] == null){
				index += 1;
				this.next();
			}
			else{
				return array[index].key;
			}
		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair {
		private K key;
		private V value;
		
		public KVPair(K yek, V eulav) {
			this.key = yek;
			this.value = eulav;
		}
		/*
		 * TODO Fill this out if you need it. Maybe not required depending on
		 * your implementation.
		 */

	}

}
