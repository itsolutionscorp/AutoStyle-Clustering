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
	private ArrayList<LinkedList<KVPair>> hashTable;  
	private double loadFactor;
	

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		hashTable = new ArrayList<LinkedList<KVPair>>(DEFAULT_CAPACITY);
		loadFactor = DEFAULT_LOAD_FACTOR;
		capacity = DEFAULT_CAPACITY;
		for (int i = 0; i < capacity; i++) {
			hashTable.add(new LinkedList<KVPair>());
		}
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		hashTable = new ArrayList<LinkedList<KVPair>>(initialCapacity);
		capacity = initialCapacity;
		for (int i = 0; i < capacity; i++) {
			hashTable.add(new LinkedList<KVPair>());
		}
		loadFactor = DEFAULT_LOAD_FACTOR;
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
		hashTable = new ArrayList<LinkedList<KVPair>>(initialCapacity);
		this.loadFactor = loadFactor;
		capacity = initialCapacity;
		for (int i = 0; i < capacity; i++) {
			hashTable.add(new LinkedList<KVPair>());
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
		return this.capacity;
	}

	/**
	 * Returns whether the map contains the given key. Runs in O(1) time on
	 * average, relative to the size of the map.
	 */
	public boolean containsKey(K key) {
		int hashIndex = key.hashCode() % capacity;
		LinkedList<KVPair> link = hashTable.get(hashIndex);
		Iterator<KVPair> iter = link.iterator();
		while(iter.hasNext()) {
			if(iter.next().key.equals(key))
				return true;
		}
		return false;
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		for (int i = 0; i < capacity; i++) {
			Iterator<KVPair> iter = hashTable.get(i).iterator();
			while(iter.hasNext()) {
				if (iter.next().value.equals(value)) 
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
		int hashIndex = key.hashCode() % capacity;
		LinkedList<KVPair> bucket = hashTable.get(hashIndex);
		KVPair to_add = new KVPair(key, value);
		V return_value = null;
		if (bucket == null) {
			bucket = new LinkedList<KVPair>();
		}
		else 
		{
			int index = bucket.indexOf(to_add);
			if (index != -1) {
				return_value = bucket.get(index).value;
				bucket.remove(index);
				size--;
			}
		}
		bucket.add(to_add);
		size++;
		while (size > loadFactor * capacity){
			expand(capacity*2);
		}
		return return_value;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		int keyHash = key.hashCode();
		LinkedList<KVPair> pointer = hashTable.get(keyHash % capacity);
		V return_value = null;
		for (int i = 0; i < pointer.size(); i++) {
			KVPair temp = pointer.get(i);
			if (temp.key.equals(key)) {
				return_value = temp.value;
				pointer.remove(i);
				size--;
				break;
			}
		}
		return return_value;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		int hashIndex = key.hashCode() % capacity;
		LinkedList<KVPair> pointer = hashTable.get(hashIndex);
		Iterator<KVPair> iter = pointer.iterator();
		V return_value = null;
		while(iter.hasNext()){
			KVPair temp = iter.next();
			if(temp.key.equals(key)){
				return_value = temp.value;
			}
		}
		return return_value;
	}

	/**
	 * ExpanmyNextds the underlying array to the given capacity.
	 * 
	 * ALSO, rehashes all of the items into this new array. Items will likely
	 * end up in different buckets after the expansion.
	 */
	private void expand(int newCapacity) {
		ArrayList<LinkedList<KVPair>> newHashTable = new ArrayList<LinkedList<KVPair>>(newCapacity);
		capacity = newCapacity;
		for (int i = 0; i < capacity; i++) {
			newHashTable.add(new LinkedList<KVPair>());
		}
		for(int i=0;i<hashTable.size();i++){
			LinkedList<KVPair> bucket = hashTable.get(i);
			Iterator<KVPair> bucketIter = bucket.iterator();
			while(bucketIter.hasNext()){
				KVPair element = bucketIter.next();
				int hashCode = element.key.hashCode();
				LinkedList<KVPair> templink = newHashTable.get(hashCode % capacity);
				templink.add(element);
			}
		}
		hashTable = newHashTable;
	}

	/**
	 * Returns an iterator over the keys of this map.
	 */
	public Iterator<K> iterator() {
		return new HashMapIterator();
	}
	
	public String toString(){
		String x = "";
		for (int i = 0; i < hashTable.size(); i++) {
			x += (i + " ");
			x += hashTable.get(i).toString();
			x += "\n";
		}
		return x;
	}

	/**
	 * An iterator for the keys of the enclosing map.
	 */
	private class HashMapIterator implements Iterator<K> {
		private int hashPos;
		Iterator<KVPair> iter;
		
		public HashMapIterator() {
			// TODO Complete this!
			hashPos = 0;
			iter = hashTable.get(0).iterator();
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			if(iter.hasNext())
				return true;
			else {
				for (int i = hashPos + 1; i < hashTable.size(); i++)
					if (hashTable.get(i).size() > 0)
					return true;
			}
			return false;
		}

		@Override
		public K next() {
			// TODO Complete this!
			while(!iter.hasNext()){
				hashPos++;
				iter = hashTable.get(hashPos).iterator();
			}
			return iter.next().key;
		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair {
		private K key;
		private V value;
	
		private KVPair(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		@Override
		@SuppressWarnings("unchecked")
		public boolean equals(Object compareTo) {
			if (((KVPair) compareTo).key.equals(this.key))
				return true;
			return false;
		}
		
		@Override
		public String toString() {
			return "(" + key.toString() + ", " + value.toString() + ")";
		}
	}

}
