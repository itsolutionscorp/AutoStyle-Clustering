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
	private double load_factor; 
	private int counter; 
	private int bucket_counter; 
    
	// TODO You may declare new instance variables here
	
	LinkedList<KVPair>[] table;  
	 
	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		capacity = 0; 
		table = new LinkedList[capacity]; 
		load_factor = DEFAULT_LOAD_FACTOR; 
		
		
		
		
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		capacity = initialCapacity; 
		table = new LinkedList[capacity];
		load_factor = DEFAULT_LOAD_FACTOR; 
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
       load_factor = loadFactor; 
       table = new LinkedList[capacity]; 
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
		int value = key.hashCode(); 
		for (int k = 0; k < table[value].size(); k += 1) {
		    KVPair a = table[value].get(k); 
			if (a.getKey().equals(key)) {
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
		int v = key.hashCode();
		KVPair a = new KVPair(); 
		a.key = key; 
		a.value = value; 
		int k = 0; 
		for (int i = 0; i < table[v].size(); i += 1) {
			KVPair c = table[v].get(i); 
			if (c.getKey().equals(key)) {
				table[v].remove(key); 
				table[v].add(i,a); 
			}
			else if (table[v].get(i) != null) { 
				k += 1 ; 
			}
			else {
				table[v].add(i, a);
			}
			}
			if (size() > load_factor) {
				this.expand(capacity*2);
			}
			if (k > 0) {
				KVPair b = table[v].get(k-1); 
				return b.getValue(); 
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
		int value = key.hashCode(); 
		for (int i = 0; i < table[value].size(); i += 1) {
			KVPair a = table[value].get(i); 
			if (a.getKey().equals(key)) {
				V v = a.getValue(); 
				table[value].remove(i); 
				return v; 
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
		int value = key.hashCode(); 
		for (int i = 0; i < table[value].size(); i+=1) {
			KVPair a = table[value].get(i); 
			if (a.getKey().equals(key)) {
				return a.getValue(); 
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
		LinkedList<KVPair>[] temp = table; 
		table = new LinkedList[newCapacity]; 
		for (int i = 0; i < temp.length; i+=1) {
			table[i] = temp[i]; 
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

		public HashMapIterator() {
			// TODO Complete this!
			counter = 0; 
			bucket_counter = 0; 
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			return table[counter].get(bucket_counter) != null; 
		}
		
		@Override
		public K next() {
			// TODO Complete this!
			KVPair a = table[counter].get(bucket_counter); 
			K temp = a.getKey(); 
			if (table[counter].get(bucket_counter+1) != null) {
				bucket_counter += 1; 
				return temp; 
			}
			for (int i = 0; i < capacity-counter; i += 1 ) {
				if (table[i+counter] != null) {
					counter += i;
					bucket_counter = 0; 
					return temp;
			}
				}
			 return null;
		}
		
		public void remove() {
			//Not implemented. Added to allow iterator to run. 
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
    private K getKey() {
    	return key; 
	}
    
    private V getValue() {
    	return value; 
    }
	}}


