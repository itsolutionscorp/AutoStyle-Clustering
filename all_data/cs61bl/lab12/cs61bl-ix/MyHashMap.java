
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
	private double loadFactor;
	
	// TODO You may declare new instance variables here
	private LinkedList<KVPair>[] content;

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		this(initialCapacity, DEFAULT_LOAD_FACTOR);
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
		size = 0;
		this.loadFactor = loadFactor;
		content = new LinkedList[capacity];
		for (int i=0;i<content.length;i++) {
			content[i] = new LinkedList<KVPair>();
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
		return content[key.hashCode() % capacity].size() != 0;
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		for (int i=0;i<content.length;i++) {
			for (int j=0;j<content[i].size(); j++) {
				if (value.equals(content[i].get(j).value))
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
		int index = Math.abs(key.hashCode() % capacity);
		for (int i = 0; i < content[index].size(); i++) {
			if (content[index].get(i).key.equals(key)) {
				V toReturn = content[index].get(i).value;
				content[index].get(i).value = value;
				return toReturn;
			}
		}
		content[index].add(new KVPair(key, value));
		size++;
		
		if (size / capacity > 0.7) {
			expand(capacity + DEFAULT_CAPACITY);
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
		int index = Math.abs(key.hashCode() % capacity);
		for (int i = 0; i < content[index].size(); i++) {
			if (content[index].get(i).key.equals(key)) {
				size--;
				return content[index].remove(i).value;
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
		int index = Math.abs(key.hashCode() % capacity);
		for (int i = 0; i < content[index].size(); i++) {
			if (content[index].get(i).key.equals(key)) {
				return content[index].get(i).value;
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
		LinkedList<KVPair>[] oldContent = content;
		capacity = newCapacity;
		content = new LinkedList[newCapacity];
		size = 0;
		
		//Reinitialize
		for (int i=0;i<content.length;i++) {
			content[i] = new LinkedList<KVPair>();
		}
		
		//Rehash
		for (LinkedList<KVPair> pairList : oldContent) {
			for (KVPair pair : pairList) {
				put(pair.key, pair.value);
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
		int currentIndex;
		int currentPosition;
		
		public HashMapIterator() {
			currentIndex = 0;
			currentPosition = 0;
			while (currentIndex < capacity && content[currentIndex].size() == 0) {
				currentIndex++;
			}
		}

		@Override
		public boolean hasNext() {
			if (currentIndex >= capacity) {
				return false;
			}
			if (currentPosition >= content[currentIndex].size()) {
				return false;
			}
			return true;
		}

		@Override
		public K next() {
			K result = content[currentIndex].get(currentPosition).key;
			currentPosition++;
			if (currentPosition >= content[currentIndex].size()) {
				currentPosition = 0;
				currentIndex++;
			}
			
			while (currentIndex < content.length && content[currentIndex].size() == 0) {
				currentIndex++;
			}
			
			return result;
		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair {
		private K key;
		private V value;
		
		KVPair(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

}
