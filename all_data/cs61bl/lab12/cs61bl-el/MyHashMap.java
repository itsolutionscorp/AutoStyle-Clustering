import java.util.ArrayList;
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

	// TODO You may declare new instance variables here
	private double loadFactor; 
//	private Object[][] myTable; 
	private LinkedList<KVPair>[] myMap; 
	
	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		this.capacity = DEFAULT_CAPACITY; 
		this.loadFactor = DEFAULT_LOAD_FACTOR;
		myMap = new LinkedList[capacity];
		
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		this.capacity = initialCapacity; 
		this.loadFactor = DEFAULT_LOAD_FACTOR;
		myMap = new LinkedList[capacity];
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
		this.capacity = initialCapacity;
		this.loadFactor = loadFactor;
		myMap = new LinkedList[capacity];
		

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
		if (key == null) {
			return false;
		}

		int hash = Math.abs(key.hashCode() % capacity);
		if (myMap[hash] == null) {
			return false;
		} else {
			if (myMap[hash]!=null && !myMap[hash].isEmpty()) {
				for (int i = 0; i < myMap[hash].size(); i++) {
					if (myMap[hash].get(i).key == key) {
						return true;
					}
				}
			}
			return false;
		}
	
		
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		// TODO Complete this!
		for (int i = 0; i < capacity; i++) {
			if (myMap[i]!= null) {
				for (int j = 0; myMap[i].get(j) != null; j++) {
					if (myMap[i].get(j).value == value) {
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
		// TODO Complete this!
		int position = Math.abs(key.hashCode() % capacity); 
		if (myMap[position] == null) {
			myMap[position] = new LinkedList<KVPair>(); 
			myMap[position].add(new KVPair(key, value)); 
			size++;
			if (size() / capacity > loadFactor) {
//				capacity *= 2; 
				expand(capacity*2); 
			}
			return null; 
		} else {
			if (containsKey(key)) {
				for (int i = 0; myMap[position].get(i) != null; i++) {
					if (myMap[position].get(i).key == key) {
						V previous = myMap[position].get(i).value;
						myMap[position].get(i).value = value; 
						return previous; 
					}
				}
				return null; 
			} else {
				myMap[position].add(new KVPair(key, value)); 
				size++;
				if (size() / capacity > loadFactor) {
//					capacity *= 2; 
					expand(capacity*2); 
				}
				return null; 
			}
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
		if (key == null) {
			return null;
		}
		V rtn;
		int hash = Math.abs(key.hashCode() % capacity);
		if (myMap[hash] == null) {
			return null;
		} else {
			if (!myMap[hash].equals(null)) {
				for (int i = 0; myMap[hash].get(i) != null; i++) {
					if (myMap[hash].get(i).key == key) {
						rtn = myMap[hash].get(i).value;
						myMap[hash].remove(i);
						size--;
						return rtn;
					}
				}
			}
			return null;
		}
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		// TODO Complete this!
		if (key == null) {
			return null;
		}
		int hash = Math.abs(key.hashCode() % capacity);
		if (myMap[hash].isEmpty()) {
			return null;
		} else {
			if (!myMap[hash].equals(null)) {
				for (int i = 0; myMap[hash].get(i) != null; i++) {
					if (myMap[hash].get(i).key == key) {
						return myMap[hash].get(i).value;
					}
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
		Iterator<K> myIt = this.iterator(); 
		LinkedList<KVPair>[] newMap = new LinkedList[newCapacity]; 
		
		while (myIt.hasNext()) {
			K target = myIt.next(); 
			int targetHash = Math.abs(target.hashCode() % newCapacity); 
			if (newMap[targetHash] == null) {
				newMap[targetHash] = new LinkedList<KVPair>(); 
			}
			newMap[targetHash].add(new KVPair(target, this.get(target))); 
		}
		myMap = newMap; 
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
		private int countHash;
		private int countLink;
		private int count;
		private LinkedList<KVPair>[] pointer;

		public HashMapIterator() {
			// TODO Complete this!
			countHash = 0;
			countLink = 0;
			count = 0; 
			pointer = myMap;
			
			
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			return count < size(); 
			
		}

		@Override
		public K next() {
			// TODO Complete this!
			K finalK; 
			while (pointer[countHash] == null) {
				countHash++;
			}
			if (countLink + 1 == pointer[countHash].size()) {
				finalK = pointer[countHash].get(countLink).key; 
				countHash++;
				countLink = 0; 
			} else {
				finalK = pointer[countHash].get(countLink).key;
				countLink++;
			}
			count++; 
			return finalK; 
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			return; 
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
		
		public KVPair(K someKey, V someValue) {
			key = someKey;
			value = someValue; 
		}
		
		public K key() {
			return key; 
		}
	}

}
