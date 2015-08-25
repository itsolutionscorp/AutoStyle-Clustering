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
	public int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	
//	private ArrayList<K> keys = new ArrayList<K>();
//	private ArrayList<V> values = new ArrayList<V>();
	
	//private 
//	private K[] keys;
//	private V[] values;
	public double factor = DEFAULT_LOAD_FACTOR;
	public LinkedList[] pairs;// = new KVPair[];
	

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		capacity = DEFAULT_CAPACITY;
		size = 0;
		pairs = new LinkedList[capacity];
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		capacity = initialCapacity;
		size = 0;
		pairs = new LinkedList[capacity];
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
		factor = loadFactor;
		size = 0;
		pairs = new LinkedList[capacity];
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
		int hash = (key.hashCode()) % capacity;
		LinkedList currNode = pairs[hash];
		if (currNode != null) {
			int size = currNode.size();
			for (int i = 0; i < size; i++) {
				KVPair currPair = (KVPair) currNode.get(i);
				if (currPair.getKey() == key) {
					return true;
				}
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
		for (LinkedList list : pairs) {
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					KVPair currPair = (KVPair) list.get(i);
					if (currPair.getVal() == value) {
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
		int hash1 = key.hashCode() % capacity;
		if (pairs[hash1] != null) {
			for (int i = 0; i < pairs[hash1].size(); i ++) {
				KVPair currPair = (KVPair) pairs[hash1].get(i);
				if (currPair.getKey() == key) {
					V toReturn = currPair.getVal();
					currPair.setVal(value);
					return toReturn;
				}
			}
		}
		if ((size + 1)/capacity > factor) {
			expand(capacity * 2);
		}
		int hash = (key.hashCode()) % capacity;
		if (pairs[hash] == null) {
			pairs[hash] = new LinkedList<KVPair>();
			pairs[hash].add(new KVPair(key, value));
		}
		else {
			pairs[hash].add(new KVPair(key,value));
		}
		size++;
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
		int hash = (key.hashCode()) % capacity;
		LinkedList currList = pairs[hash];
		for (int i = 0; i < currList.size(); i++) {
			KVPair currPair = (KVPair) currList.get(i);
			if (currPair.getKey() == key) {
				V toReturn = currPair.getVal();
				currList.remove(i);
				size--;
				return toReturn;
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
		int hash = (key.hashCode()) % capacity;
		LinkedList currList = pairs[hash];
		for (int i = 0; i < currList.size(); i ++) {
			KVPair currPair = (KVPair) currList.get(i);
			if (currPair.getKey() == key) {
				return currPair.getVal();
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
		capacity = newCapacity;
		LinkedList[] tempPairs = new LinkedList[capacity];
		LinkedList[] oldPairs = pairs;
		pairs = tempPairs;
		for (LinkedList list : oldPairs) {
			if (list!= null) {
				for (int i = 0; i < list.size(); i++) {
					KVPair currPair = (KVPair) list.get(i);
					int hash = (currPair.getKey().hashCode()) % capacity;
					this.put(currPair.getKey(), currPair.getVal());
					size--;
				}
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

		int count;
		
		public HashMapIterator() {
			// TODO Complete this!
			count = 0;
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			return count < size;
		}

		@Override
		public K next() {
			// TODO Complete this!
			K toReturn = null;
			int countCopy = count;
			for (LinkedList list : pairs) {
				if (list != null) {
					if (countCopy < list.size()) {
						for (int i = 0; i < list.size(); i++) {
							if (countCopy == 0) {
								KVPair currPair = (KVPair) list.get(i);
								toReturn = currPair.getKey();
							}
							countCopy--;
						}
					}
					else {
						countCopy -= list.size();
					}
				}
			}
			
			count++;
			return toReturn;
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
		
		//public int index;
		
		public KVPair(K k, V v) {
			key = k;
			value = v;
		}
		
		public K getKey() {
			return key;
		}
		
		public V getVal() {
			return value;
		}
		
		public void setVal(V v) {
			value = v;
		}
	}

}
