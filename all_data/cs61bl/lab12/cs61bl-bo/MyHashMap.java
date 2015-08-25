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

	ArrayList<LinkedList<KVPair>> values;
	double loadFactor;
	
	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		values = new ArrayList<LinkedList <KVPair>>();
		for (int i = 0; i < DEFAULT_CAPACITY; i++) {
			values.add(new LinkedList<KVPair>());
		}
		size = 0;
		capacity = DEFAULT_CAPACITY;
		loadFactor = DEFAULT_LOAD_FACTOR;
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		values = new ArrayList<LinkedList <KVPair>>();
		for (int i = 0; i < initialCapacity; i++) {
			values.add(new LinkedList<KVPair>());
		}
		size = 0;
		capacity = initialCapacity;
		this.loadFactor = DEFAULT_LOAD_FACTOR;
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
		values = new ArrayList<LinkedList <KVPair>>();
		for (int i = 0; i < initialCapacity; i++) {
			values.add(new LinkedList<KVPair>());
		}
		size = 0;
		capacity = initialCapacity;
		this.loadFactor = loadFactor;
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
		return values.get(key.hashCode()).contains(key);
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		for(LinkedList<KVPair> ll:values) {
			for(KVPair p: ll) {
				if(p.value.equals(value)) {
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
		int hash = key.hashCode()%capacity;
		LinkedList<KVPair> ll = values.get(hash);
		for(KVPair p: ll) {
			if (p.key.equals(key)) {
				V temp = p.value;
				p.value= value;
				return temp;
			}
		}
		ll.add(new KVPair(key,value));
		size++;
		return null;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		int hash = key.hashCode()%capacity;
		LinkedList<KVPair> ll = values.get(hash);
		for(KVPair p: ll) {
			if (p.key.equals(key)) {
				V temp = p.value;
				ll.remove(p);
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
		int hash = key.hashCode()%capacity;
		LinkedList<KVPair> ll = values.get(hash);
		for(KVPair p: ll) {
			if (p.key.equals(key)) {
				return p.value;
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
		ArrayList<LinkedList<KVPair>> hash2 = new ArrayList<LinkedList<KVPair>>(newCapacity);
		for(LinkedList<KVPair> ll:values) {
			for(KVPair p: ll) {
				int hash = p.key.hashCode()%newCapacity;
				hash2.get(hash).add(p);
			}
		}
		values = hash2;
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
		int arrayIndex;
		int llIndex;
		int count = 0;
		public HashMapIterator() {
			arrayIndex = 0;
			llIndex = 0;
			count = 0;
			findNext();
		}

		public boolean hasNext() {
			return count<size;
		}

		public K next() {
			K key = values.get(arrayIndex).get(llIndex).key;
			findNext();
			return key;
		}
		//precondition: hasNext is true.
		private void findNext() {
			if(llIndex < values.get(arrayIndex).size()) {
				llIndex++;
			}
			else {
				arrayIndex++;
				llIndex = 0;
			}
			while(arrayIndex < values.size() && values.get(arrayIndex).isEmpty()) {
				arrayIndex++;
			}
		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair {
		private K key;
		private V value;
		public KVPair(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		public boolean equals(KVPair kvpair) {
			return this.key.equals(kvpair.key) && this.value.equals(kvpair.value);
		}
	}

}
