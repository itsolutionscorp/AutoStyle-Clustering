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
	private LinkedList<KVPair>[] myList;
	private double myLoad;

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		this(initialCapacity, DEFAULT_LOAD_FACTOR);
	}

	/**
	 * Constructs an empty map with the given initial capacity and the given load
	 * factor.
	 * 
	 * @param loadFactor
	 *            A fraction greater than 0 and less than 1. Once the size /
	 *            capacity exceeds this number, the map's underlying array
	 *            should expand.
	 */
	public MyHashMap(int initialCapacity, double loadFactor) {
		capacity = initialCapacity;
		myList = new LinkedList[capacity];
		for (int i = 0; i < capacity; i++) {
			myList[i] = new LinkedList();
		}
		myLoad = loadFactor;
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
		int index = key.hashCode() % capacity;
		for (KVPair i: myList[index]) {
			if (i.key.equals(key)) {
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
		for (int i = 0; i < myList.length; i++) {
			if (myList[i].contains(value)) {
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
		// TODO Complete this!
		int index = key.hashCode() % capacity;
		for (KVPair i: myList[index]) {
			if (i.getK().equals(key)) {
				V prev = i.getV();
				i.value = value;
				return prev;
			}
		}
		myList[index].add(new KVPair(key, value));
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
		int index = key.hashCode() % capacity;
		for (KVPair i: myList[index]) {
			if (i.getK().equals(key)) {
				V temp = i.getV();
				myList[index].remove(i);
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
		int index = key.hashCode() % capacity;
		for (KVPair i: myList[index]) {
			if (i.getK().equals(key)) {
				return i.getV();
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
		MyHashMap temp = new MyHashMap(newCapacity);
		for (int i = 0; i < this.capacity; i++) {
			temp.myList[i] = new LinkedList();
			for (KVPair k : this.myList[i]) {
				if (this.myList[i]!=null) {
					int index = k.hashCode() % newCapacity;
					if (index == i) {
						temp.put(k.getK(), k.getV());
					}			
				}	
			}
		}
		this.myList = temp.myList;
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
		private int myLink;
		private int myLinkpos;
		private int myCount;
		private int myBucket;

		public HashMapIterator() {
			// TODO Complete this!
			myLink = 0;
			myLinkpos = 0;
			myCount = 0;
			myBucket = 0;
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			int myLinkpos = 0;
			if (myBucket < capacity) {
				if (myList[myBucket] != null) {
					for (KVPair k : myList[myBucket]) {
						myLinkpos++;
					}
					if (myLink < myLinkpos) {
						return true;
					}
				}
			}
			return (myCount < size);
		}

		@Override
		public K next() {
			// TODO Complete this!
			K returnval = myList[myBucket].get(myLink).getK();
			if (myLink == myLinkpos) {
				myBucket ++;
				myLink = 0;
				if (myList[myBucket] == null) {
					myBucket++;
				}				
			}
			myLink++;
			myCount ++;
			return returnval;
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
		public KVPair(K k, V v) {
			key = k;
			value = v;
		}
		
		public K getK() {
			return key;
		}
		
		public V getV() {
			return value;
		}
	}

}
