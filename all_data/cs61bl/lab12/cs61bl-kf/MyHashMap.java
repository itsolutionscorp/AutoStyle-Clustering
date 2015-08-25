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
	private LinkedList<KVPair>[] myList;
	
	private double loadFactor = DEFAULT_LOAD_FACTOR;
	
	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		this.capacity = DEFAULT_CAPACITY;
		this.size = 0;
		this.myList = new LinkedList[this.capacity];
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		this.capacity = initialCapacity;
		this.size = 0;
		this.myList = new LinkedList[this.capacity];
		
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
		this.capacity = initialCapacity;
		this.size = 0;
		this.myList = new LinkedList[this.capacity];
		this.loadFactor = loadFactor;
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
		int myCode = key.hashCode() % this.capacity;
		if (myList[myCode] == null) {
			return false;
		} else {
			for (int i = 0; i < myList[myCode].size(); i++) {
				if (key.equals(myList[myCode].get(i).getKey())) {
					return true;
				}
			}
			return false;
		}
		
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		// TODO Complete this!
		for (int i = 0; i < this.capacity; i++) {
			if(myList[i] == null) {
				continue;
			} else {
				for (int j = 0; j < myList[i].size(); j++) {
					if (myList[i].get(j).getValue() == value) {
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
		if (((double) (this.size() + 1)) / ((double) capacity) >= 0.7) {
			this.expand(this.capacity * 2);
		}
		int myCode = key.hashCode() % this.capacity();
		if (myList[myCode] == null) {
			myList[myCode] = new LinkedList<KVPair>();
			myList[myCode].add(new KVPair(key, value));
			this.size ++;
			return null;
		} else {
			for (int i = 0; i < myList[myCode].size(); i++) { // Loops through Linked List at hashCode index in array
				if (key.equals(myList[myCode].get(i).getKey())) { // Checks to see if any key in linked list equals inputed key
					V temp = myList[myCode].get(i).getValue(); // getting the replaced value so it can be returned
					myList[myCode].get(i).setValue(value); // setting the value to be the new value
					this.size ++;
					return temp;
				}
			}
			this.size ++;
			myList[myCode].add(new KVPair(key, value));
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
		// TODO Complete this!
		int myCode = key.hashCode() % this.capacity;
		if (!this.containsKey(key)) {
			return null;
		} else {
			for(int i = 0; i < myList[myCode].size(); i++) {
				if (myList[myCode].get(i).getKey() == key) {
					V val = myList[myCode].get(i).getValue();
					myList[myCode].remove(i);
					this.size --;
					return val;
				}
			}
		}
		return null; //just so compiles
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		// TODO Complete this!
		int myCode = key.hashCode() % this.capacity();
		if (!this.containsKey(key)) {
			return null;
		} else {
			for (int i = 0; i < myList[myCode].size(); i++) {
				if (key.equals(myList[myCode].get(i).getKey())) {
					return myList[myCode].get(i).getValue();
				}
			}
			return null; // only so compiles; the for loop will always get a valid key
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
		LinkedList<KVPair>[] newList = new LinkedList[this.capacity * 2];
		for (int i = 0; i < this.capacity; i++) {
			if (myList[i] == null) {
				continue;
			} else {
				//iterate through the linked list and create a new linked list in newList
				for (int j = 0; j < myList[i].size(); j++) {
					int myCode = myList[i].get(j).getKey().hashCode() % (this.capacity * 2);
					if (newList[myCode] == null) { 
						newList[myCode] = new LinkedList<KVPair>(); 
						newList[myCode].add(myList[i].get(j)); //adds old KVPair to new linked list
					} else {
						newList[myCode].add(myList[i].get(j));
					}
				}
			}
		} 
		this.capacity *= 2;
		myList = newList;
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
		private int count;
		
		public HashMapIterator() {
			// TODO Complete this!
			arrayIndex = 0;
			linkedIndex = -1;
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
			count ++;
			while (myList[arrayIndex] == null) {
				arrayIndex ++;
			}
			linkedIndex++;
			K k = myList[arrayIndex].get(linkedIndex).getKey();
			if (linkedIndex == myList[arrayIndex].size() - 1) {
				linkedIndex = -1;
				arrayIndex ++;
			}
			count++;
			return k;
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
		
		public KVPair(Object key, Object value) {
			this.key = (K) key;
			this.value = (V) value;			
		}
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
		
		public void setValue(V value) {
			this.value = value;
		}
	}

}
