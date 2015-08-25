import java.util.Iterator;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;


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
	
	private float maxLoadFactor;
	private ArrayList<LinkedList<KVPair>> bucket;
	private HashSet<K> keys;
	private HashSet<V> values;
	private KVPair next;

	
	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		capacity = DEFAULT_CAPACITY;
		maxLoadFactor = (float) DEFAULT_LOAD_FACTOR;
		bucket = new ArrayList<LinkedList<KVPair>>(capacity);
		initialLinkedList(bucket, capacity);
		keys = new HashSet<K>();
		values = new HashSet<V>();
		
	}
	
	private void initialLinkedList(ArrayList<LinkedList<KVPair>> list, int cap) {
		for (int i = 0; i < cap; i++ ) {
			list.add(i, new LinkedList<KVPair>());
		}
	}


	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		capacity = initialCapacity;
		maxLoadFactor = (float) DEFAULT_LOAD_FACTOR;
		bucket = new ArrayList<LinkedList<KVPair>>(capacity);
		initialLinkedList(bucket, capacity);
		keys = new HashSet<K>();
		values = new HashSet<V>();

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
		maxLoadFactor = (float) loadFactor;
		bucket = new ArrayList<LinkedList<KVPair>>(capacity);
		initialLinkedList(bucket, capacity);
		keys = new HashSet<K>();
		values = new HashSet<V>();

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
		// TODO Complete this!
		return keys.contains(key);

	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		// TODO Complete this!
		return values.contains(value);

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
		// TODO Complete this!
		int index = modulo(key.hashCode(), capacity);
		KVPair temp = new KVPair(key, value);
		if(keys.contains(key)){
			V oldValue = get(key);
			LinkedList<KVPair> search = bucket.get(index);
			for (KVPair pair : search) {
				if (pair.getKey().equals(key)) {
					pair.setValue(value);
					return oldValue;
				}
			}
		} 
		else {
			
			float currentLoadFactor = (float) size / capacity;
	        if (currentLoadFactor > maxLoadFactor) {
	            expand(capacity*2);
	        }
			keys.add(key);
	        bucket.get(index).add(temp);

	        size += 1;
		}
		
        return null;

	}
	
	private int modulo(int val, int mod) {
		if (val >= 0) {
			return val % mod;
		}
		else {
			return mod - (-1 * val) % mod;
		}
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		KVPair removePair = null;
		if (key == null){
			return null;
		}
		if(!keys.contains(key)){
			return null;
		}
		int index = key.hashCode()% capacity;
		LinkedList<KVPair> search = bucket.get(index);
		for (KVPair pair : search) {
			if (pair.getKey().equals(key)) {
				removePair = pair;
				break;
			}
		}

		search.remove(removePair);
		size -= 1;
		keys.remove(key);
		return removePair.getValue();
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		// TODO Complete this!
		if (key == null){
			return null;
		}
		if (!containsKey(key)) {
			return null;
		} else {
			int index = key.hashCode() % capacity;
			LinkedList<KVPair> search = bucket.get(index);
			for (KVPair pair : search) {
				if (pair.getKey().equals(key)) {
					return pair.getValue();
				}
			}
			return null;
		}
	}

	/**
	 * Expands the underlying array to the given capacity.
	 * 
	 * ALSO, rehashes all of the items into this new array. Items will likely
	 * end up in different buckets after the expansion.
	 */
	private void expand(int newCapacity) {
		// TODO Complete this!
		ArrayList<LinkedList<KVPair>> temp = new ArrayList<LinkedList<KVPair>>(newCapacity);
		initialLinkedList(temp, newCapacity);
		for (K key : keys){
			addKVPair(key, get(key), temp, newCapacity);
		}
		capacity = newCapacity;
		bucket = temp;

	}
	
	private void addKVPair(K key, V value, ArrayList<LinkedList<KVPair>> array, int nCap){
		int index = key.hashCode() % nCap;
		LinkedList<KVPair> put = array.get(index);
		KVPair newPair = new KVPair(key, value);
		for (KVPair pair : put){
			if (pair.getKey().equals(key)){
				pair = newPair;
				return;
			}
		}
		put.addFirst(newPair);
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
		
		private KVPair head; 
		private LinkedList<KVPair> next;
		private int index;
		
		
		
		public HashMapIterator() {
			// TODO Complete this!
			this.head = head;
			this.next = next;
	
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			return (head != null);
		}

		@Override
		public K next() {
			// TODO Complete this!
			K temp = head.key;
			head = head.next;

			return temp;
		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair {
		private K key;
		private V value;
		private KVPair next;

		/*
		 * TODO Fill this out if you need it. Maybe not required depending on
		 * your implementation.
		 */
		
		private KVPair(K key, V value) {
			this.key = key;
			this.value = value;

		}
		
		private KVPair(K key, KVPair next) {
			this.key = key;
			this.next = next;

		}
		
        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
        
        public void setValue(V newValue) {
        	value = newValue;
        }
        
        

        public String toString() {
            return "(" + key + ", " + value + ") ";
	}

}
	   
	
}
