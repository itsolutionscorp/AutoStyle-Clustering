
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
	private double loadFactor;
	private KVPair[] storage;
	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		capacity = DEFAULT_CAPACITY;
		size = 0;
		loadFactor = DEFAULT_LOAD_FACTOR;
		storage = (KVPair[]) new MyHashMap.KVPair[capacity];
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		capacity = initialCapacity;
		size = 0;
		loadFactor = DEFAULT_LOAD_FACTOR;
		storage = (KVPair[]) new MyHashMap.KVPair[capacity];
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
		storage = (KVPair[]) new MyHashMap.KVPair[capacity];
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
		int index = indexHash(key);
		if (storage[index] == null) {
			return false;
		} else {
			KVPair pair = storage[index];
			for (; pair.myNext != null; pair = pair.myNext) {
				if (pair.myKey == key) {
					return true;
				}
			}
			if (pair.myKey == key) {
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
		for (int i = 0; i < capacity; i++) {
			if (storage[i] == null) {
				continue;
			}
			KVPair pair = storage[i];
			for (; pair.myNext != null; pair = pair.myNext) {
				if (pair.myValue == value) {
					return true;
				}
			}
			if (pair.myValue == value) {
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
		if ((((double) size() + 1) / (double) capacity()) > loadFactor) {
			this.expand(2*capacity());
		}
		int index = indexHash(key);
		if (storage[index] == null) {
			storage[index] = new KVPair(key, value);
			size++;
			return null;
		} else {
			KVPair pair = storage[index];
			for (; pair.myNext != null; pair = pair.myNext) {
				if (pair.myKey == key) {
					V temp = pair.myValue;
					pair.myValue = value;
					return temp;
				}
				pair = pair.myNext;
			}
			if (pair.myKey == key) {
				V temp = pair.myValue;
				pair.myValue = value;
				return temp;
			} else {
				pair.myNext = new KVPair(key, value);
				size++;
			}
		}
		return null;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		int index = indexHash(key);
		if (storage[index] == null) {
			return null;
		}
		KVPair pair = storage[index];
		KVPair previous = null;
		boolean foundKey = false;
		if (pair.myKey == key) {
			foundKey = true;
		} else {
			for (; pair.myNext != null; previous = pair, pair = pair.myNext) {
				if (pair.myKey == key) {
					foundKey = true;
					break;
				}
			}
		}
		if (foundKey) {
			if (previous == null && pair.myNext != null) {
				storage[index] = pair.myNext;
				size--;
			} else if (previous == null) {
				storage[index] = null;
				size--;
			} else {
				V temp = pair.myValue;
				previous.myNext = pair.myNext;
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
		int index = indexHash(key);
		if (storage[index] == null) {
			return null;
		} else {
			KVPair pair = storage[index];
			for (; pair.myNext != null; pair = pair.myNext) {
				if (pair.myKey == key) {
					return pair.myValue;
				}
			}
			if (pair.myKey == key) {
				return pair.myValue;
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
		KVPair[] temp = (KVPair[]) new MyHashMap.KVPair[size()];
		int x = 0;
		for (int i = 0; i < capacity(); i++) {
			if (storage[i] == null) {
				continue;
			}
			KVPair pair = storage[i];
			for (; pair.myNext != null; pair = pair.myNext) {
				temp[x] = pair;
				x++;
			}
			temp[x] = pair;
			x++;
		}
		storage = (KVPair[]) new MyHashMap.KVPair[newCapacity];
		size = 0;
		for (int y = 0; y < temp.length; y++) {
			put(temp[y].myKey, temp[y].myValue);
		}
		capacity = newCapacity;
	}

	/**
	 * Returns an iterator over the keys of this map.
	 */
	
	private int indexHash(Object obj) {
		return obj.hashCode() % capacity;
	}
	
	public Iterator<K> iterator() {
		return new HashMapIterator();
	}

	/**
	 * An iterator for the keys of the enclosing map.
	 */
	private class HashMapIterator implements Iterator<K> {

		int index;
		KVPair[] all;
		
		public HashMapIterator() {
			index = 0;
			all = (KVPair[]) new MyHashMap.KVPair[size()];
			int x = 0;
			for (int i = 0; i < capacity; i++) {
				if (storage[i] == null) {
					continue;
				}
				KVPair pair = storage[i];
				for (; pair.myNext != null; pair = pair.myNext) {
					all[x] = pair;
					x++;
				}
				all[x] = pair;
				x++;
			}
		}

		@Override
		public boolean hasNext() {
			return index < all.length;
		}

		@Override
		public K next() {
			if (hasNext()) {
				index++;
				return all[index-1].myKey;
			}
			return null;
		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair {
		private K myKey;
		private V myValue;
		private KVPair myNext;
		
		public KVPair (K theKey, V theValue) {
			myKey = theKey;
			myValue = theValue;
			myNext = null;
		}
		
	}

}

