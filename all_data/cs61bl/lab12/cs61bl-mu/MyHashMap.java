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
	private double load;
	private LinkedList[] arr;
	
	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		arr = new LinkedList[DEFAULT_CAPACITY];
		capacity = DEFAULT_CAPACITY;
		load = DEFAULT_LOAD_FACTOR;
		size = 0;
		// TODO Complete this!
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		arr = new LinkedList[initialCapacity];
		capacity = initialCapacity;
		load = DEFAULT_LOAD_FACTOR;
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
		arr = new LinkedList[initialCapacity];
		capacity = initialCapacity;
		load = loadFactor;
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
		// TODO Complete this!
		int i = key.hashCode()%capacity;
		if(arr[i] == null){
			return false;
		}
		LinkedList node = arr[i];
		
			for(Object j : node){
			if( ((KVPair)j).getKey()==key){
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
		// TODO Complete this!
		for(int i = 0; i < capacity; i++){
			LinkedList node = arr[i];
			if(arr[i] == null){
				return false;
			}
			for(Object j: node){
				if(((KVPair)j).getValue() == value){
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
		// TODO Complete this!
		int i = key.hashCode()%capacity;
		if(i >= capacity){
			return null;
		}
		if(arr[i] == null){
			arr[i] = new LinkedList();
			LinkedList node = arr[i];
			node.add(new KVPair(key,value));
			size++;
			if ((double) size/capacity > load) {
				expand(capacity*2);
			}
		}
		LinkedList node = arr[i];
		
		
			for(Object j : node){
			if( ((KVPair)j).getKey()==key){
				V temp = ((KVPair)j).getValue();
				((KVPair)j).setValue(value);
				return temp;
			}
			
			}
			node.add(new KVPair(key,value));
			size++;
			if ((double) size/capacity > load) {
				expand(capacity*2);
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
		int i = key.hashCode()%capacity;
		if(arr[i] == null){
			return null;
		}
		LinkedList node = arr[i];
		
		
		for(Object j : node){
		if( ((KVPair)j).getKey()==key){
			V temp = ((KVPair)j).getValue();
			node.remove(j);
			size--;
			return temp;
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
		int i = key.hashCode()%capacity;
		
		if(arr[i] == null){
			return null;
		}
		LinkedList node = arr[i];
		
		
		for(Object j : node){
		if( ((KVPair)j).getKey()==key){
			V temp = ((KVPair)j).getValue();
			return temp;
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
		LinkedList[] arrs = new LinkedList[newCapacity];
		for(int i = 0; i < capacity; i++){
			arrs[i] = arr[i];
		}
		capacity = newCapacity;
		arr = arrs;
		
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
		private int index;
		private int count;
		public HashMapIterator() {
			// TODO Complete this!
			 index = 0;
			 count = 0;;
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			return ( count < size );
			
		}

		@Override
		public K next() {
			// TODO Complete this!
			
			if(hasNext()){
				int k = 0;
				for(int i = 0; i < capacity; i++){
				if(arr[i] == null){
					continue;
				}
				
				LinkedList node = arr[i];
				
				for(Object j : node){
				
					if(k== count){
						
					K keys = ((KVPair)j).getKey();
					count++;
					return keys;
					}
					k++;
				
			}
		}
			}
			return null;
		}
			
		public void remove() {
			// TODO Complete this!
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
		public KVPair(K keys, V values){
			key = keys;
			value = values;
		}
		public K getKey(){
			return key;
		}
		public V getValue(){
			return value;
		}
		public void setKey(K keys){
			 key = keys;
		}
		public void setValue(V values){
			value = values;
		}
	}

}
