import java.util.Iterator;
import java.util.LinkedList;
import java.lang.Math;

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
	private Object[] buckets;
	private double loadfactor;
	
	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		buckets = new Object[DEFAULT_CAPACITY];
		capacity = DEFAULT_CAPACITY;
		size = 0;
		loadfactor = DEFAULT_LOAD_FACTOR;
		
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		buckets = new Object[initialCapacity];
		capacity = initialCapacity;
		size = 0;
		loadfactor = DEFAULT_LOAD_FACTOR;
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
		buckets = new Object[initialCapacity];
		capacity = initialCapacity;
		size = 0;
		loadfactor = loadFactor;
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
		int index = Math.abs(key.hashCode()) % capacity;
		LinkedList<KVPair> current = (LinkedList<KVPair>) buckets[index];
		try {
			Iterator<KVPair> iter = current.listIterator();
			while (iter.hasNext()) {
				KVPair holder = iter.next();
				if (holder.getK().equals(key)) {
					return true;
				}
			}
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		for (int i = 0; i < capacity; i++) {
			LinkedList<KVPair> current = (LinkedList<KVPair>) buckets[i];
			try {
				Iterator<KVPair> iter = current.listIterator(0);
				while (iter.hasNext()) {
					KVPair holder = iter.next();
					if (holder.getV().equals(value)) {
						return true;
					}
				}
			} catch (NullPointerException e) {
				continue;
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
		if ((double)(size+1)/capacity < loadfactor){
			int index = Math.abs(key.hashCode()) % capacity;
			if (containsKey(key) == false) size += 1;
			return putHelper(buckets, index, new KVPair(key, value));
		} else {
			expand(capacity*2);
			return put(key, value);
		}
		
		
	}
	
	private V putHelper(Object[] arr, int index, KVPair item) {
		if (arr[index] == null) {
			arr[index] = new LinkedList<KVPair>();
		}
		LinkedList<KVPair> current = (LinkedList<KVPair>) arr[index];
		if (containsKey(item.getK())) {
			V previous = get(item.getK());
			Iterator<KVPair> iter = current.listIterator(0);
			while (iter.hasNext()) {
				KVPair holder = iter.next();
				if (holder.getK().equals(item.getK())) {
					holder.setV(item.getV());
					break;
				}
			}
			return previous;
		} else {
			current.add(item);
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
		if (containsKey(key)) {
			int index = Math.abs(key.hashCode()) % capacity;
			LinkedList<KVPair> current = (LinkedList<KVPair>) buckets[index];
			Iterator<KVPair> iter = current.listIterator();
			while (iter.hasNext()) {
				KVPair holder = iter.next();
				if (holder.getK().equals(key)) {
					V toReturn = holder.getV();
					iter.remove();
					size -= 1;
					return toReturn;
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
		int index = Math.abs(key.hashCode()) % capacity;
		LinkedList<KVPair> current = (LinkedList<KVPair>) buckets[index];
		try {
			Iterator<KVPair> iter = current.listIterator();
			while (iter.hasNext()) {
				KVPair holder = iter.next();
				if (holder.getK().equals(key)) {
					return holder.getV();
				}
			}
			return null;
		} catch (NullPointerException e) {
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
		Object[] newArr = new Object[newCapacity];
		Iterator<K> iter = iterator();
		while (iter.hasNext()) {
			K holder = iter.next();
			KVPair item = new KVPair(holder, get(holder));
			int index = (Math.abs(holder.hashCode())) % newCapacity;
			if (newArr[index] == null) {
				newArr[index] = new LinkedList<KVPair>();
			}
			((LinkedList<KVPair>) newArr[index]).add(item);
		}
		capacity = newCapacity;
		buckets = newArr;
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
		Iterator<KVPair> currIter;
		
		public HashMapIterator() {
			index = 0;
			while (buckets[index] == null && index < capacity) {
				index++;
			}
			if (index > capacity) {
				throw new IllegalStateException();
			}
			currIter = ((LinkedList<KVPair>) buckets[index]).listIterator(0);
		}

		@Override
		public boolean hasNext() {
			for (int i = index + 1; i < capacity; i++) {
				if (buckets[i] != null) {
					return true;
				}
			}
			return false || currIter.hasNext();
		}

		@Override
		public K next() throws IndexOutOfBoundsException {
			if (hasNext() == false) {
				throw new IndexOutOfBoundsException();
			} else {
				if (currIter.hasNext() == false) {
					index += 1;
					while (buckets[index] == null && index < capacity) {
						index += 1;
					}
					currIter = ((LinkedList<KVPair>) buckets[index]).listIterator();
				}
				return currIter.next().getK();

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
		public KVPair(K key2, V value2) {
			this.key = key2;
			this.value = value2;
		}
		public K getK() {
			return key;
		}
		public V getV() {
			return value;
		}
		
		public void setV(V value2) {
			value = value2;
		}
	}

}
