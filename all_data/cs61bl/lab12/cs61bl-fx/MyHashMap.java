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
	private double loadFactor;
	private LinkedList<KVPair>[] array;
	// TODO You may declare new instance variables here
	
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
		this.loadFactor = loadFactor;
		array = new LinkedList[initialCapacity];
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
		LinkedList<KVPair> position = array[key.hashCode()%capacity];
		if (position != null) {
			for (int i = 0; i < position.size(); i++) {
				if (position.get(i).getK().equals(key)) {
					return true;
				}
			} return false;
		} return false;
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		for (int i = 0; i < capacity; i++) {
			LinkedList<KVPair> position = array[i];
			if (position != null) {
				for (int j = 0; j < position.size(); j++) {
					if (position.get(j).getV().equals(value)) {
						return true;
					}
				}
			} 
		} return false;
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
	public V put(K key, V value) throws IllegalArgumentException {
		// TODO Complete this!
		if (key == null) {
			throw new IllegalArgumentException("Null cannot be a key."); }
		
		KVPair hashbrown = new KVPair(key, value);
		int index = key.hashCode() % capacity;
		
		if (this.containsKey(key)) {
			int k = 0;
			for (int i = 0; i<array[index].size(); i++) {
				if (array[index].get(i).getK().equals(key)) {
					k = i;
				}
			} V replacedValue = array[index].get(k).getV();
			array[index].get(k).changeV(value);
			return replacedValue;
		
		} else {
			this.size++;
			if ((((double)this.size)/this.capacity) > this.loadFactor) {
				this.expand(this.capacity*2);
			} 
			int newindex = key.hashCode() % capacity;
			if (array[newindex] == null) {
				array[newindex] = new LinkedList<KVPair>();
				array[newindex].add(hashbrown);
				return null;
				
			} else {
				array[newindex].add(hashbrown);
				return hashbrown.getV();
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
		KVPair save = null;
		if (this.containsKey(key)) {
			LinkedList<KVPair> position = array[key.hashCode()%capacity];
			for (int i = 0; i < position.size(); i++) {
				if (position.get(i).getK().equals(key)) {
					save = position.remove(i);
				}
			} size --;
			return save.getV();
		} return null;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		// TODO Complete this!
		int index = key.hashCode() % capacity;
		if (array[index] == null) {
			return null;
		} else {
			for (int i = 0; i < array[index].size(); i++) {
				if (array[index].get(i).getK().equals(key)) {
					return array[index].get(i).getV(); 
				}
			} return null;
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
		MyHashMap<K,V> newhash = new MyHashMap(newCapacity, this.loadFactor);
		for (int i = 0; i < this.capacity; i ++) {
			//LinkedList<KVPair> position = array[i];
			if (array[i] != null) {
				for (int j = 0; j < array[i].size(); j++) {
					newhash.put(array[i].get(j).getK(), array[i].get(j).getV());
				}
			}
		} this.array = newhash.array;
		this.capacity = newhash.capacity();
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
			// TODO Complete this!
			arrayIndex = 0;
			linkIndex = 0;
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			if (array[arrayIndex] != null && linkIndex < array[arrayIndex].size()){
				return true;
			}
			for (int i = arrayIndex + 1; i < capacity; i++) {
				if (array[i] != null){
					return true;
				}
			} return false; 
		}

		@Override
		public K next() {
			K key = null;
			while (array[arrayIndex] == null) {
				arrayIndex++;
			}
			if (linkIndex < array[arrayIndex].size()) {
				key = array[arrayIndex].get(linkIndex).getK();
				linkIndex++;
				return key; }
			else {
				arrayIndex++;
				linkIndex = 0;
				return next();
			}
		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair {
			
		private K key;
		private V value;
			
		public KVPair(K key, V value) {
			this.key = key;
			this.value = value;
		}
			
		public K getK() {
			return this.key;
		}
			
		public V getV() {
			return this.value;
		}
		
		public void changeV(V newvalue) {
			this.value = newvalue;
		}
		
	}

}
