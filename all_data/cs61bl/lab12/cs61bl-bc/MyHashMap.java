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
	private double loadFactor;
	// TODO You may declare new instance variables here
	LinkedList[] bucketArray;

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		size = 0;
		capacity = DEFAULT_CAPACITY;
		loadFactor = DEFAULT_LOAD_FACTOR;
		bucketArray = new LinkedList[capacity];
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		size = 0;
		capacity = initialCapacity;
		loadFactor = DEFAULT_LOAD_FACTOR;
		bucketArray = new LinkedList[capacity];
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
		size = 0;
		capacity = initialCapacity;
		this.loadFactor = loadFactor;
		bucketArray = new LinkedList[capacity];

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
		for(int i = 0 ; i < bucketArray[key.hashCode()].size() ; i++){
			KVPair pair = (KVPair) bucketArray[key.hashCode()].get(i);
			if(pair.getKey().equals(key)){
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
		for (int i = 0; i < capacity ; i++){
			for(int j = 0 ; j<  bucketArray[i].size(); j++){
				KVPair pair = (KVPair) bucketArray[i].get(j);
				if(pair.getValue().equals(value)){
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
			KVPair input = new KVPair();
			input.key = key;
			input.value = value;
			if(containsKey(key)){
				for(int i = 0 ; i < bucketArray[key.hashCode()].size() ; i++){
					KVPair pair = (KVPair) bucketArray[key.hashCode()].get(i);
					if(pair.getKey().equals(key)){
						V temp = pair.getValue();
						pair.value = value;
						return temp;
					}
				}
			}
			if(size+1/capacity < loadFactor){
				bucketArray[key.hashCode()].add(input);
				return null;
			}
			else{
				expand(capacity*2);
				bucketArray[key.hashCode()].add(input);
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
		// TODO Complete this!
		if(containsKey(key)){
			for ( int i = 0; i < bucketArray[key.hashCode()].size() ; i ++){
				KVPair pair = (KVPair) bucketArray[key.hashCode()].get(i);
				if(pair.getKey().equals(key)){
					return (V) bucketArray[key.hashCode()].remove(i);
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
		if(containsKey(key)){
			for ( int i = 0; i < bucketArray[key.hashCode()].size() ; i ++){
				KVPair pair = (KVPair) bucketArray[key.hashCode()].get(i);
				if(pair.getKey().equals(key)){
					return pair.getValue();
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
		LinkedList[] newList = new LinkedList[newCapacity];
		for (int i = 0 ; i< capacity ; i++){
			newList[i] = bucketArray[i];
		}
		bucketArray = newList;
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

		int arrayIndex;
		int nodeIndex;
		public HashMapIterator() {
			// TODO Complete this!
			arrayIndex  = 0;
			nodeIndex = 0;
			
		}

		
		public boolean hasNext() {
			// TODO Complete this!
			if(arrayIndex == capacity -1  && nodeIndex == bucketArray[arrayIndex].size()-1 ){
				return false;
			}
			else{
				for( int i = arrayIndex ; i<capacity ; i++){
					for(int j = nodeIndex ; j<bucketArray[arrayIndex].size() ; j++){
						if (bucketArray[arrayIndex].get(j) != null){
							return true;
						}
					}
				}	
			}
			return false;
		}


		public K next() {
			// TODO Complete this!
			if (nodeIndex == bucketArray[arrayIndex].size()-1 ){
				arrayIndex++;
				int tempNode = nodeIndex;
				nodeIndex = 0;
				KVPair pair = (KVPair) bucketArray[arrayIndex-1].get(tempNode);
				return pair.getKey();
			}
			else{
				nodeIndex++;
				KVPair pair = (KVPair) bucketArray[arrayIndex].get(nodeIndex-1);
				return pair.getKey();
			}
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
		public K getKey(){
			return key;
		}
		public V getValue(){
			return value;
		}
	}

}
