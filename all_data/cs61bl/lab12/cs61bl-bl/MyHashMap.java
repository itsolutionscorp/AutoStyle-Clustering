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
	private double loadFactor; // the load factor
	private KVPair head; // the first KVPair of the HashMap
	private KVPair curr; // the KVPair whose existance is being checked

	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		head = new KVPair(null, null, null);
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		capacity = initialCapacity;
		head = new KVPair(null, null, null);
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
		head = new KVPair(null, null, null);
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
		KVPair pointer = head;
		while (pointer != null) {
			if (pointer.key == key) {
				curr = pointer;
				return true;
			}
			pointer = pointer.next;
		}
		return false;
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		KVPair pointer = head;
		while (pointer != null) {
			if (pointer.value == value) {
				curr = pointer;
				return true;
			}
			pointer = pointer.next;
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
		V oldValue = null;
		if (containsKey(key)) {
			oldValue = value;
			curr.value = value;
		} else {
			head = new KVPair(key, value, head);
		}
		size += 1;
		return oldValue;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		KVPair pointer = head;
		V removedVal = null;
		while (pointer.next.key != key) {
			removedVal = pointer.next.value;
			pointer.next = pointer.next.next;
			pointer = pointer.next;
		}
		return removedVal;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		if (containsKey(key)) {
			return curr.value;
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
		KVPair iterHead;

		public HashMapIterator() {
			iterHead = head;
		}

		@Override
		public boolean hasNext() {
			return iterHead != null;
		}

		@Override
		public K next() {
			K nextKey = iterHead.key;
			iterHead = iterHead.next;
			return nextKey;
		}

		@Override
		public void remove() {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair {
		private K key;
		private V value;
		private KVPair next;

        public KVPair(K k, V v, KVPair n) {
            key = k;
            value = v;
            next = n;
        }

        public KVPair get(K k) {
            KVPair pointer = this;
            while (pointer != null) {
                if (pointer.key.equals(key)) {
                    return pointer;
                }
                pointer = pointer.next;
            }
            return null;
        }
	}

}
