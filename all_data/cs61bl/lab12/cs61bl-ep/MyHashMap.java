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

	private double myLoadFactor;
	private LinkedList<KVPair>[] arr; 
	
	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		capacity = DEFAULT_CAPACITY;
		arr = new LinkedList[capacity];
		myLoadFactor = DEFAULT_LOAD_FACTOR;
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		capacity = initialCapacity;
		arr = new LinkedList[capacity];
		myLoadFactor = DEFAULT_LOAD_FACTOR;
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
		myLoadFactor = loadFactor;
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
		if (size() == 0) {
			return false;
		}
		if (arr[key.hashCode()%capacity] == null) {
			return false;
		}
		for (KVPair pair : arr[key.hashCode()%capacity]) {
				if (pair.key() == key) {
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
		if (size() == 0) {
			return false;
		}
		for (LinkedList<KVPair> bucket : arr) {
			if (bucket == null) {
				continue;
			}
			else {
				for (KVPair pair : bucket) {
					if (pair.value() == value) {
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
		if (containsKey(key)) {
			for (KVPair pair : arr[key.hashCode()%capacity]) {
				if (pair.key() == key) {
					V previousValue = pair.value();
					pair.setValue(value);
					return previousValue;
				}
			}
		}
		else if (arr[key.hashCode()%capacity] == null) {
			arr[key.hashCode()%capacity] = new LinkedList<KVPair>();
		}
		arr[key.hashCode()%capacity].add(new KVPair(key, value));
		size++;

		if (((double)size/capacity) > myLoadFactor) {
			expand(2*capacity);
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
		V val = null;
		if (containsKey(key)) {
			for (KVPair pair : arr[key.hashCode()%capacity]) {
				if (pair.key() == key) {
					val = pair.value();
					arr[key.hashCode()%capacity].remove(pair);
				}
			}
			size --;
		}
		return val;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		V val = null;
		if (containsKey(key)) {
			for (KVPair pair : arr[key.hashCode()%capacity]) {
				if (pair.key() == key) {
					val = pair.value();
				}
			}
		}
		return val;
	}

	/**
	 * Expands the underlying array to the given capacity.
	 * 
	 * ALSO, rehashes all of the items into this new array. Items will likely
	 * end up in different buckets after the expansion.
	 */
	private void expand(int newCapacity) {
		/*LinkedList<KVPair>[] arr2 = new LinkedList[newCapacity];
		capacity = newCapacity;
		int i = 0;
		while (i < size) {
			arr2[i] = arr[i];
			i ++;
		}
		arr = arr2;*/
		LinkedList<KVPair>[] arr2 = new LinkedList[newCapacity];
		
		int i = 0;
		while (i < capacity) {
			arr2[i] = arr[i];
			i++;
		}
		arr = arr2;
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

		int index;
		int arrIndex;
		int bucketIndex;
		
		public HashMapIterator() {
			index = 0;
			arrIndex = 0;
			bucketIndex = 0;
		}

		@Override
		public boolean hasNext() {
			return (index < size);
		}

		@Override
		public K next() {
			if (arr[arrIndex].size() == 0) {
				arrIndex ++;
			}
			K nextKey = arr[arrIndex].get(bucketIndex).key();
			index ++;
			bucketIndex ++;
			if (bucketIndex == arr[arrIndex].size()) {
				arrIndex ++;
				bucketIndex = 0;
			}
			return nextKey;		
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
		
		public K key() {
			return key;
		}
		
		public void setValue(V val) {
			value = val;
		}
		
		public V value() {
			return value;
		}
	}

}