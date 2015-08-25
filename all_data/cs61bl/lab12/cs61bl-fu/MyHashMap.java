import java.util.ArrayList;
import java.util.Iterator;

/* YOU ARE NOT allowed to use any library function related to hash maps
 if you feel the need you may use the ArrayList or LinkedList class */

public class MyHashMap<K, V> implements Iterable<K> {
	/* Default size of the map if not set in constructor */
	private static final int DEFAULT_CAPACITY = 5;
	/* Default load factor if not set in constructor */
	private static final double DEFAULT_LOAD_FACTOR = 0.7;

	private int capacity; // the number of buckets in the map
	private int size; // the number of items that have been put into the map
	private double loadFactor; //% Occupied buckets before resize.
	private ArrayList<ArrayList<KVPair>> kList; //<K,V> mapping.
	private ArrayList<ArrayList<KVPair>> vList; //<V,K> mapping.
	
	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair {
		public K key;
		public V value;
		/*
		 * TODO Fill this out if you need it. Maybe not required depending on
		 * your implementation.
		 */
		public KVPair(K k, V v) {
			key = k;
			value = v;
		}
	}
	

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		this(initialCapacity, DEFAULT_LOAD_FACTOR);
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
		if (initialCapacity <= 0) throw new IllegalArgumentException("Need capacity greater than zero");
		if (loadFactor <= 0 && loadFactor > 1)
			throw new IllegalArgumentException("Need load factor between zero and one");
		capacity = initialCapacity;
		this.loadFactor = loadFactor;
		size = 0;
		initList();
	}
	
	/**
	 * Helper method to initialize the hash map variables based on
	 * arguments set from the constructor.
	 */
	private void initList() {
		kList = new ArrayList<ArrayList<KVPair>>(capacity);
		vList = new ArrayList<ArrayList<KVPair>>(capacity);
		for (int i = 0; i < capacity; i ++) {
			kList.add(new ArrayList<KVPair>());
		}
		for (int i = 0; i < capacity; i ++) {
			vList.add(new ArrayList<KVPair>());
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
		if (key == null) return false;
		int code = helperHash(key);
		code = code % capacity;
		ArrayList<KVPair> bucketList = kList.get(code);
		for (KVPair p : bucketList) {
			if (p.key.equals(key)) return true;
		}
		return false;
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run? 
	 * Answer: O(1) with some memory cost (doubled memory cost).
	 */
	public boolean containsValue(V value) {
		int code = helperHash(value);
		code = code % capacity;
		ArrayList<KVPair> bucketList = vList.get(code);
		for (KVPair p : bucketList) {
			if (p.value == value || (p.value != null && p.value.equals(value))) 
				return true;
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
		if (key == null) {
			throw new IllegalArgumentException("No Null Keys are Allowed in the Map");
		}
		V toReturn = putHelper(key,value);
		if ((double)size/capacity > loadFactor) {
			rehash(capacity*2);
		}
		return toReturn;
	}
	
	//Does updating of a key-value pair or adds it to the list.
	private V putHelper (K key, V value) {
		boolean exists = false;
		V toReturn = null;
		int kHash = helperHash(key);
		int vHash = helperHash(value);
		ArrayList<KVPair> kBucket = kList.get(kHash % capacity);
		ArrayList<KVPair> vBucket = vList.get(vHash % capacity);
		for (int i = 0; i < kBucket.size(); i ++) {
			if (kBucket.get(i).key.equals(key)) {
				exists = true;
				toReturn = kBucket.get(i).value;
				kBucket.set(i,new KVPair(key,value));
			}
		}
		if (exists) {
			for (int i = 0; i < vBucket.size(); i ++) {
				if (vBucket.get(i) == toReturn || (vBucket.get(i).value != null && 
						vBucket.get(i).value.equals(toReturn) && vBucket.get(i).key.equals(key))) {
					vBucket.set(i,new KVPair(key,value));
				}
			}
		}
		else {
			size ++;
			kBucket.add(new KVPair(key,value));
			vBucket.add(new KVPair(key,value));
		}
		return toReturn;
	}
	
	/**
	 * Expands the underlying array to the given capacity.
	 * 
	 * ALSO, rehashes all of the items into this new array. Items will likely
	 * end up in different buckets after the expansion.
	 */
	private void expand(int newCapacity) {
		rehash(newCapacity);
	}
	
	//Recreates the hashMap with the specified new capacity..
	private void rehash (int newCap) {
		size = 0;
		capacity = newCap;
		ArrayList<ArrayList<KVPair>> oldK = kList;
		initList();
		for (ArrayList<KVPair> bucketList : oldK) {
			for (KVPair pair : bucketList) {
				put(pair.key, pair.value);
			}
		}
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		if (key == null) { return null; }
		boolean found = false;
		V value = null;
		int hashK = helperHash(key);
		ArrayList<KVPair> bucketList = kList.get(hashK % capacity);
		for (int i = 0; i < bucketList.size(); i++) {
			KVPair p = bucketList.get(i);
			if (p.key.equals(key)) {
				found = true;
				value = p.value;
				bucketList.remove(i);
			}
		}
		if (found) {
			size --;
			int hashV = helperHash(value);
			ArrayList<KVPair> bucketList2 = vList.get(hashV % capacity);
			for (int i = 0; i < bucketList2.size(); i++) {
				KVPair p = bucketList2.get(i);
				if (((p.value == null && p.value == value) || (p.value != null && p.value.equals(value)))
					&& p.key.equals(key)) {
					bucketList2.remove(i);
				}
			}
		}
		return value;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		if (key == null) {
			return null;
		}
		int hashK = helperHash(key);
		ArrayList<KVPair> bucketList1 = kList.get(hashK % capacity);
		for (KVPair p : bucketList1) {
			if (p.key.equals(key)) {
				return p.value;
			}
		}
		return null;
	}

	private int helperHash (Object o) {
		if (o == null) return 0;
		return o.hashCode();
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
		
		int bucketCount;
		int bucketListCount;

		public HashMapIterator() {
			bucketCount = 0;
			bucketListCount = -1;
			
		}

		@Override
		public boolean hasNext() {
			if (bucketListCount < kList.get(bucketCount).size() - 1) return true;
			else {
				for (int i = bucketCount+1; i < kList.size(); i += 1) {
					if (kList.get(i).size() > 0) return true;
				}
			}
			return false;
		}

		@Override
		public K next() {
			while (bucketCount < kList.size()) {
				bucketListCount ++;
				if (bucketListCount < kList.get(bucketCount).size()) 
					return kList.get(bucketCount).get(bucketListCount).key;
				bucketCount += 1;
				bucketListCount = -1;
			}
			
			return null;
		}

		@Override
		public void remove() {
			throw new RuntimeException("No remove available");
		}
	}
}
