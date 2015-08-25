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

	// TODO You may declare new instance variables here
	
	private double loadFactor;
	private LinkedList<KVPair>[] arr;
	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		this(initialCapacity, DEFAULT_LOAD_FACTOR);
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
		capacity = initialCapacity;
		size = 0;
		this.loadFactor = loadFactor;
		arr = new LinkedList[capacity];
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
		int hashedKey = Math.abs(key.hashCode() % capacity);
		if (arr[hashedKey] != null) {
			for (KVPair k: arr[hashedKey]) {
				if (k.key.equals(key)) {
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
		for (int i = 0; i < capacity; i++) {
			if (arr[i] != null) {
				for (KVPair p: arr[i]) {
					if (p.value.equals(value)) {
					return true;
					}
				}
			}
		} return false;
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
		if (key == null) {
			return null;
		}
		if (size() + 1 >= (loadFactor * capacity())) {
			expand(capacity * 2);
		}
		KVPair temp = new KVPair(key, value);
		int hashedKey = Math.abs(key.hashCode() % capacity);
		if (arr[hashedKey] == null) {
			arr[hashedKey] = new LinkedList();
			arr[hashedKey].add(temp);
			size++;
			return null;
		} else {
			V val = get(key);
			remove(key);
			arr[hashedKey].add(temp);
			size++;
			return val;			
		} 
		
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		if (key == null) {
			return null;
		}
		
		if (containsKey(key)) {
			int hashedKey = Math.abs(key.hashCode() % capacity);
			V val = get(key);
			for (KVPair i : arr[hashedKey]) {
				if (i.key.equals(key)) {
					arr[hashedKey].remove(i);
					size--;
					if (arr[hashedKey].size() == 0) {
						arr[hashedKey] = null;
					}
				}
			} 
			return val;
		} return null; 
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		// TODO Complete this!
		if (key == null) {
			return null;
		}
		if (containsKey(key)) {
			int hashedKey = Math.abs(key.hashCode() % capacity);
			for (KVPair i : arr[hashedKey]) {
				if (key.equals(i.key)) {
					return i.value;
				}
			}	
		} return null;
	}

	/**
	 * Expands the underlying array to the given capacity.
	 * 
	 * ALSO, rehashes all of the items into this new array. Items will likely
	 * end up in different buckets after the expansion.
	 */
	private void expand(int newCapacity) {
		// TODO Complete this!
		LinkedList<KVPair>[] temp = new LinkedList[newCapacity];
		for (int i = 0; i < capacity; i++) {
			if (arr[i] != null) { 
				int hashedKey = Math.abs(arr[i].getFirst().key.hashCode() % newCapacity);
				temp[hashedKey] = arr[i];
			}
		}
		arr = temp;
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
		
		private int arrayIndex;
		private int listIndex;
		
		public HashMapIterator() {
			// TODO Complete this!
			arrayIndex = 0;
			listIndex = 0;
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			int i = arrayIndex;
			while (i < capacity) {
				if (arr[i] != null) {
					return true;
				} else {
					i++;
				}
			} return false;
		}

		@Override
		public K next() {
			// TODO Complete this!
			while (arr[arrayIndex] == null) {
				arrayIndex++;
			}
			K ans = arr[arrayIndex].get(listIndex).key;
			listIndex++;
			if (arr[arrayIndex].size() == listIndex) {
				arrayIndex++;
				listIndex = 0;
			}
			return ans;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
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
