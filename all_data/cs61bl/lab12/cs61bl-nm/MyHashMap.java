import java.util.*;

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
	private ArrayList<LinkedList<KVPair>> myMap;
	private double loadFactor;
	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		capacity = DEFAULT_CAPACITY;
		loadFactor = DEFAULT_LOAD_FACTOR;
		myMap = new ArrayList<LinkedList<KVPair>>(capacity);
		for (int i=0; i<capacity; i++) {
			myMap.add(i, new LinkedList<KVPair>());
		}
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		capacity = initialCapacity;
		loadFactor = DEFAULT_LOAD_FACTOR;
		myMap = new ArrayList<LinkedList<KVPair>>(capacity);
		for (int i=0; i<capacity; i++) {
			myMap.add(i, new LinkedList<KVPair>());
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
		this.loadFactor = loadFactor;
		myMap = new ArrayList<LinkedList<KVPair>>(capacity);
		for (int i=0; i<capacity; i++) {
			myMap.add(i, new LinkedList<KVPair>());
		}
	}
	
	public int hashCode(K key){
		return (int) (key.hashCode()) % capacity;
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
		int index = hashCode(key);
		if (myMap.get(index) == null) {
			return false;
		}
		for (KVPair pair : myMap.get(index)) {
			if (pair.key.equals(key)) {
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
		// TODO Complete this!
		for (LinkedList<KVPair> list : myMap) {
			for (KVPair pair : list) {
				if (pair.value.equals(value)) {
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
		// TODO Complete this!
		int index = hashCode(key);
		if (myMap.get(index) == null) {
			myMap.add(index, new LinkedList<KVPair>());
		}
		KVPair pair = new KVPair(key, value);
		myMap.get(index).add(pair);
		size++;
		if ((double) size/capacity > loadFactor) {
			expand(capacity*2);
		}
		return value;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		int index = hashCode(key);
		if (myMap.get(index) == null) {
			return null;
		}
		V rtnValue = get(key);
		LinkedList<KVPair> list = myMap.get(index);
		for (KVPair pair : list) {
			if (pair.key.equals(key)) {
				list.remove(pair);
				size--;
				return rtnValue;
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
		int index = hashCode(key);
		LinkedList<KVPair> list = myMap.get(index);
		for (KVPair pair : list) {
			if (pair.key.equals(key)) {
				return pair.value;
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
		capacity = newCapacity;
		ArrayList<LinkedList<KVPair>> oldMap = myMap;
		myMap = new ArrayList<LinkedList<KVPair>>(capacity);
		size = 0;
		for (int i=0; i<capacity; i++) {
			myMap.add(i, new LinkedList<KVPair>());
		}
		for(LinkedList <KVPair> lst: oldMap){
			for(KVPair p : lst){
				put(p.key, p.value);
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
		private int arrayIndex, LinkedListIndex;
		

		public HashMapIterator() {
			// TODO Complete this!
			arrayIndex = 0;
			LinkedListIndex = 0;
		}

		public boolean hasNext() {
			// TODO Complete this!
			return (arrayIndex < size && LinkedListIndex < myMap.get(arrayIndex).size());
		}

		public K next() {
			// TODO Complete this!
			if (hasNext()) {
				K currentVal = myMap.get(arrayIndex).get(LinkedListIndex).key;
				if(myMap.get(arrayIndex).size() > LinkedListIndex + 1) {
					LinkedListIndex++;
				} else if(size > arrayIndex + 1){
					while (myMap.get(arrayIndex+1) == null) {
						arrayIndex++;
					}
					LinkedListIndex = 0;
				}
				return currentVal;
			} else {
				return null;
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
		public KVPair(K key, V value) {
			this.key = key;
			this.value = value;
		}

	}

}
