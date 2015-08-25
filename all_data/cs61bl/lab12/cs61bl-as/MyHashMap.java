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
	private LinkedList<KVPair>[] hashMapArray;
 
	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		loadFactor = DEFAULT_LOAD_FACTOR;
		capacity = DEFAULT_CAPACITY;
		hashMapArray = new LinkedList[capacity];
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		loadFactor = DEFAULT_LOAD_FACTOR;
		capacity = initialCapacity;
		hashMapArray = new LinkedList[capacity];
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
		this.loadFactor = loadFactor;
		capacity = initialCapacity;
		hashMapArray = new LinkedList[capacity];
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
		if (get(key) == null) {
			return false;
		}
		return true;
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		for (LinkedList<KVPair> i : hashMapArray) {
			if (i!= null) {
				for (int k = 0; k<i.size(); k++) {
					if (i.get(k).getValue() == value) {
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
		int place = hash(key.hashCode());
		if (get(key) != null) {
			LinkedList<KVPair> temp = hashMapArray[place];
			for (KVPair i : temp) {
				if (i.getKey().equals(key)) {
					V oldKey = i.getValue();
					i.setValue(value);
					return oldKey;
				}
			}
		} else {
			if ((capacity * loadFactor) <= (size)) {
				expand(capacity + 10);
				return put(key, value);
			}  else if (hashMapArray[place] == null) {
				hashMapArray[place] = new LinkedList<KVPair>();
			}
			hashMapArray[place].add(new KVPair(key, value));
			size++;
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
		int place = hash(key.hashCode());
		if (hashMapArray[place] != null) {
			LinkedList<KVPair> temp = hashMapArray[place];
			for (KVPair i : temp) {
				if (i.getKey().equals(key)) {
					V oldKey = i.getValue();
					temp.remove(i);
					size--;
					return oldKey;
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
		int place = hash(key.hashCode());
		if (hashMapArray[place] != null) {
			LinkedList<KVPair> temp = hashMapArray[place];
			for (KVPair i : temp) {
				if (i.getKey().equals(key)) {
					return i.getValue();
				}
			}
		}
		return null;
	}
	
	public int hash(int hashCode) {
		if (hashCode < 0) {
			return hash(-hashCode);
		} else if ((hashCode % 10) >= hashMapArray.length) {
			return hash((hashCode % 10) / 2);
		} else {
			return hashCode % 10;
		}
	}

	/**
	 * Expands the underlying array to the given capacity.
	 * 
	 * ALSO, rehashes all of the items into this new array. Items will likely
	 * end up in different buckets after the expansion.
	 */
	private void expand(int newCapacity) {
		capacity = newCapacity;
		size = 0;
		LinkedList<KVPair>[] hashMapNew = hashMapArray;
		hashMapArray = new LinkedList[newCapacity];
		for (LinkedList<KVPair> i : hashMapNew) {
			if (i != null) {
				for (int j = 0; j < i.size(); j++) {
					K putKey = i.get(j).getKey();
					V putValue = i.get(j).getValue();
					put(putKey, putValue);
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
		
		private int arrayIndex;
		private int linkedIndex;
		private LinkedList<KVPair> myList;

		public HashMapIterator() {
			arrayIndex = 0;
			linkedIndex = 0;
			myList = hashMapArray[0];
		}

		@Override
		public boolean hasNext() {
			return arrayIndex < hashMapArray.length;
		}

		@Override
		public K next() {
			if (myList != null) {
				if (linkedIndex == (myList.size())) {
					arrayIndex++;
					myList = hashMapArray[arrayIndex];
					linkedIndex = 0;
					while (myList == null && arrayIndex<myList.size()) {
						arrayIndex++;
						myList = hashMapArray[arrayIndex];
					}
				}
				linkedIndex++;
				return myList.get(linkedIndex-1).getKey();
		}
			return null;
		}

		@Override
		public void remove() {
			
			
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
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
		
		public void setKey(K newKey) {
			key = newKey;
		}
		
		public void setValue(V newValue) {
			value = newValue;
		}
	}
	
	public static void main(String[] args) {
		MyHashMap<String, Integer> myMap = new MyHashMap<String, Integer>(5);
		myMap.put("Hello", 1);
		myMap.put("Hi", 2);
		myMap.put("Bonjour", 3);
		myMap.put("Aloha", 4);
		myMap.remove("Bonjour");
		System.out.print(myMap.get("Hi")); //should print 4
		System.out.print(myMap.remove("Hi")); //should print 4
	}

}
