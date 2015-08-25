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
	KVPair[] pairs;
	private double myLoadFactor;
	
	
	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {		
		capacity = DEFAULT_CAPACITY;
		pairs = (KVPair[])new MyHashMap.KVPair[capacity];
		size = 0;
		myLoadFactor = DEFAULT_LOAD_FACTOR;
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		capacity = initialCapacity;
		pairs = (KVPair[])new MyHashMap.KVPair[capacity];
		size = 0;
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
		size = 0;
		myLoadFactor = loadFactor;
		pairs = (KVPair[])new MyHashMap.KVPair[capacity];

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
	 * Returns the load factor of the underlying array of the map.
	 */
	public double loadFactor(){
		return this.myLoadFactor;
	}
	
	public int hashFunction(K key){ 
		return Math.abs(key.hashCode() % capacity);
	}

	/**
	 * Returns whether the map contains the given key. Runs in O(1) time on
	 * average, relative to the size of the map.
	 */
	public boolean containsKey(K key) {
		Iterator<K> iter = iterator();
		while(iter.hasNext()){
			if(iter.next().equals(key)) return true;
		}
		return false;
						
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		
		Iterator<K> iter = iterator();
		while(iter.hasNext()){
			if(get(iter.next()).equals(value)) 
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
		size++;
		if(size()/(double)capacity > loadFactor()){
			expand(capacity*2);				
		}
		
		int index = hashFunction(key);		
		
		while(pairs[index] != null){			
			if(pairs[index].key().equals(key)){				
				 	
				V val = pairs[index].value();
				pairs[index] = new KVPair(key, value);
				return val;
			}
			index++;						
		}
		pairs[index] = new KVPair(key, value);
		
		return null;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
	
		int start = hashFunction(key);
		for(int i = start; i < pairs.length; i++){
			if(pairs[i].key().equals(key)){
				size--;
				V val = pairs[i].value();
				pairs[i] = null;
				return val;
				
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
		int start = hashFunction(key);
		for(int i = start; i < pairs.length; i++){
			if(pairs[i] != null && pairs[i].key().equals(key)) 
				return pairs[i].value();
				
				
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
		
		KVPair[] copy = (KVPair[])new MyHashMap.KVPair[capacity];
		
		for(int i = 0; i < pairs.length; i++){
			copy[i] = pairs[i];
			
		}
		
		capacity = newCapacity;		
		pairs = (KVPair[])new MyHashMap.KVPair[capacity];
		for(int i = 0; i < copy.length; i++){
			if(copy[i] != null)
				put(copy[i].key(), copy[i].value()); // uses new hash function
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
		int index;
		

		public HashMapIterator() {
			index = 0;					
		}

		@Override
		public boolean hasNext() {		
			if(index < pairs.length){
				for(int i = index; i < pairs.length; i++){
					if(pairs[i] != null) return true;
				}
			}
			return false;			
		}

		@Override
		public K next() {			
			KVPair result = pairs[index];
			while(result == null){
				index++;
				result = pairs[index];
			}
			index++;
			return result.key();
		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair {
		private K key;
		private V value;

		public KVPair(K theKey, V theValue){
			key = theKey;
			value = theValue;
		}
		public K key(){
			return key;
		}
		public V value(){
			return value;
		}

	}

}
