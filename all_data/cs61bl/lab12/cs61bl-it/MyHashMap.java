import java.security.KeyPair;
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
	private double myloadFactor;
	// TODO You may declare new instance variables here

	private LinkedList[] myMap;

	/**
	 * Constructs an empty map.
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MyHashMap() {
		capacity = DEFAULT_CAPACITY;
		myloadFactor = DEFAULT_LOAD_FACTOR;
		myMap = new LinkedList[capacity];
		for (int i = 0; i < capacity; i++) {
			myMap[i] = new LinkedList<KVPair>();
		}
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	@SuppressWarnings("unchecked")
	public MyHashMap(int initialCapacity) {
		capacity = initialCapacity;
		myloadFactor = DEFAULT_LOAD_FACTOR;
		myMap = new LinkedList[capacity];
		for (int i = 0; i < capacity; i++) {
			myMap[i] = new LinkedList<KVPair>();
		}
		// myMap= (KVPair[]) new MyHashMap.KVPair[capacity];
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
		myloadFactor = loadFactor;
		myMap = new LinkedList[capacity];
		for (int i = 0; i < capacity; i++) {
			myMap[i] = new LinkedList<KVPair>();
		}
		// myMap= (KVPair[]) new MyHashMap.KVPair[capacity];
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
		boolean ret= false;
		Iterator<K> listIterator = iterator();
		while (listIterator.hasNext()) {
			if (listIterator.next() == key) {
				return true;
			}
		}
		return ret;
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		Iterator<K> listIterator = iterator();
		while (listIterator.hasNext()) {
			if (get(listIterator.next()) == value) {
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
	@SuppressWarnings("unchecked")
	public V put(K key, V value) {
		int c = Math.abs(key.hashCode()) % capacity;
		V ret = null;
		KVPair input = new KVPair(key, value);
		if (containsKey(key)) {
			for (int i = 0; 1 < myMap[c].size(); i++) {
				if (((MyHashMap<K, V>.KVPair) myMap[c].get(i)).getKey() == key) {
					ret = ((MyHashMap<K, V>.KVPair) myMap[c].get(i)).getValue(); // get
																					// old
																					// value
					myMap[c].set(i, input); // replace with new
					return ret;
				}
			}
		} else {
			myMap[c].add(input);
			size++;
			if (checkexpand()) {
				int newcapacity = (int) (1.5 * capacity);
				expand(newcapacity);
				capacity = newcapacity;
			}
		}
		return ret;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	@SuppressWarnings("unchecked")
	public V remove(K key) {
		V ret = null;
		int c = Math.abs(key.hashCode()) % capacity;

		if (containsKey(key)) {
			if (myMap[c].size() == 1) {
				size--;
			}
			ret = get(key); // get old value
			for (int i = 0; 1 < myMap[c].size(); i++) {
				if (((MyHashMap<K, V>.KVPair) myMap[c].get(i)).getKey() == key) {
					myMap[c].remove(i); // remove old value's node
					return ret;
				}
			}
		}

		return ret;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	@SuppressWarnings("unchecked")
	public V get(K key) {
		V ret = null;
		if (containsKey(key)) {
			
			int c = Math.abs(key.hashCode()) % capacity;
			for (int i = 0; 1 < myMap[c].size(); i++) {
				if (((MyHashMap<K, V>.KVPair) myMap[c].get(i)).getKey() == key) {
					return ((MyHashMap<K, V>.KVPair) myMap[c].get(i))
							.getValue();
				}
			}

		}
		return ret;
	}

	/**
	 * Expands the underlying array to the given capacity.
	 * 
	 * ALSO, rehashes all of the items into this new array. Items will likely
	 * end up in different buckets after the expansion.
	 */
	public boolean checkexpand() {
		Double a = (double) (size / capacity);
		if (a > myloadFactor) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private void expand(int newCapacity) {
		@SuppressWarnings({ "rawtypes" })
		LinkedList[] NewmyMap = new LinkedList[newCapacity];
		Iterator<K> listIterator = iterator();
		for (int i = 0; i < newCapacity; i++) {
			NewmyMap[i] = new LinkedList<KVPair>();
		}
		while (listIterator.hasNext()) {
			K key = listIterator.next();
			V value = get(key);
			int newC = Math.abs(key.hashCode()) % newCapacity;
			KVPair newInput= new KVPair(key,value);
   			NewmyMap[newC].add(newInput);
		}
		
		myMap = NewmyMap; // point to the same location
		
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
		private int pos;
		private int amt;
		private int totKeys;
		private int currKeys;
		private int[] amount;

		public HashMapIterator() {
			pos = 0;
			amt = 0;
			currKeys = 0;
			amount = new int[capacity];
			totKeys = 0;
			for (int i = 0; i < capacity; i++) {
				if (!myMap[i].isEmpty()) {
					amount[i] = myMap[i].size();
					totKeys += amount[i];
				}
			}
		}

		@Override
		public boolean hasNext() {
			if (pos < capacity) {
				if (currKeys < totKeys) {
					return true;
				}
			}
			return false;
		}

		@SuppressWarnings("unchecked")
		@Override
		public K next() {
			if (hasNext()) {
				K ret = null;
				if (amount[pos] > amt) {
					ret = ((MyHashMap<K, V>.KVPair) myMap[pos].get(amt)).getKey();
					currKeys++;
					amt++;
				}
				if (amt == amount[pos]) {
					while (0 == amount[pos]) {
						pos++;
						if (pos >= capacity) {
							break;
						}
					}
					pos++;
					amt = 0;

				}
				return ret;
			} else {
				throw new IllegalArgumentException("no more next()... stop it!");
			}
		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair {
		private K key;
		private V value;

		public KVPair(K k, V v) {
			key = k;
			value = v;
		}

		public K getKey() {
			return key;
		}

		@SuppressWarnings("unused")
		public void setKey(K key) {
			this.key = key;
		}

		public V getValue() {
			return value;
		}

		@SuppressWarnings("unused")
		public void setValue(V value) {
			this.value = value;
		}

	}

}
