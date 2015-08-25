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
	private double factor;
	private ArrayList<LinkedList<KVPair>> myList;
	
	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		capacity = DEFAULT_CAPACITY;
		factor = DEFAULT_LOAD_FACTOR;
		size = 0;
		myList = new ArrayList<LinkedList<KVPair>>();
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		this();
		capacity = initialCapacity;
		for (int i = 0; i < capacity; i++) {
			myList.add(i, new LinkedList<KVPair>());
		}
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
		this();
		capacity = initialCapacity;
		factor = loadFactor;
		for (int i = 0; i < capacity; i++) {
			myList.add(i, new LinkedList<KVPair>());
		}
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
		return capacity;
	}

	/**
	 * Returns whether the map contains the given key. Runs in O(1) time on
	 * average, relative to the size of the map.
	 */
	public boolean containsKey(K key) {
		int index = Math.abs(key.hashCode() % capacity);
		for (KVPair kv : myList.get(index)) {
			if (kv.key == key) {
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
		for (int i = 0; i < myList.size(); i++) {
			for (int j = 0; j < myList.get(i).size(); j++) {
				if (myList.get(i).get(j).value.equals(value)) {
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
		KVPair entry = new KVPair(key, value);
		int index = Math.abs(key.hashCode() % capacity);
		LinkedList<KVPair> l = myList.get(index);
		V v = remove(key);
		l.add(entry);
		if (++size/capacity > factor) {
			expand(capacity*2);
		}
		return v;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		if (containsKey(key)) {
			int index = Math.abs(key.hashCode() % capacity);
			LinkedList<KVPair>  l = myList.get(index);
			KVPair removed = null;
			for (int i = 0; i < l.size(); i++) {
				if (l.get(i).key.equals(key)) {
					removed = l.remove(i);
				}
			}
			return removed.value;
		} else return null;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		if (containsKey(key)) {
			int index = Math.abs(key.hashCode() % capacity);
			LinkedList<KVPair> l = myList.get(index);
			for (int i = 0; i < l.size(); i++) {
				if (l.get(i).key.equals(key)) {
					return l.get(i).value;
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
		ArrayList<LinkedList<KVPair>> temp = myList;
		myList = new ArrayList<LinkedList<KVPair>>();
		capacity = newCapacity;
		size = 0;
		for (LinkedList<KVPair> l : temp) {
			for (KVPair kv : l) {
				put(kv.key, kv.value);
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
		
		private int arrayIndex;
		private int linkIndex;

		public HashMapIterator() {
			arrayIndex = 0;
			linkIndex = 0;
		}
		
		@Override
		public boolean hasNext() {
			return arrayIndex < myList.size();
		}
		
		@Override
		public K next() {
			K temp = myList.get(arrayIndex).get(linkIndex).key;
			if (linkIndex+1 >= myList.get(arrayIndex).size()) {
				arrayIndex++;
				while (arrayIndex < myList.size() && myList.get(arrayIndex).isEmpty()) {
					arrayIndex++;
				}
				linkIndex = 0;
			} else {
				linkIndex++;
			}
			return temp;
			
		}
		
		public void remove() {
			throw new UnsupportedOperationException("don't try to remove");
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
		private KVPair(K k, V v) {
			key = k;
			value = v;
		}
	}
	
	public static void main(String[] args) {
		System.out.println("out of".hashCode() % 10);
	}
	
}
