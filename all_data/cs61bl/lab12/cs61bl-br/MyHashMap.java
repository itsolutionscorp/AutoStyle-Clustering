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
	/* Horizontal Index = hashCode % size
	 * Bucket Index = hashCode/size
	 */
	private ArrayList<ArrayList<KVPair>> hashie;

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		hashie = new ArrayList<ArrayList<KVPair>>();
		capacity = DEFAULT_CAPACITY;
		loadFactor = DEFAULT_LOAD_FACTOR;
		for(int i = 0; i<capacity; i++) {
			hashie.add(new ArrayList<KVPair>());
		}
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		hashie = new ArrayList<ArrayList<KVPair>>();
		capacity = initialCapacity;
		loadFactor = DEFAULT_LOAD_FACTOR;
		for(int i = 0; i<capacity; i++) {
			hashie.add(new ArrayList<KVPair>());
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
		hashie = new ArrayList<ArrayList<V>>();
		capacity = initialCapacity;
		this.loadFactor = loadFactor;
		for(int i = 0; i<capacity; i++) {
			hashie.add(new ArrayList<KVPair>());
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
	/* Horizontal Index = hashCode % size
	 * Bucket Index = hashCode/size
	 */
	public boolean containsKey(K key) {
		ArrayList<V> temp = hashie.get(key.hashCode()%capacity);
		return (temp.get(key.hashCode()/capacity)!=null);
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		int size = hashie.size();
		for(int i = 0; i<size; i++) {
			for(int j = 0; j<size2; j++) {
				if(hashie.get(j).get(i).getValue().equals(value) {
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
		V rtn = null;
		if(hashie.get(key.hashCode()%capacity).get(key.hashCode()/capacity) != null) {
			rtn = hashie.get(key.hashCode()%capacity).get(key.hashCode()/capacity).getValue();
		}
		hashie.get(key.hashCode()%capacity).add(key.hashCode()/capacity, new KVPair(key,value));
		size++;
		return rtn;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		size--;
		V rtn = null;
		if(hashie.get(key.hashCode()%capacity).get(key.hashCode()/capacity) != null) {
			rtn = hashie.get(key.hashCode()%capacity).remove(key.hashCode()/capacity).getValue();
		return rtn;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		V rtn = null;
		if(hashie.get(key.hashCode()%capacity).get(key.hashCode()/capacity) != null) {
			rtn = hashie.get(key.hashCode()%capacity).get(key.hashCode()/capacity).getValue();
		}
		return rtn;
	}

	/**
	 * Expands the underlying array to the given capacity.
	 * 
	 * ALSO, rehashes all of the items into this new array. Items will likely
	 * end up in different buckets after the expansion.
	 */
	private void expand(int newCapacity) {
		capacity = newCapacity;
		ArrayList<ArrayList<V>> temp = new ArrayList<ArrayList<V>>();
		int size = hashie.size();
		for(int i = 0; i<size; i++){
			int size2 = hashie.get(i).size();
			for(int j = 0; j<size2; j++) {
				if(hashie.get(i).get(j) != null) {
					int hash = i+j; //original hashCode!
					temp.get(hash%capacity).add(hash/capacity, hashie.get(i).get(j));
				}
			}
		}
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
		private int outerIndex;
		private int numSeen;
		private KVPair prevIndex;
		public HashMapIterator() {
			outerIndex = 0;
			numSeen=0;
			prevIndex = 0;
		}

		@Override
		public boolean hasNext() {
			if(outerIndex >= capacity) {
				return false;
			}
			return true;
		}

		@Override
		public K next() {
			if(numSeen==hashie.get(a).size()) {
				outerIndex++;
				prevIndex = 0;
				numSeen = 0;
				return next();
			}
			ArrayList<KVPair> hashie2 = hashie.get(outerIndex);
			KVPair rtn = null;
			int index = prevIndex + 1;
			while(rtn == null) {
				rtn = hashie2.get(index);
			}
			return rtn.getKey();
		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair {
		private K key;
		private V value;
		public KVPair(K key, V value){
			this.key = key;
			this.value = value;
		}
		public K getKey() {
			return key;
		}
		public V getValue() {
			return value;
		}
	}

}
