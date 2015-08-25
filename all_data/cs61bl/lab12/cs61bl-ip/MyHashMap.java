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
	private double loadFactor;
	private ArrayList<KVPair>[] bucket;
	
	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		this.capacity = DEFAULT_CAPACITY;
		this.size = 0;
		this.loadFactor = DEFAULT_LOAD_FACTOR;
		this.bucket = new ArrayList[DEFAULT_CAPACITY];
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		this.capacity = initialCapacity;
		this.size = 0;
		this.loadFactor = DEFAULT_LOAD_FACTOR;
		this.bucket = new ArrayList[initialCapacity];
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
		this.capacity = initialCapacity;
		this.size = 0;
		this.loadFactor = loadFactor;
		this.bucket = new ArrayList[initialCapacity];
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
		return (getKV(key) != null);
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		for (int i = 0; i < bucket.length; i++) {
			if (bucket[i] != null) {
				Iterator iter = bucket[i].iterator();
				while (iter.hasNext()) {
					KVPair insert = (MyHashMap<K, V>.KVPair) iter.next();
					if (insert.getVal().equals(value))
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
		int hash = Math.abs(key.hashCode() % bucket.length);
		KVPair insert = new KVPair(key, value);
		V rtn = null;
		
		if (bucket[hash] == null)
			bucket[hash] = new ArrayList<KVPair>();

		ArrayList<KVPair> list = bucket[hash];
		if (containsKey(key)) {
			rtn = get(key);
			KVPair replace = getKV(key);
			replace.replaceVal(value);
		} else {
			list.add(insert);
			size++;
			if (size() >= capacity*loadFactor)
				expand(capacity*2);
		}
		return rtn;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		V rtn = null;
		if (containsKey(key)) {
			int hash = Math.abs(key.hashCode() % bucket.length);
			KVPair rmv = getKV(key);
			rtn = rmv.getVal();
			bucket[hash].remove(rmv);
		}
		return rtn;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		KVPair pair = getKV(key);
		if (pair == null)
			return null;
		return pair.getVal();
	}
	
	/**
	 * Returns the KVPair associated with the key in the map, or null if there is
	 * no such value.
	 * @param key
	 * @return
	 */
	private KVPair getKV(K key) {
		int hash = Math.abs(key.hashCode() % bucket.length);
		ArrayList<KVPair> list = bucket[hash];
		if (list == null)
			return null;
		
		Iterator iter = list.iterator();
		while(iter.hasNext()) {
			KVPair next = (MyHashMap<K, V>.KVPair) iter.next();
			if (next.getKey().equals(key))
				return next;
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
		this.capacity = newCapacity;
		ArrayList<KVPair>[] newBucket = new ArrayList[newCapacity];
		
		for (int i = 0; i < bucket.length; i++) {
			if (bucket[i] != null) {
				Iterator iter = bucket[i].iterator();
				while (iter.hasNext()) {
					KVPair insert = (MyHashMap<K, V>.KVPair) iter.next();
					int hash = Math.abs(insert.getKey().hashCode() % newBucket.length);
					if (newBucket[hash] == null)
						newBucket[hash] = new ArrayList<KVPair>();
					ArrayList<KVPair> list = newBucket[hash];
					list.add(insert);
				}
			}
		}
		this.bucket = newBucket;
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
		private Iterator list;
		
		public HashMapIterator() {
			this.index = 0;
			
			while (bucket[index] == null)
				index++;
			
			this.list = bucket[index].iterator();
		}

		@Override
		public boolean hasNext() {
			return false;
		}

		@Override
		public K next() {
			if (!list.hasNext()) {		// reset to next index
				index++;
				while (bucket[index] == null)
					index++;
				list = bucket[index].iterator();
			}
			KVPair next = (MyHashMap<K, V>.KVPair) list.next();
			
			return next.getKey();
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
		
		public KVPair(K k, V v) {
			this.key = k;
			this.value = v;
		}
		
		public K getKey() {
			return key;
		}
		
		public V getVal() {
			return value;
		}
		
		public void replaceVal(V newVal) {
			this.value = newVal;
		}
	}

}
