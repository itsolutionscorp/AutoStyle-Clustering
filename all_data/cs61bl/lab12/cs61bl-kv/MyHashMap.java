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
	private double load_factor;
	private ArrayList<LinkedList> buckets;

	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		this.capacity = DEFAULT_CAPACITY;
		this.size = 0;
		this.load_factor = DEFAULT_LOAD_FACTOR;
		this.buckets = new ArrayList<LinkedList>(this.capacity);
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		this.capacity = initialCapacity;
		this.size = 0;
		this.load_factor = DEFAULT_LOAD_FACTOR;
		this.buckets = new ArrayList<LinkedList>(this.capacity);
	}

	/**
	 * Constructs an empty map with the given initial capacity and the given
	 * load factor.
	 * 
	 * @param loadFactor
	 *            A fraction greater than 0 and less than 1. Once the size /
	 *            capacity exceeds this number, the map's underlying array
	 *            should expand.
	 */
	public MyHashMap(int initialCapacity, double loadFactor) {
		this.capacity = initialCapacity;
		this.size = 0;
		this.load_factor = loadFactor;
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
		// YOUR CODE HERE
		LinkedList<KVPair> bin = buckets.get(key.hashCode()%this.capacity);
		for (int k = 0; k < bin.size(); k++) {
			if (((KVPair) bin.get(k)).key == key) {
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
		// YOUR CODE HERE
		for (int i = 0; i < buckets.size(); i++) {
			LinkedList<KVPair> bin = buckets.get(i);
			for (int k = 0; k < bin.size(); k++) {
				if (((KVPair) bin.get(k)).value == value) {
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
		LinkedList<KVPair> bin = buckets.get(key.hashCode()%this.capacity);
		for (int k = 0; k < bin.size(); k++) {
			if (((KVPair) bin.get(k)).key == key) {
				V store = ((KVPair) bin.get(k)).value;
				((KVPair) bin.get(k)).value = value;
				return store;
			}
		}
		bin.add(new KVPair(key, value));
		this.size++;
		if (this.size()/this.capacity() > this.load_factor) {
			this.expand((int) (this.capacity*1.5)); 
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
		this.size--;
		LinkedList<KVPair> bin = buckets.get(key.hashCode()%this.capacity);
		for (int k = 0; k < bin.size(); k++) {
			if (((KVPair) bin.get(k)).key == key) {
				V store = ((KVPair) bin.get(k)).value;
				bin.remove(k);
				return store;
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
		LinkedList<KVPair> bin = buckets.get(key.hashCode()%this.capacity);
		for (int k = 0; k < bin.size(); k++) {
			if (((KVPair) bin.get(k)).key == key) {
				return ((KVPair) bin.get(k)).value;
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
//		// TODO Complete this!
		this.capacity = newCapacity;
		
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
		
		KVPair currpair;
		K curr;
		int bin; int num;

		public HashMapIterator() {
			// TODO Complete this!
			this.currpair = (MyHashMap<K, V>.KVPair) buckets.get(bin).get(num);
			this.curr = this.currpair.key;
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			if (num == buckets.get(bin).size() - 1) {
				if (bin == buckets.size() - 1) {
					return false;
				}
				
			}
			return false;
		}

		@Override
		public K next() {
			// TODO Complete this!
			K store = this.curr;
			this.currpair = this.currpair.getNext();
			this.curr = this.currpair.key;
			return store;
		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair {
		private K key;
		private V value;
		private KVPair myNext;

		/*
		 * TODO Fill this out if you need it. Maybe not required depending on
		 * your implementation.
		 */
		public KVPair() {
			this.key = null;
			this.value = null;
			this.myNext = null;
		}

		public MyHashMap<K, V>.KVPair getNext() {
			// TODO Auto-generated method stub
			return null;
		}

		public KVPair(K key, V value) {
			this.key = key;
			this.value = value;
			this.myNext = null;
		}

		public KVPair(K key, V value, KVPair next) {
			this.key = key;
			this.value = value;
			this.myNext = next;

		}

		public void setNext(KVPair thenext) {
			this.myNext = thenext;
		}
		
		public KVPair getNext(KVPair x) {
			return x.myNext;
		}

	}

}
