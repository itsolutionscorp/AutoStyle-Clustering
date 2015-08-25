import java.util.Iterator;
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

	// TODO You may declare new instance variables here
	private double myLoadFactor;
	private Object[] myList;
	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		capacity = DEFAULT_CAPACITY;
		myLoadFactor = DEFAULT_LOAD_FACTOR;
		size = 0;
		myList = new Object[capacity];
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		capacity = initialCapacity;
		myLoadFactor = DEFAULT_LOAD_FACTOR;
		size = 0;
		myList = new Object[capacity];
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
		myLoadFactor = loadFactor;
		size = 0;
		myList = new Object[capacity];
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
	
	//MY METHOD 
	public int length() {
		return myList.length;
	}

	/**
	 * Returns whether the map contains the given key. Runs in O(1) time on
	 * average, relative to the size of the map.
	 */
	public boolean containsKey(K key) {
		// TODO Complete this!
		if (key == null) {
			return false;
		}
		Iterator<K> myIter = new HashMapIterator(key.toString().hashCode()%capacity);
		while (myIter.hasNext()){
			K temp = myIter.next();
			if (key.equals(temp)) {
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
		Iterator<K> myIter = iterator();
		while (myIter.hasNext()){
			V temp = get(myIter.next());
			if (value.equals(temp)) {
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
		V oldVal = remove(key);
		KVPair newVal = new KVPair(key, value); 
		boolean done = false;
		for (int i = key.toString().hashCode()%capacity; i < myList.length && !done; i++) {
			if (myList[i] == null) {
				myList[i] = newVal;	
				done = true;
			}
		}
		for (int i = 0; i < key.toString().hashCode()%capacity && !done; i+=1) {
			if (myList[i] == null) {
				myList[i] = newVal;
				done = true;
			}
		}
		size++;
		if (((double) size())/(double)capacity >= myLoadFactor) {
			capacity = capacity*2;				
			expand(capacity);
		}
		
		return oldVal;  //null if remove(key) returned null
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		V oldVal = get(key);
		if (oldVal != null) {
			int index = indexOf(key, oldVal);
			if (index != -1) {
				myList[index] = null;
				size--;
			}
			//return oldVal;
		}
		return oldVal;
	}
	
	//MY METHOD
	public int indexOf(K key, V values) {
		//int index = -1;
		HashMapIterator myIter = new HashMapIterator(key.toString().hashCode()%capacity);
		while (myIter.hasNext()){
			//index++;
			if (key.equals(myIter.next())) {
				
				return myIter.index;
			}
		}
		return -1;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		// TODO Complete this!
		if (containsKey(key)) {
			//int index = 0;
			HashMapIterator myIter = new HashMapIterator(key.toString().hashCode()%capacity);
			while (myIter.hasNext()){
				K temp = myIter.next();
				if (key.equals(temp)) {
					return myIter.nextValue();
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
		// TODO Complete this!
		Object[] temp = myList;
		myList = new Object[newCapacity];
		size = 0;
		for (int i = 0; i < temp.length; i += 1) {
			if (temp[i] != null) {
				KVPair item = (KVPair) temp[i];
				put(item.key(), (V)item.value());
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
		int hash;
		int count;
		int index;
		public HashMapIterator() {
			// TODO Complete this!
			hash = -1;	//to know where to start
			count = -1; //to know how many items we've retrieved (should be less than size())
			index = -1; //to know where we are in myList array
		}

		public HashMapIterator(int i) { //i is hashCode of key
			hash = i-1;
			count = -1;
			index = i-1;
			//for (int j = 0; j<i && hasNext(); j++) {
			//	next();
			//}
		}
		@Override
		public boolean hasNext() {
			// TODO Complete this!
			if (size() == 0) {
				return false;
			} else if (hash == -1) {
				return (index < myList.length-1);
		
			} else if (index == myList.length-1) {	//if given a key, hash wont be -1
				index = -1;
				return true;
			} else {							//if index restarted
				return (count < hash);		
			}
		}

		@Override
		public K next() {
			// TODO Complete this!
			count ++;
			//index ++;

			KVPair temp = null;
			while (temp == null && hasNext()) {
				index++;
				//if (index == -1) {
				//	index++;
				//}
				temp = (KVPair) myList[index];
				
			}
			if (temp == null) {
				return null;
			}
			return temp.key();
		}
		
		//MY METHOD
		public V nextValue() {
			KVPair temp = (KVPair) myList[index];
			return temp.value();
		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair {
		private K myKey;
		private V myValue;
		/*
		 * TODO Fill this out if you need it. Maybe not required depending on
		 * your implementation.
		 */
		public KVPair(K key, V value) {
			myKey = key;
			myValue = value;
		}
		
		public K key() {
			return myKey;
		}
		
		public V value() {
			return myValue;
		}
		
		//public String tos
	}

}
