import java.util.Arrays;
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
	private int size; // the number of items that have been put into the map
	LinkedList<KVPair>[] myMap;
	private double loadFactor;
	private String name;
	

	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		capacity = DEFAULT_CAPACITY;
		size = 0;
		
		LinkedList<KVPair>[] myMap = new LinkedList[capacity];
		
		for (int i = 0; i < capacity; i++){
			name = "list"+ "" + i+1;
			LinkedList<KVPair> name = new LinkedList<KVPair>();
			myMap[i] = name;
		}
		
		
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		capacity = initialCapacity;
		size = 0;
		
		LinkedList<KVPair>[] myMap = new LinkedList[capacity];
		
		for (int i = 0; i < capacity; i++){
			name = "list"+ "" + i+1;
			LinkedList<KVPair> name = new LinkedList<KVPair>();
			myMap[i] = name;
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
		capacity = initialCapacity;
		this.loadFactor = loadFactor;
		if (myMap.length/capacity > loadFactor) {
			capacity = capacity * 2;
			LinkedList<KVPair>[] myMap = new LinkedList[capacity];
			
			for (int i = 0; i < capacity; i++){
				name = "list"+ "" + i+1;
				LinkedList<KVPair> name = new LinkedList<KVPair>();
				myMap[i] = name;
			}
		}
		else{
		LinkedList<KVPair>[] myMap = new LinkedList[capacity];
		
		for (int i = 0; i < capacity; i++){
			name = "list"+ "" + i+1;
			LinkedList<KVPair> name = new LinkedList<KVPair>();
			myMap[i] = name;
		}}
		
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
		myMap = this.myMap;
		if (Arrays.asList(myMap).contains((key.hashCode() % myMap.length))) {
			//iterate through linked list at specified array index (the bucket) to find key
			for (int i = 0; i < myMap[key.hashCode() % myMap.length].size(); i++){
				KVPair x = myMap[key.hashCode() % myMap.length].get(i);
				if (x.getK() == key) {
					return true;
				}
				else {return false;}
			}
			
		}
		else {return false;}
		return false;
		
		
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		// TODO Complete this!
		if (value != null) {
		for (int i=0; i< myMap.length; i++) {
			
			for (int j = 0; j < myMap[i].size(); j++) {
				KVPair x = myMap[i].get(j);
				if (x.getV() == value) {
					return true;
				}
				else {return false;}
			}
		}
		}
		else {
		return false;}
		
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
		K newkey = key;
		if (this.containsKey(key)) {
			this.get(key);}
		else {return null;}
			
			//replace value
			for (int i = 0; i < myMap[key.hashCode() % myMap.length].size(); i++){
				KVPair x = myMap[key.hashCode() % myMap.length].get(i);
				if (x.getK() == key || x.getK() == null) {
					this.remove(key);
					x.key = newkey;
				}
				else {break;}
			}
		
		size++;
		
		if (size / this.capacity > this.loadFactor) {
			int newCapacity = capacity *2;
			this.expand(newCapacity);
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
		for (int i = 0; i < myMap[key.hashCode() % myMap.length].size(); i++){
			KVPair x = myMap[key.hashCode() % myMap.length].get(i);
			if (x.getK() == key) {
				V value = x.value;
				x.key = null;
				return value;
			}
			else {
				return null;
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
		if (true) {
		
		for (int i = 0; i < myMap[key.hashCode() % myMap.length].size(); i++) {
			KVPair x = myMap[key.hashCode() % myMap.length].get(i);
			if (x.key == key) {
				if (x.value != null){
				return x.value;}
				else {return null;}
			}
			else { return null;}
		}}
		
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
		
		LinkedList<KVPair>[] tempMap = new LinkedList[this.size];
		System.arraycopy(this, 0, tempMap, 0, this.size );
		
		LinkedList<KVPair>[] myMap = new LinkedList[newCapacity];
		
		for (int i = 0; i < newCapacity; i++){
			name = "list"+ "" + i+1;
			LinkedList<KVPair> name = new LinkedList<KVPair>();
			myMap[i] = name;
			
			//add values to new myMap
			for (int j = 0; j < tempMap[i].size(); j++) {
				K key = tempMap[i].get(j).key;
				V value = tempMap[i].get(j).value;
				
				LinkedList<MyHashMap<K, V>.KVPair> list;
				list = myMap[key.hashCode() % newCapacity];
				
				list.add(new KVPair(key, value));
				
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
		
		int index;
		LinkedList<KVPair>[] myMap;
		int counter;
		Iterator initIterator;
		

		public HashMapIterator() {
			// TODO Complete this!
			Iterator<LinkedList<KVPair>[]> itr = this.initIterator();
		}
		
		public Iterator<LinkedList<MyHashMap<K, V>.KVPair>[]> initIterator() {
			index =-1;
			return initIterator;
			
		}

		@Override
		public boolean hasNext() {
			if (index < myMap[counter].size()){
				return true;}
			
			else {return false;}
			
		}

		@Override
		public K next() {
			index++;
			return myMap[counter].get(index).getK();
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
		public K getK() {
			return key;
		}
		public V getV() {
			return value;
		}
		public KVPair(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
	}

}