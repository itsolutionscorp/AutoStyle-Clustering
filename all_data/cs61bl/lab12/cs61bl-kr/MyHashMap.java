import java.util.Iterator;
import java.util.ArrayList;

/* YOU ARE NOT allowed to use any library function related to hash maps
 if you feel the need you may use the ArrayList or LinkedList class */

public class MyHashMap<K, V> implements Iterable<K> {
	/* Default size of the map if not set in constructor */
	private static final int DEFAULT_CAPACITY = 30;
	private static final double DEFAULT_LOAD_FACTOR = 0.7;
	private int capacity; // the number of buckets in the map
	private int size; // the number of items that have been put into the map
	private double Factor;
	private int count;
	private ArrayList<KVPair> list;
	private ArrayList<KVPair> expandedlist;
	// TODO You may declare new instance variables here
	public class KVPair {
		private K key;
		private V value;
//		KVPair next;
		public KVPair (K key, V value) { //KVPair next
			this.key = key;
			this.value = value;
//			this.next = next;
			}
		public V getV(){
			return value;
		}
		public K getK() {
			return key;
		}
		public void insertV( V input) {
			this.value = input;
		}
		public void insertK( K newkey) {
			this.key = newkey;
		}
		public void removeK() {
			this.key = null;
		}
		public void removeV() {
			this.value = null;
		}
		
	}
	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		list = 	new ArrayList<KVPair>(DEFAULT_CAPACITY);
		Factor = DEFAULT_LOAD_FACTOR;
		capacity = DEFAULT_CAPACITY;
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		if  (initialCapacity < 1) {
			throw new IllegalArgumentException("Incorrect capacity or loadfactor" );
			}
		list = 	new ArrayList<KVPair>(initialCapacity);
		capacity = initialCapacity;
		Factor = DEFAULT_LOAD_FACTOR;
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
		if (loadFactor <= 0 || initialCapacity < 1) {
			throw new IllegalArgumentException("Incorrect capacity or loadfactor" );
			}
		list = new ArrayList<KVPair> (initialCapacity);
		capacity = initialCapacity;
		Factor = loadFactor;
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
			if (list == null) {
			return false;
			}
		
			if (list.get(key.hashCode()).getK().equals(key)) {
				return true;
			}
			else {
				return false;
			}
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		if (list == null) {
			return false;
		}
		for (int i = 0; i < capacity; i++) {
			if (list.get(i).getV().equals(value)) {
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
//	try (objectInputStream ois = new ObjectInputStream filename)
	public V put(K key, V value) {
		this.size++;
		if (list.size()/capacity > (int) Factor) {
			this.expand(capacity*2);
		}
		int newhash = key.hashCode();
		V temp = list.get(newhash).getV(); 
		list.set(newhash, new KVPair(key, value));
		return temp;
		
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		V x = null;
		if (list.get(key.hashCode()).getK() == null) {
			return null;
		}
		if (list.get(key.hashCode()).getK() == key) {
			return list.get(key.hashCode()).getV() ;
		}
		if (list.get(key.hashCode()).getK() != key) {
			K temp = key;
			key = null;
			x = list.get(temp.hashCode()).getV();
		}
		return x;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		return list.get(key.hashCode()).getV();
	}

	/**
	 * Expands the underlying array to the given capacity.
	 * 
	 * ALSO, rehashes all of the items into this new array. Items will likely
	 * end up in different buckets after the expansion.
	 */
	private void expand(int newCapacity) {
		expandedlist = new ArrayList<KVPair>(newCapacity);
		for (int i = 0; i < capacity; i++) {
			if (list.get(i).getK() != null) {
				expandedlist.set(list.get(i).getK().hashCode(),new KVPair(list.get(i).getK(),list.get(i).getV()));
			}
		}
		capacity = newCapacity;
		this.list = expandedlist;
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
		int next;
		
		
		public HashMapIterator() {
			this.next = next;
			this.index = index;
			
		}

		@Override
		public boolean hasNext() {
			return next < size;
		}

		@Override
		public K next() {
			if (next > size()) {
				throw new IllegalArgumentException("There are no other keys");
			}
			for (int i = 0; i < capacity; i++) {
				if(list.get(i).getK() != null) {
					next ++;
					index = i;
					return list.get(i).getK();
				}
			}
			return list.get(index).getK();
		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	

}
