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
	private ArrayList<KVPair>[] table;
	private double loadFactor;
	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		table = new ArrayList[DEFAULT_CAPACITY];
		size = 0;
		capacity = DEFAULT_CAPACITY;
		loadFactor = DEFAULT_LOAD_FACTOR;
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		table = new ArrayList[initialCapacity];
		size = 0;
		capacity = initialCapacity;
		loadFactor = DEFAULT_LOAD_FACTOR;
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
		table = new ArrayList[initialCapacity];
		size = 0;
		capacity = initialCapacity;
		this.loadFactor = loadFactor;
	}

	/**
	 * Returns the number of items put into the map (and not subsequently
	 * removed).
	 */
	public int size() {
		return this.size;
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
		int hash;
		if (key == null) hash = 0; 
		else {
			hash = key.hashCode();
		}
		while (hash < table.length) {
			hash -= table.length;
		}
		if (table[hash] == null) return false; //meaning no such key, value pair exists
		else {
			for (int i = 0; i<table[hash].size(); i++) {
				if (table[hash] != null) { // we only want to check arrays that are not null (has a key in it)
					if (table[hash].get(i).returnKey() == key) return true;
				}
			} 
			return false; // meaning we couldn't find any such key inside
		}
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		for (int j = 0; j<table.length; j++) {
			if (table[j] != null) { // meaning an ArrayList exists
				for (int i = 0; i<table[j].size(); i++) {
					if (table[j].get(i) != null) { // we only want to check arrays that are not null (has a key in it)
						if (table[j].get(i).returnValue() == value) return true;
					}
				}
			}
			// else return false;
		}
		return false; // I don't get why this extra line is needed
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
		KVPair pair = new KVPair (key, value);
		int hash;
		if (key == null) hash = 0; 
		else {
			hash = key.hashCode();
		}
		while (hash < table.length) {
			hash -= table.length;
		}
		
		if (table[hash] == null) { // if the arrayList has no values. Need to initialize it
			table[hash] = new ArrayList<KVPair>();
			table[hash].add(pair);
			size++;
			if (size/capacity > this.loadFactor) this.expand(capacity*2);
			return null;
		} else {
			for (int i = 0; i<table[hash].size(); i++) {
				if (table[hash].get(i) == null) {
					table[hash].add(pair);
					size++;
					if (size/capacity > this.loadFactor) this.expand(capacity*2);
					return null;
				} 
				if(table[hash].get(i).returnKey() == key ) {
					KVPair result = table[hash].get(i);
					table[hash].add(i, new KVPair(key, value));
					return result.returnValue();
				}
			}
		} 
		return null; // no idea why I need this here but putting this here gets rid of the red lines
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		int hash;
		if (key == null) hash = 0; 
		else {
			hash = key.hashCode();
		}
		while (hash < table.length) {
			hash -= table.length;
		}
		
		if (table[hash] == null) return null; //meaning no such key, value pair exists
		else {
			for (int i = 0; i<table[hash].size(); i++) {
				if (table[hash] != null) { // we only want to check arrays that are not null (has a key in it)
					if (table[hash].get(i).returnKey() == key) { // we want to find the array that has the same key
						KVPair result = table[hash].get(i);
						table[hash].remove(i);
						size--;
						if(table[hash].size() == 0) table[hash] = null;
						return result.returnValue();
					}
				}
			} 
			return null; // meaning we couldn't find any such key inside
		}
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		int hash;
		if (key == null) hash = 0; 
		else {
			hash = key.hashCode();
		}
		while (hash < table.length) {
			hash -= table.length;
		}
		
		if (table[hash] == null) return null; //meaning no such key, value pair exists
		else {
			for (int i = 0; i<table[hash].size(); i++) {
				if (table[hash] != null) { // we only want to check arrays that are not null (has a key in it)
					if (table[hash].get(i).returnKey() == key) { // we want to find the array that has the same key
						KVPair result = table[hash].get(i);
						return result.returnValue();
					}
				}
			} 
			return null; // meaning we couldn't find any such key inside
		}
	}

	/**
	 * Expands the underlying array to the given capacity.
	 * 
	 * ALSO, rehashes all of the items into this new array. Items will likely
	 * end up in different buckets after the expansion.
	 */
	private void expand(int newCapacity) {
		ArrayList<KVPair>[] tempTable = table; // copying over table
		this.table = new ArrayList[newCapacity]; // pointing the HashMap's table array to a bigger array
		capacity *= 2; // doubling the capacity of the HashMap
		for (int j = 0; j<tempTable.length; j++) {
			if (tempTable[j] != null) { // meaning an ArrayList exists
				for (int i = 0; i<tempTable[j].size(); i++) {
					if (tempTable[j].get(i) != null) { // we only want to check arrays that are not null (has a key in it)
						this.put(tempTable[j].get(i).returnKey(), tempTable[j].get(i).returnValue()); 
						// adding the (K, V) to the new table
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		MyHashMap lol = new MyHashMap();
		lol.put("a", 1);
		System.out.println(lol.containsKey("a"));
		System.out.println(lol.containsValue(1));
		System.out.println(-100%12);
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

		private int count;
		private int arrayIndex;
		private int arrayListIndex;
		
		public HashMapIterator() {
			count = size;
			arrayIndex = 0;
			arrayListIndex = 0;
		}

		@Override
		public boolean hasNext() {
			return count > 0;
		}

		@Override
		public K next() {
			while (table[arrayIndex] == null) arrayIndex++;
			K answer = table[arrayIndex].get(arrayListIndex).returnKey();
			count -- ;
			arrayListIndex++;
			if (table[arrayListIndex] == null){
				// we have reached the end of the arrayList so let's move onto the next array and reset the ArrayListIndex
				arrayIndex++;
				arrayListIndex = 0;
			}
			return answer;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
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
		public KVPair (K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		public K returnKey() {
			 return key;
		}
		
		public V returnValue() {
			return value;
		}
		
		public void setKey(K a) {
			key = a;
		}
		
		public void setValue(V a) {
			value = a;
		}
	}

}
