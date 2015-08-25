import java.util.Iterator;
import java.util.LinkedList;

/* YOU ARE NOT allowed to use any library function related to hash maps
 if you feel the need you may use the ArrayList or LinkedList class */

public class MyHashMap<K, V> implements Iterable<K> {
	/* Default size of the map if not set in constructor */
	private static final int DEFAULT_CAPACITY = 30;

	/* Default load factor if not set in constructor */
	private static final double DEFAULT_LOAD_FACTOR = 0.7;

	private double load;
	private int capacity; // the number of buckets in the map
	private int size; // the number of items that have been put into the map
	private LinkedList<KVPair>[] map;

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		size = 0;
		capacity = DEFAULT_CAPACITY;
		load = DEFAULT_LOAD_FACTOR;
		map = new LinkedList[capacity];
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		capacity = initialCapacity;
		size = 0;
		load = DEFAULT_LOAD_FACTOR;
		map = new LinkedList[capacity];
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
		load = loadFactor;
		size = 0;
		map = new LinkedList[capacity];
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
	
	public double load() {
		return load;
	}

	/**
	 * Returns whether the map contains the given key. Runs in O(1) time on
	 * average, relative to the size of the map.
	 */
	public boolean containsKey(K key) {
		int index = key.hashCode() % capacity;
		if (index < 0) {
			index = index * -1;
		}
		LinkedList<KVPair> temp = map[index];
		if (temp == null) {
			return false;
		} else {
			for (KVPair i : temp) {
				if (i.key().equals(key)) {
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

		for (LinkedList<KVPair> list : map) {
			if (list != null) {
				for (KVPair kv : list) {
					if (kv.value().equals(value)) {
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
		if (((double) (size + 1) / (double) capacity) > load) {
			expand(2*capacity);
		}
		int index = key.hashCode() % capacity;
		if (index < 0) {
			index = index * -1;
		}
		V prevVal = null;
		boolean replaced = false;
		if (map[index] == null) {
			map[index] = new LinkedList<KVPair>();
		}
		LinkedList<KVPair> temp = map[index];
		for (KVPair i : temp) {
			if (i.key().equals(key)) {
				replaced = true;
				prevVal = i.value();
				i.setValue(value);
			}
		}
		if (!replaced) {
			temp.add(new KVPair(key, value));
			size++;
		}
		return prevVal;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		int index = key.hashCode() % capacity;
		if (index < 0) {
			index = index * -1;
		}
		LinkedList<KVPair> temp = map[index];
		if (temp == null) {
			return null;
		}
		for (KVPair i : temp) {
			if (i.key().equals(key)) {
				V tempVal = i.value();
				temp.remove(i);
				size--;
				return tempVal;
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
		if (index < 0) {
			index = index * -1;
		}
		LinkedList<KVPair> temp = map[index];
		if (temp == null) {
			return null;
		}
		for (KVPair i : temp) {
			if (i.key().equals(key)) {
				return i.value();
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
		LinkedList<KVPair>[] newArray = new LinkedList[newCapacity];
		for (LinkedList<KVPair> list : map) {
			if (list != null) {
				for (KVPair i : list) {
					int index = i.key().hashCode() % newCapacity;
					if (index < 0) {
						index = index * -1;
					}
					if (newArray[index] == null) {
						newArray[index] = new LinkedList<KVPair>();
					}
					LinkedList<KVPair> temp = newArray[index];
					temp.add(i);
				}
			}
		}
		capacity = newCapacity;
		map = newArray;
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
		private int count;
		private int currArrayIndex;
		private int currListIndex;
		
		public HashMapIterator() {
			count = 0;
			currArrayIndex = 0;
			currListIndex = 0;
		}

		@Override
		public boolean hasNext() {
			return count < size;
		}

		@Override
		public K next() {
			int i = currArrayIndex;
			while(i < capacity) {
				LinkedList<KVPair> list = map[i];
				if (list != null) {
					K nextKey = list.get(currListIndex).key();
					currListIndex += 1;
					if (currListIndex == list.size()) {
						currArrayIndex += 1;
						currListIndex = 0;
					}
					count++;
					return nextKey;
				}
				i++;
				currArrayIndex++;
			}
			return null;
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
		public KVPair(K k, V v) {
			key = k;
			value = v;
		}
		
		public K key() {
			return key;
		}
		
		public V value() {
			return value;
		}
		
		public void setValue(V newValue) {
			value = newValue; 
		}
	}

}
