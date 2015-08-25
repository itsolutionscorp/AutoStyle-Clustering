import java.util.Iterator;
import java.util.ArrayList;

import java.util.LinkedList;
/* YOU ARE NOT allowed to use any library function related to hash maps
 if you feel the need you may use the ArrayList or LinkedList class */

public class MyHashMap<K, V> implements Iterable<K> {
	/* Default size of the map if not set in constructor */
	private static final int DEFAULT_CAPACITY = 30;

	/* Default load factor if not set in constructor */
	private static final double DEFAULT_LOAD_FACTOR = 0.7;

	private int capacity; // the number of buckets in the map
	private int size = DEFAULT_CAPACITY; // the number of items that have been put into the map
	private int totalRelations; 
	// TODO You may declare new instance variables here
	private LinkedList<KVPair>[] bucket;
	//private ArrayList<V> values;
	


	
	
	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		totalRelations = 0;	
		bucket = new LinkedList[size];	
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		size = initialCapacity;
		totalRelations = 0;
		bucket = new LinkedList[size];	
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
		size = initialCapacity;
		loadFactor = loadFactor;
		totalRelations = 0;
		bucket = new LinkedList[size];	
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
		boolean ret = false;
		int num = key.hashCode() % size;
		// first spot to check after calling hashCode mod arraySize
		if (bucket[num] == null)
			ret = false;
		// loops through all of bucket list 
		else{
			for(int i = 0; i < bucket[num].size(); i++)
				if(bucket[num].get(i).key.equals(key)){
					ret = true;
					break;
				}
		}
		return ret;
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		boolean ret = false;	
		// go through the hash 
		for(int i = 0; i < size; i++){
			// if bucket element is not null, we want to investigate 
			// the linked list with a for loop
			if(bucket[i] != null){
				for(int j = 0; j < bucket[i].size(); j++){
					if(bucket[i].get(j).value.equals(value)){
						ret = true;
						break;
					}	
				}
			}
		}
		return ret; 
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
		int num;
		V ret = null;
		num = key.hashCode() % size;
		// put key and value to the first hashChode mod arraySize 
		if(bucket[num] == null){
			// create a new linked list to store key and value
			bucket[num] = new LinkedList<KVPair>();
			// add the key and value but must create new object to 
			// extend linked list
			bucket[num].add(new KVPair(key, value));
			// modify number of new keys 
			totalRelations += 1;
			
		} else {
			for(int i = 0; i < bucket[num].size(); i++){
				if(bucket[num].get(i).key.equals(key)){
					// will return the previous value of the key. 
					ret = bucket[num].get(i).value;
					bucket[num].get(i).value = value;
					return ret;
				}
			}
			// add new key and value by creating a new hashitem. 
			bucket[num].add(new KVPair(key, value));
			//modify number of keys 
			totalRelations += 1;
		}
		return ret;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// initialize return value to null
		V ret = null;
		// create hashmap 
		int num = key.hashCode() % size;
		// if the key is in the hash 
		if(bucket[num].size() == 1){
			if(bucket[num].get(0).key.equals(key)){
				ret = bucket[num].get(0).value;
				bucket[num] = null;
				totalRelations--;
			}
		} else {
			for(int i = 0; i < bucket[num].size(); i++) {
				if(bucket[num].get(i).key.equals(key)){
					ret = bucket[num].get(i).value;
					bucket[num].remove(i);
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
	public V get(K key) {
		int num = key.hashCode() % size;
		V ret = null;
		if(this.containsKey(key)){
			for(int i = 0; i < bucket[num].size(); i++){
				if(bucket[num].get(i).key.equals(key)){
					ret = bucket[num].get(i).value;
					//break;
					return ret;
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
	private void expand(int newCapacity) {
		// TODO Complete this!
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

		public HashMapIterator() {
			// TODO Complete this!
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			return false;
		}

		@Override
		public K next() {
			// TODO Complete this!
			return null;
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
		public KVPair(K Key, V Value) {
			this.key = Key;
			this.value = Value;
		}
				
		public void setValue(V val) {
			this.value = val;
		}
		
	}

}
