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
	private double loadFactor;
	private LinkedList<KVPair>[] table;
	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		loadFactor = DEFAULT_LOAD_FACTOR;
		capacity = DEFAULT_CAPACITY;
		table = (LinkedList<KVPair>[]) new LinkedList[capacity];
		for ( int i = 0; i < capacity;i++) {
			table[i] = new LinkedList<KVPair>();
		}
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		loadFactor = DEFAULT_LOAD_FACTOR;
		capacity = initialCapacity;
		table = (LinkedList<KVPair>[]) new LinkedList[capacity];
		for ( int i = 0; i < capacity;i++) {
			table[i] = new LinkedList<KVPair>();
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
		this.loadFactor = loadFactor;
		capacity = initialCapacity;
		table = (LinkedList<KVPair>[]) new LinkedList[capacity];
		for ( int i = 0; i < capacity;i++) {
			table[i] = new LinkedList<KVPair>();
		}
		
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
		int bucketNumber = Math.abs(key.hashCode()) % capacity;
		return getBucketIndex(bucketNumber, key) != -1;
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		for (LinkedList<KVPair> i: table) {
			if (size() == 0) {
				continue;
			}
			for (int index = 0; index < i.size(); index++) {
				if (value.equals(i.get(index).getValue())) {
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
		int bucketNumber = Math.abs(key.hashCode()) % capacity;
		int index = getBucketIndex(bucketNumber, key);
		if (index == -1) {
			table[bucketNumber].add(new KVPair(key, value));
			size++;
			if (size >= capacity*loadFactor) {
				expand(capacity*2);
			}
			return null;
		} else {
			V toReturn = table[bucketNumber].remove(index).getValue();
			table[bucketNumber].add(new KVPair(key, value));
			return toReturn;
		}
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		int bucketNumber = Math.abs(key.hashCode()) % capacity;
		int index = getBucketIndex(bucketNumber, key);
		if (index == -1) {
			return null;
		} else {
			size--;
			return table[bucketNumber].remove(index).getValue();
		}
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		int bucketNumber = Math.abs(key.hashCode()) % capacity;
		int index = getBucketIndex(bucketNumber, key);
		if (index == -1) {
			return null;
		} else {
			return table[bucketNumber].get(index).getValue();
		}
	}

	/**
	 * Expands the underlying array to the given capacity.
	 * 
	 * ALSO, rehashes all of the items into this new array. Items will likely
	 * end up in different buckets after the expansion.
	 */
	private void expand(int newCapacity) {
		iterator();
		LinkedList<KVPair>[] newTable = (LinkedList<KVPair>[]) new LinkedList[newCapacity];
		for ( int i = 0; i < newCapacity;i++) {
			newTable[i] = new LinkedList<KVPair>();
		}
		for (LinkedList<KVPair> i: table) {
			if (i.size() == 0) {
				continue;
			}
			for (int index = 0; index < i.size(); index++) {
				KVPair item = i.get(index);
				int bucketNumber = Math.abs(item.getKey().hashCode()) % capacity;
				newTable[bucketNumber].add(item);
			}
		}
		capacity = newCapacity;
		table = newTable;
	}
	
	private int getBucketIndex(int bucketNumber, K key) {
		LinkedList<KVPair> toCheck = table[bucketNumber];
		for (int i = 0; i < toCheck.size(); i++) {
			if (toCheck.get(i).getKey().equals(key)) {
				return i;
			}
		}
		return -1;
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
		private int iterBucket,iterIndex,numOfReturned;
		public HashMapIterator() {
			iterBucket = 0;
			iterIndex = 0;
			numOfReturned = 0;
		}

		@Override
		public boolean hasNext() {
			return numOfReturned < size;
		}

		@Override
		public K next() {
			if (hasNext()){
				while(iterBucket < table.length) {
					if (table[iterBucket].size() > iterIndex) {
						K toReturn = table[iterBucket].get(iterIndex).getKey();
						numOfReturned++;
						iterIndex++;
						return toReturn;
					} else {
						iterBucket++;
						iterIndex = 0;
					}
				}
			}
			throw new IllegalStateException("No next available");
		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair {
		private K key;
		private V value;
		
		public KVPair(K Key, V Value) {
			key = Key;
			value = Value;
		}
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
		
	}

}
