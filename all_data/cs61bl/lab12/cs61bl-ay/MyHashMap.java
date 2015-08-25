import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* YOU ARE NOT allowed to use any library function related to hash maps
 if you feel the need you may use the ArrayList or LinkedList class */

public class MyHashMap<K, V> implements Iterable<K>{
	/* Default size of the map if not set in constructor */
	private static final int DEFAULT_CAPACITY = 30;

	/* Default load factor if not set in constructor */
	private static final double DEFAULT_LOAD_FACTOR = 0.7;

	private int capacity; // the number of buckets in the map
	private int size; // the number of items that have been put into the map
	private double loadfactor;
	private Object[] myMap;

	// TODO You may declare new instance variables here


	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		capacity = DEFAULT_CAPACITY;
		loadfactor = DEFAULT_LOAD_FACTOR;
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		capacity = initialCapacity;
		loadfactor = DEFAULT_LOAD_FACTOR;
		
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
		loadfactor = loadFactor;
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
		return myMap[key.hashCode()]!= null;
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		// TODO Complete this!
		for(Object key:myMap){
			if (((KVPair) myMap[key.hashCode()]).getValue() == value){
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
	public V put(K key, V value) {
		if(key.hashCode()>capacity){
			this.expand((int)(key.hashCode()/loadfactor));
		}
		if(this.containsKey(key)){
			V oldValue = ((KVPair) myMap[key.hashCode()]).getValue();
			((KVPair) myMap[key.hashCode()]).setValue(value);
			size++;
			return oldValue;
		}else{
			myMap[key.hashCode()] = new KVPair(key,value);
			size++;
			return null;
		}	
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		V oldValue = null;
		if(myMap[key.hashCode()] == null){
			return oldValue;
		}else{
			oldValue = ((KVPair) myMap[key.hashCode()]).getValue();
			myMap[key.hashCode()] = null;
			size--;
			return oldValue;
		}
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		for(int i = 0; i < capacity; i++){
			if(i == key.hashCode()){
				if(((KVPair) myMap[i]).getKey().equals(key)){	
					return ((KVPair) myMap[i]).getValue();
				}else{
					continue;
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
		private int counter;
		private int currIndex;

		public HashMapIterator() {
			counter = 0;
			currIndex = 0;		
		}

		@Override
		public boolean hasNext() {
			return counter < size;
		}

		@Override
		public K next() {
			for(int i = currIndex; currIndex < capacity; currIndex ++){
				if(myMap[i] != null){
					counter++;
					currIndex = i;
					return((KVPair) myMap[i]).getKey();
				}
			}
			return null;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair{
		private K key;
		private V value;
		/*
		 * TODO Fill this out if you need it. Maybe not required depending on
		 * your implementation.
		 */
		public KVPair(K newKey, V newValue){
			key = newKey;
			value = newValue;
		}
		
		public V getValue(){
			return value;
		}
		
		public K getKey(){
			return key;
		}
		
		public void setValue(V newValue){
			value = newValue;
		}
	}

}
