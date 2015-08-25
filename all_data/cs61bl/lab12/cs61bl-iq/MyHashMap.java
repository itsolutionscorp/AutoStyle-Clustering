import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;

/* YOU ARE NOT allowed to use any library function related to hash maps
 if you feel the need you may use the ArrayList or LinkedList class */

public class MyHashMap<K, V> implements Iterable<K> {
	/* Default size of the map if not set in constructor */
	private static final int DEFAULT_CAPACITY = 30;

	/* Default load factor if not set in constructor */
	private static final double DEFAULT_LOAD_FACTOR = 0.7;

	private int capacity; // the number of buckets in the map
	private int size; // the number of items that have been put into the map
	
	private double loadFactor;
	private LinkedList<ArrayList>[] arrayOfKeyValues;

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		capacity = DEFAULT_CAPACITY;
		loadFactor = DEFAULT_LOAD_FACTOR;
		size = 0;
		arrayOfKeyValues = new LinkedList[capacity];
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		capacity = initialCapacity;
		loadFactor = DEFAULT_LOAD_FACTOR;
		size = 0;
		arrayOfKeyValues = new LinkedList[capacity];
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
		size = 0;
		arrayOfKeyValues = new LinkedList[capacity];
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
		int index = key.hashCode() % capacity;
		if (arrayOfKeyValues[index] != null) {
			LinkedList<ArrayList> list = arrayOfKeyValues[index];
			int size = arrayOfKeyValues[index].size();
			int counter = 0;
			while (counter < size) {
				if (list.get(counter).get(0) == key) {
					return true;
				} else {
					counter++;
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
		HashMapIterator iterator = new HashMapIterator();
		while (iterator.hasNext()) {
			if (((LinkedList<ArrayList>) iterator.next()).get(1) == value) {
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
		int index = key.hashCode() % capacity;
		if (this.containsKey(key)) {
			LinkedList<ArrayList> list = arrayOfKeyValues[index];
			int size = arrayOfKeyValues[index].size();
			int counter = 0;
			while (!list.get(counter).get(0).equals(key)) {
				counter++;
			}
			V currentValue = (V) list.get(counter).get(1);
			list.get(counter).set(1, value);
			return currentValue;
		} else {
			ArrayList newNode = new ArrayList();
			newNode.add(key);
			newNode.add(value);
			arrayOfKeyValues[index].add(newNode);
			size++;
			if (size / capacity > loadFactor) {
				this.expand(capacity * 2);
			}
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
		int index = key.hashCode() % capacity;
		LinkedList<ArrayList> list = arrayOfKeyValues[index];
		int size = arrayOfKeyValues[index].size();
		int counter = 0;
		while (counter < size) {
			if (list.get(counter).get(0) == key) {
				V value = (V) list.get(counter).get(1);
				list.remove(counter);
				return value;
			} else {
				counter++;
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
		int index = key.hashCode() % capacity;
		LinkedList<ArrayList> list = arrayOfKeyValues[index];
		int size = arrayOfKeyValues[index].size();
		int counter = 0;
		while (counter < size) {
			if (list.get(counter).get(0) == key) {
				return (V) list.get(counter).get(1);
			} else {
				counter++;
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
		LinkedList[] copy = arrayOfKeyValues;
		arrayOfKeyValues = new LinkedList[newCapacity];
		HashMapIterator iterator = new HashMapIterator();
		while (iterator.hasNext()) {
			ArrayList pair = (ArrayList) iterator.next();
			V value = (V) pair.get(1);
			K key = (K) pair.get(0);
			this.put(key, value);
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

		int counter; //count of the amount of key value pairs we have gone through
		int bucketCounter; //count of what bucket we are on
		int sizeThisList; //size of the current linked list we are iterating through
		int counterThisList; //where in the current linked list we are
		boolean hasItems;
		
		public HashMapIterator() {
			counter = 0;
			bucketCounter = 0;
			counterThisList = 0;
			while (arrayOfKeyValues[bucketCounter] == null && bucketCounter < capacity) {
				bucketCounter++;
			}
			if (bucketCounter == capacity) {
				hasItems = false;
				sizeThisList = 0;
			} else {
				hasItems = true;
				sizeThisList = arrayOfKeyValues[bucketCounter].size();
			}
		}

		@Override
		public boolean hasNext() {
			if (counter < size && hasItems) {
				return true;
			}
			return false;
		}

		@Override
		public K next() {
			if (hasNext()) {
				if (counterThisList < sizeThisList) {
					counter++;
					counterThisList++;
					return (K) arrayOfKeyValues[bucketCounter].get(counterThisList - 1);
				} else {
					bucketCounter++;
					counter++;
					while (arrayOfKeyValues[bucketCounter] == null) {
						bucketCounter++;
					}
					sizeThisList = arrayOfKeyValues[bucketCounter].size();
					counterThisList = 1;
					return (K) arrayOfKeyValues[bucketCounter].get(counterThisList - 1);
				}
			}
			return null;
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
	}

}
