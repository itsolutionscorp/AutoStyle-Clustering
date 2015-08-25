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

	// TODO You may declare new instance variables here
	private ArrayList <ArrayList<KVPair>> table; 
	private double myLoadFactor; 
	
	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		capacity = DEFAULT_CAPACITY; 
		myLoadFactor = DEFAULT_LOAD_FACTOR;
		table = new ArrayList<ArrayList<KVPair>>(capacity);
		for (int i = 0; i < capacity; i ++){
			table.add(new ArrayList<KVPair>());
		}
		size = 0; 
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		capacity = initialCapacity; 
		table = new ArrayList<ArrayList<KVPair>>(capacity);
		for (int i = 0; i < capacity; i ++){
			table.add(new ArrayList<KVPair>());
		}
		myLoadFactor = DEFAULT_LOAD_FACTOR;
		size = 0; 
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
		myLoadFactor = loadFactor;
		table = new ArrayList<ArrayList<KVPair>>(capacity);
		for (int i = 0; i < capacity; i ++){
			table.add(new ArrayList<KVPair>());
		}
		size = 0; 
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
		int bucket = key.hashCode() % capacity; 
		if(table.get(bucket)!=null) { 
			for(int i = 0; i < table.get(bucket).size(); i++) { 
				if(table.get(bucket).get(i).getK().equals(key)) { 
					return true; 
				}
			}

		}
		return false;
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		for(int bucket = 0; bucket < table.size(); bucket ++){	
			if(table.get(bucket)!=null) { 
				for(int i = 0; i < table.get(bucket).size(); i++) { 
					if(table.get(bucket).get(i).getV().equals(value)) { 
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
		ArrayList<KVPair> bucket = table.get(key.hashCode() % capacity);
		if (containsKey(key)){
			for(int i = 0; i < bucket.size(); i++) { 
				if(bucket.get(i).getK().equals(key)) { 
					V rtn = bucket.get(i).getV();
					bucket.set(i, new KVPair(key, value));
					return rtn;
				}
			}
		} 
		bucket.add(new KVPair(key, value));
		size++;
		
		if(((double)size/(double)capacity) > myLoadFactor){
			expand(capacity * 2);
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
		// TODO Complete this!
		ArrayList<KVPair> bucket = table.get(key.hashCode() % capacity);
		if (containsKey(key)){
			for(int i = 0; i < bucket.size(); i++) { 
				if(bucket.get(i).getK().equals(key)) { 
					V rtn = bucket.get(i).getV();
					bucket.remove(i);
					size--;
					return rtn;
				}
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
		// TODO Complete this!
		ArrayList<KVPair> bucket = table.get(key.hashCode() % capacity);
		if (containsKey(key)){
			for(int i = 0; i < bucket.size(); i++) { 
				if(bucket.get(i).getK().equals(key)) { 
					return bucket.get(i).getV();
				}
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
		// TODO Complete this!
		for (int i = 0; i < (newCapacity - capacity); i++){
			table.add(new ArrayList<KVPair>());
		}
		
		capacity = newCapacity;
		
		for(int bucket = 0; bucket < table.size(); bucket ++){	
			if(table.get(bucket)!=null) { 
				for(int i = 0; i < table.get(bucket).size(); i++) { 
					put(table.get(bucket).get(i).getK(), table.get(bucket).get(i).getV());
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

		private int myCount;
		private int bucket;
		private int index;
		
		public HashMapIterator() {
			// TODO Complete this!
			myCount = 0;
			bucket = 0;
		}

		@Override
		public boolean hasNext() {
			return myCount < size;
		}

		@Override
		public K next() {
			while(table.get(bucket) == null){
				bucket ++;
			}
			K rtn = table.get(bucket).get(index).getK();
			if (index >= table.get(bucket).size()){
				index = 0;
				bucket++;
			} 
			index ++;
			return rtn;
		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair {
		private K key;
		private V value;
		/*
		 * TODO Fill this out if you need it. Maybe not required depending on
		 * your implementation.
		 */
		public KVPair(K key1, V value1) { 
			key = key1; 
			value = value1; 
		}
		public K getK() { 
			return key; 
		}
		public V getV() { 
			return value; 
		}
	}

}
