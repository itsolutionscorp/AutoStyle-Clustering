import java.util.Iterator;
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

	private LinkedList<KVPair>[] HashMapper;
	private double factor = 0;

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		HashMapper = new LinkedList[DEFAULT_CAPACITY];
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		HashMapper = new LinkedList[initialCapacity];
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
		HashMapper = new LinkedList[initialCapacity];
		factor = loadFactor;

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
		if (HashMapper[key.hashCode() % size] == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		boolean contains = false;
		for (int i = 0; i < HashMapper.length; i++) {
			if (HashMapper[i] != null) {
				contains = HashMapper[i].element().value == value;
			}
		}
		return contains;
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
		if (HashMapper[key.hashCode() % size] == null) {
			HashMapper[key.hashCode() % size].add(new KVPair(key, value));
			size++;
			if (factor != 0) {
				if (size / HashMapper.length > factor) {
					expand(2 * HashMapper.length);
				}
			} else if (factor == 0) {
				if (size / HashMapper.length > DEFAULT_LOAD_FACTOR) {
					expand(2 * HashMapper.length);
				}
			}
			return null;
		} else {
			MyHashMap<K, V>.KVPair i = HashMapper[key.hashCode() % size]
					.remove();
			HashMapper[key.hashCode() % size] = new LinkedList<KVPair>();
			HashMapper[key.hashCode() % size].add(new KVPair(key, value));
			return i.value;

		}
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		if (HashMapper[key.hashCode() % size] == null) {
			return null;
		} else {
			MyHashMap<K, V>.KVPair i = HashMapper[key.hashCode() % size]
					.remove();
			HashMapper[key.hashCode() % size] = null;
			size--;
			return i.value;
		}
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		if (HashMapper[key.hashCode() % size] == null) {
			return null;
		} else {
			return HashMapper[key.hashCode() % size].element().value;
		}
	}

	/**
	 * Expands the underlying array to the given capacity.
	 * 
	 * ALSO, rehashes all of the items into this new array. Items will likely
	 * end up in different buckets after the expansion.
	 */
	private void expand(int newCapacity) {
		LinkedList<KVPair>[] temp = new LinkedList[newCapacity];
		for (int i = 0; i < size; i++) {
			temp[i].add(HashMapper[i].element());
		}
		HashMapper = temp;
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
		private int current;
		private K toRtn;

		public HashMapIterator() {
			current = 0;
			K toRtn = null;
		}

		@Override
		public boolean hasNext() {
			for (int i = current; i < size; i++) {
				if (HashMapper[i] != null) {
					return true;
				}
			}
			return false;
		}

		@Override
		public K next() {
			for (int i = current; i < size; i++) {
				if (HashMapper[i] != null) {
					current = i + 1;
					 toRtn = HashMapper[i].element().key;
				}
			}
			return toRtn;
		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair {
		private K key;
		private V value;

		public KVPair(K setKey, V setValue) {
			key = setKey;
			value = setValue;
		}
	}

}
